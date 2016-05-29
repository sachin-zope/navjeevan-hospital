<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Navjeevan Hospital</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<link href="css/bootstrap.min.css" rel="stylesheet">
<style>
.right {
text-align: right;
}
</style>

<script type="text/javascript">

function validate() {
	
	$('#final_bill_form').submit();
}

function goBack() {
	window.history.back();
}
</script>
</head>	
<body>
	<jsp:include page="nav.html"></jsp:include>
	<div class="container">
		<div class="page-header">
			<h3>Generate Bill</h3>
		</div>

		<div class="row">
			<form class="form-horizontal" method="post"
				action="BillController" id="final_bill_form">
				<input type="hidden" name="action" value="save">
				<input type="hidden" name="indoorId" value="<c:out value="${bill.indoorRegisterId}" />">
				<input type="hidden" name="room_type" value="<c:out value="${bill.roomType}" />">
				<input type="hidden" name="serial_no" value="<c:out value="${bill.serialNo}" />">
				<div class="form-group">
					<label for="indoor_hospital_charges" class="col-sm-3 control-label">Indoor
						Hospital Charges</label>
					<div class="col-sm-4">
						<input type="number" class="form-control right"
							id="indoor_hospital_charges" name="indoor_hospital_charges" value="<c:out value="${bill.indoorCharges}"/>" min="1">
					</div>
				</div>

				<div class="form-group">
					<label for="sonography" class="col-sm-3 control-label">Sonography</label>		
					<div class="col-sm-4">
						<input type="number" class="form-control right" id="sonography" value="<c:out value="${bill.sonography}"/>"
							name="sonography" min="1">
					</div>
				</div>

				<div class="form-group">
					<label for="consultant_charges" class="col-sm-3 control-label">Consultant
						Charges</label>
					<div class="col-sm-4">
						<input type="number" class="form-control right" id="consultant_charges" value="<c:out value="${bill.consultantCharges}"/>"
							name="consultant_charges" min="1">
					</div>
				</div>

				<div class="form-group">
					<label for="blood_transfusion_charges"
						class="col-sm-3 control-label">Blood Transfusion Charges</label>
					<div class="col-sm-4">
						<input type="number" class="form-control right"
							id="blood_transfusion_charges" name="blood_transfusion_charges" value="<c:out value="${bill.bloodTransmissionCharges}"/>"
							min="1">
					</div>
				</div>

				<div class="form-group">
					<label for="procedure_charges" class="col-sm-3 control-label">Procedure
						Charges</label>
					<div class="col-sm-4">
						<input type="number" class="form-control right" id="procedure_charges" value="<c:out value="${bill.procedureCharges}"/>"
							name="procedure_charges" min="1">
					</div>
				</div>

				<div class="form-group">
					<label for="operation_charges" class="col-sm-3 control-label">Operation
						Charges</label>
					<div class="col-sm-4">
						<input type="number" class="form-control right" id="operation_charges" value="<c:out value="${bill.operationCharges}"/>"
							name="operation_charges" min="1">
					</div>
				</div>

				<div class="form-group">
					<label for="episiotomy_charges" class="col-sm-3 control-label">Episiotomy
						Charges</label>
					<div class="col-sm-4">
						<input type="number" class="form-control right" id="episiotomy_charges" value="<c:out value="${bill.episiotomyCharges}"/>"
							name="episiotomy_charges" min="1">
					</div>
				</div>

				<div class="form-group">
					<label for="nursing_charges" class="col-sm-3 control-label">Nursing
						Charges</label>
					<div class="col-sm-4">
						<input type="number" class="form-control right" id="nursing_charges" value="<c:out value="${bill.nursingCharges}"/>"
							name="nursing_charges" min="1">
					</div>
				</div>

				<div class="form-group">
					<label for="ot_charges" class="col-sm-3 control-label">O.T.
						Charges</label>
					<div class="col-sm-4">
						<input type="number" class="form-control right" id="ot_charges" value="<c:out value="${bill.otCharges}"/>"
							name="ot_charges" min="1">
					</div>
				</div>

				<div class="form-group">
					<label for="other_charges" class="col-sm-3 control-label">Other
						Charges</label>
					<div class="col-sm-4">
						<input type="number" class="form-control right" id="other_charges" value="<c:out value="${bill.otherCharges}"/>"
							name="other_charges" min="1">
					</div>
				</div>

				<div class="form-group">
					<label for="total" class="col-sm-3 control-label">Total</label>
					<div class="col-sm-4">
						<input type="text" class="form-control right" id="total" name="total" value="<c:out value="${bill.total}"/>" 
							readonly="readonly">
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-10">
						<button type="button" class="btn btn-success" onclick="validate()">Save</button>
						<button type="button" class="btn btn-default" onclick="goBack()">Cancel</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$('#final_bill_form input[type="number"]').each(function() {
				$(this).keyup(function() {
					calculateSum();
				});
			});
		});

		function calculateSum() {

			var sum = 0;
			$('#final_bill_form input[type="number"]').each(function() {
				if (!isNaN(this.value) && this.value.length != 0) {
					sum += parseFloat(this.value);
				}
			});
			$("#total").val(sum.toFixed(2));
		}
	</script>
</body>
</html>