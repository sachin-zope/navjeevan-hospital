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
	
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<link href="https://cdn.datatables.net/1.10.10/css/dataTables.bootstrap.min.css" rel="stylesheet">
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
			<h3>MTP Register Report</h3>
		</div>
		
		<div class="row" style="padding-bottom: 20px;">
			<div class="col-sm-6">
				<h4><%= session.getAttribute("REPORT_MONTH") %>, <%= session.getAttribute("REPORT_YEAR") %></h4>
			</div>
			<div class="col-sm-6" style="text-align: right;">
				<form class="form-inline" action="MTPRegisterController">
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
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Yearly No</th>
							<th>Monthly No</th>
							<th>Dates</th>
							<th>Name &amp; Address</th>
							<th>Weeks</th>
							<th>Indication</th>
							<th>Opinion given by</th>
							<th>MTP done by</th>
							<th>No of Children</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${mtprs}" var="mtpr">
							<tr>
								<td><c:out value="${mtpr.mtpSerialNo}" /></td>
								<td><c:out value="${mtpr.monthlySerialNo}" /></td>
								<td>DOA: <fmt:formatDate pattern="dd-MM-yyyy" value="${mtpr.admitDate}" /><br>
									DOT: <fmt:formatDate pattern="dd-MM-yyyy" value="${mtpr.operationDate}" /><br>
									DOD: <fmt:formatDate pattern="dd-MM-yyyy" value="${mtpr.dischargeDate}" /></td>
								<td><c:out value="${mtpr.patientName}" /><br>
											<c:out value="${mtpr.patientAddress}"/>&nbsp;&nbsp;&nbsp; &nbsp;
											<c:out value="${mtpr.gender}"/>/<c:out value="${mtpr.age}"/></td>
								<td><c:out value="${mtpr.durationOfPregnancy}" /></td>
								<td><c:out value="${mtpr.mindication}" /></td>
								<td><c:out value="${mtpr.opinionGivenBy}" /></td>
								<td><c:out value="${mtpr.doneByDr}" /></td>
								<td>M: <c:out value="${mtpr.mChildrens}" /><br>
									F: <c:out value="${mtpr.fChildrens}" /></td>
								<td><a href="MTPRegisterController?action=edit&id=<c:out value="${mtpr.id}"/>">Edit</a>
								 &nbsp; <a href="MTPRegisterController?action=delete&id=<c:out value="${mtpr.id}"/>"  onclick="return confirm('Are you sure you want to delete this entry?');">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>