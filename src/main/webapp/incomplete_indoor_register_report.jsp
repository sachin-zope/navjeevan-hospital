<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<title>Navjeevan Hospital</title>
	<script src="js/jquery-1.12.0.min.js"></script>
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/dataTables.bootstrap.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common_styles.css" rel="stylesheet">

	<script>
		$(document).ready(function() {
		    $('#example').DataTable({
		    	"order" : []	
		    });
		} );
	</script>
</head>

<body>
	<jsp:include page="nav.html"></jsp:include>
	<div class="container">
		<div class="page-header">
			<h3>Indoor Register Report</h3>
		</div>
		<div class="row" style="padding-bottom: 20px;">
			<div class="col-sm-6">
				<h4><%= session.getAttribute("REPORT_MONTH") %>, <%= session.getAttribute("REPORT_YEAR") %></h4>
			</div>
			<div class="col-sm-6" style="text-align: right;">
				<form class="form-inline" action="IndoorRegisterController">
					<input type="hidden" name="action" value="report">
					<div class="form-group">
						<select name="month" class="form-control">
							<option value="">Select Month</option>
							<option value="Jan">Jan</option>
							<option value="Feb">Feb</option>
							<option value="Mar">Mar</option>
							<option value="Apr">Apr</option>
							<option value="May">May</option>
							<option value="Jun">Jun</option>
							<option value="Jul">Jul</option>
							<option value="Aug">Aug</option>
							<option value="Sep">Sep</option>
							<option value="Oct">Oct</option>
							<option value="Nov">Nov</option>
							<option value="Dec">Dec</option>
						</select>
					</div>
					<div class="form-group">
						<select name="year" class="form-control">
							<option value="">Select Year</option>
							<option value="2015">2015</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
							<option value="2019">2019</option>
							<option value="2020">2020</option>
						</select>
					</div>
					<input type="submit" name="btnSubmit" class="btn btn-primary" value="Submit"/>
				</form>
			</div>
		</div>
		<div class="row">
			<div>
				<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>IPD No</th>
							<th>Dates</th>
							<th>Name &amp; Address</th>
							<th>Diagnosis</th>
							<th>Treatment</th>
							<th>Fees</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${irs}" var="ir">
							<tr>
								<td><c:out value="${ir.ipdNo}" /></td>
								<td>DOA: <fmt:formatDate pattern="dd-MM-yyyy" value="${ir.admitDate}" /><br>
								DOD: <fmt:formatDate pattern="dd-MM-yyyy" value="${ir.dischargeDate}" /></td>
								<c:choose>
									<c:when test="${ ir.treatment == 'MTP' }">
										<td><c:out value="${ir.mtpSerialNo}" /></td>
									</c:when>
									<c:when test="${ ir.treatment == 'MTP + Abdominal Tubectomy' }">
										<td><c:out value="${ir.mtpSerialNo}" /></td>
									</c:when>
									<c:when test="${ ir.treatment == 'MTP + Laparoscopic Tubectomy' }">
										<td><c:out value="${ir.mtpSerialNo}" /></td>
									</c:when>
									<c:otherwise>
										<td><c:out value="${ir.patientName}" /><br>
										<c:out value="${ir.patientAddress}"/>&nbsp;&nbsp;&nbsp; &nbsp;
										<c:out value="${ir.gender}"/>/<c:out value="${ir.age}"/></td>
									</c:otherwise>
								</c:choose>
								
								<td><c:out value="${ir.diagnosis}" /></td>
								<td><c:out value="${ir.treatment}" /></td>
								<td><c:out value="${ir.fees}" /></td>
								<td><a href="IndoorRegisterController?action=edit&id=<c:out value="${ir.id}"/>">Edit</a>
								 &nbsp; <a href="IndoorRegisterController?action=delete&id=<c:out value="${ir.id}"/>"  onclick="return confirm('Are you sure you want to delete this entry?');">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>