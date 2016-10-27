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
	<script src="js/sum.js"></script>
	<link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common_styles.css" rel="stylesheet">

	<script>
		$(document).ready(function() {
		    var indoorTable = $('#example').DataTable({
		    	"order" : []	
		    });
		    
		    $("#monthlyTotal").val(indoorTable.column(5).data().sum());
		} );
		
		$(document).on("click", ".generateBillDialog", function () {
		     $(".modal-body #id").val($(this).data('id'));
		     $(".modal-body #ipdno").val($(this).data('ipdno'));
		     $(".modal-body #pname").html($(this).data('pname'));
		});
	</script>
	
	<style type="text/css">
		.separator {
			 padding-left: 2px;
			 padding-right: 2px;
		}
		
		.billlinks {
			color: #28B463;
		}
	</style>
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
				<form class="form-inline" action="IndoorRegisterController" method="get">
					<input type="hidden" name="action" value="report">
					<input type="hidden" name="type" value="complete">
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
			<div class="col-sm-6">
				<a href="IndoorRegisterController?action=print" target="_blank">Print Report</a>
			</div>
			<div class="col-sm-6" style="text-align: right;">
				Monthly Total: <input type="text" name="monthlyTotal" id="monthlyTotal" value="" disabled="disabled"/>
			</div>
		</div>
	</div>
		
		
			<div style="margin-left: 20px; width: 90%; ">
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
									<c:when test="${ ir.treatment == 'MTP + Tubectomy' }">
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
								<td><a href="IndoorRegisterController?action=edit&from=complete&id=<c:out value="${ir.id}"/>">Edit</a>
								<span class="separotor">|</span> <a href="IndoorRegisterController?action=delete&id=<c:out value="${ir.id}"/>"  onclick="return confirm('Are you sure you want to delete this entry?');">Delete</a>
								<c:choose>
									<c:when test="${(ir.fees > 0) && ir.billGenerated == false}">
										<span class="separotor">|</span> <a data-id="<c:out value="${ir.id}"/>" data-ipdno="<c:out value="${ir.ipdNo}"/>" data-pname="<c:out value="${ir.patientName}" />" data-toggle="modal" data-target="#myModal" class="generateBillDialog">Generate Bill</a>
									</c:when>
									<c:when test="${ir.billGenerated == true }">
										<br> <a class="billlinks" href="BillController?action=print&id=<c:out value="${ir.id}"/>" onclick="window.open(this.href,'_blank',
                                   'titlebar=no, toolbar=no, location=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=500, height=900'); return false;">Print</a>
										<span class="separotor">|</span> <a class="billlinks" href="BillController?action=receipt&id=<c:out value="${ir.id}"/>" onclick="window.open(this.href,'_blank',
                                   'titlebar=no, toolbar=no, location=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=900, height=500'); return false;">Receipt</a>
										<span class="separotor">|</span> <a class="billlinks" href="BillController?action=edit&id=<c:out value="${ir.id}"/> ">Edit Bill</a>
										<span class="separotor">|</span> <a class="billlinks" href="BillController?action=bill_receipt_print&id=<c:out value="${ir.id}" />" onclick="window.open(this.href,'_blank',
                                   'titlebar=no, toolbar=no, location=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=800, height=900'); return false;">Print Bill Receipt</a>
									</c:when>
								</c:choose>
								 </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Select Room Type</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" method="post" action="BillController"
						id="bill_form">
						<input type="hidden" name="action" value="generate"> 
						<input type="hidden" name="id" id="id" value="" />
						<input type="hidden" name="ipdno" id="ipdno" value="" />
						
						<div class="form-group">
							<label class="col-sm-3 control-label">Patient Name:</label>
							<label class="col-sm-8" id="pname"></label>
						</div>
						
						<div class="form-group">
							<label for="room_type" class="col-sm-3 control-label">Room Type</label>
							<div class="col-sm-3">
								<select id="room_type" name="room_type" class="form-control">
										<option value="general" selected="selected">General</option>
										<option value="special">Special</option>
									</select>
							</div>
						</div>
						
						<div class="form-group">
							<label for="bill_type" class="col-sm-3 control-label">Bill By</label>
							<div class="col-sm-3">
								<select id="bill_type" name="bill_type" class="form-control">
										<option value="Cash" selected="selected">Cash</option>
										<option value="Cheque">Cheque</option>
										<option value="NEFT">NEFT</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="cheque_no" class="col-sm-3 control-label">Cheque No.:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="cheque_no"
									name="cheque_no">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-10">
								<button type="submit" class="btn btn-success">Generate Bill</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>