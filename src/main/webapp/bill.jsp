<%@page import="java.text.SimpleDateFormat"%>
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

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<link href="css/bootstrap.min.css" rel="stylesheet">

<style>
#diagnosisOther {
	display: none;
	padding-top: 10px;
}

#treatmentOther {
	display: none;
	padding-top: 10px;
}
.form-horizontal .radio-inline {
	padding-top: 0px;
}
</style>

<script type="text/javascript">

function validate() {
	if($('#room_type').val() == "-1") {
		alert("Please select Room Type");
		return false;
	}
	
	$('#bill_form').submit();
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
			<h3>Select Rooom Type</h3>
		</div>

		<div class="row">
			<form class="form-horizontal" method="post"
				action="BillController" id="bill_form">
				<input type="hidden" name="action" value="generate">
				<input type="hidden" name="id" value="<c:out value="${ir.id}" />"/>
				<input type="hidden" name="ipdno" value="<c:out value="${ir.ipdNo}" />"/>
				<div class="form-group">
					<label for="admitDate" class="col-sm-2 control-label">Admit
						Date</label>
					<div class="col-sm-3">
						<input type="date" class="form-control" id="admitDate"
							name="admitDate" placeholder="Admit Date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${ir.admitDate}" />" readonly="readonly">
					</div>

					<label for="dischargeDate" class="col-sm-2 control-label">Discharge
						Date</label>
					<div class="col-sm-3">
						<input type="date" class="form-control" id="dischargeDate"
							name="dischargeDate" placeholder="Discharge Date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${ir.dischargeDate}" />" readonly="readonly">
					</div>
				</div>

				<div class="form-group">
					<label for="pName" class="col-sm-2 control-label">Name</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="pName" name="pName"
							placeholder="Patient Name" value="<c:out value="${ir.patientName}" />" readonly="readonly">
					</div>

					<label class="col-sm-1 control-label"></label>
					<div class="col-sm-4">
						<div class="radio">
							<label class="radio-inline"> <input type="radio"
								name="gender" id="male" value="male" disabled="disabled"> Male
							</label> <label class="radio-inline"> <input type="radio"
								name="gender" id="female" value="female" checked disabled="disabled"> Female
							</label>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="pAddress" class="col-sm-2 control-label">Address</label>
					<div class="col-sm-4">
						<textarea class="form-control" rows="3" id="pAddress"
							name="pAddress" placeholder="Address" readonly="readonly"><c:out value="${ir.patientAddress}" /></textarea>
					</div>

					<label for="age" class="col-sm-1 control-label">Age </label>
					<div class="col-sm-3">
						<input type="number" class="form-control" id="age" name="age"
							placeholder="Age" max="100" min="1" value="<c:out value="${ir.age}" />" readonly="readonly">
					</div>
				</div>

				<div class="form-group">
					<label for="diagnosis" class="col-sm-2 control-label">Diagnosis</label>
					<div class="col-sm-3">
						<select name="diagnosis" id="diagnosis" class="form-control" disabled>
							<option value="-1">Select Diagnosis</option>
							<option value="<c:out value="${ir.diagnosis}" />" selected><c:out value="${ir.diagnosis}" /></option>
							<option value="Primigravida">Primigravida</option>
							<option value="G2P1L0">G2P1L0</option>
							<option value="G2P1L1">G2P1L1</option>
							<option value="G3P2L2">G3P2L2</option>
							<option value="G4P3L3">G4P3L3</option>
							<option value="Primigravida with CPD">Primigravida with CPD</option>
							<option value="Primigravida with PIH with oligohydramnios">Primigravida with PIH with oligohydramnios</option>
							<option value="Primigravida with failure to progress">Primigravida with failure to progress</option>
							<option value="G2P1L1 with previous LSCS with CPD">G2P1L1 with previous LSCS with CPD</option>
							<option value="Primigravida with breech presentation">Primigravida with breech presentation</option>
							<option value="G2P1L1 with previous LSCS">G2P1L1 with previous LSCS</option>
							<option value="P2L2 for tubal ligation">P2L2 for tubal ligation</option>
							<option value="P3L3 for tubal ligation">P3L3 for tubal ligation</option>
							<option value="Missed Abortion">Missed Abortion</option>
							<option value="Incomplete Abortion">Incomplete Abortion</option>
							<option value="Chronic Cervicitis">Chronic Cervicitis</option>
							<option value="Dysfunctional uterine bleeding">Dysfunctional uterine bleeding</option>
							<option value="Uterine prolapse with CRE">Uterine prolapse with CRE</option>
							<option value="Fibroid uterus">Fibroid uterus</option>
							<option value="Infertility for diagnostic laparoscopy">Infertility for diagnostic laparoscopy</option>
							<option value="Ovarian cyst">Ovarian cyst</option>
							<option value="other">Other</option>
						</select>
						<div id="diagnosisOther">
							<input name="OtherDiagnosis" class="form-control" type="text"
								placeholder="Other Diagnosis" />
						</div>
					</div>

					<label for="treatment" class="col-sm-2 control-label">Treatment</label>
					<div class="col-sm-3">
						<select name="treatment" id="treatment" class="form-control" disabled>
							<option value="-1">Select Treatment</option>
							<option value="<c:out value="${ir.treatment}" />" selected><c:out value="${ir.treatment}" /></option>
							<option value="Delivery">Delivery</option>
							<option value="LSCS">LSCS</option>
							<option value="LSCS with Tubectomy">LSCS with Tubectomy</option>
							<option value="MTP">MTP</option>
							<option value="MTP + Abdominal Tubectomy">MTP +	Abdominal Tubectomy</option>
							<option value="MTP + Laparoscopic Tubectomy">MTP + Laparoscopic Tubectomy</option>
							<option value="Tubectomy">Tubectomy</option>
							<option value="Abdominal Tubectomy">Abdominal Tubectomy</option>
							<option value="Laparoscopic Tubectomy">Laparoscopic Tubectomy</option>
							<option value="Abdominal Hysterectomy">Abdominal Hysterectomy</option>
							<option value="Vaginal Hysterectomy">Vaginal Hysterectomy</option>
							<option value="Diagnostic Laparohysteroscopy">Diagnostic Laparohysteroscopy</option>
							<option value="Laparoscopic Hysterectomy">Laparoscopic Hysterectomy</option>
							<option value="D and E">D and E</option>
							<option value="Cervical Encirclage">Cervical Encirclage</option>
							<option value="other">Other</option>
						</select>
						<div id="treatmentOther">
							<input name="OtherTreatment" class="form-control" type="text"
								placeholder="Other Treatment" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="fees" class="col-sm-2 control-label">Fees</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="fees" name="fees"
							placeholder="Fees" value="<c:out value="${ir.fees}" />" readonly="readonly">
					</div>

				</div>

				<div class="form-group">
					<label for="room_type" class="col-sm-2 control-label">Room Type</label>
					<div class="col-sm-3">
						<select id="room_type" name="room_type" class="form-control">
								<option value="-1"></option>
								<option value="general">General</option>
								<option value="semi-special">Semi Special</option>
							</select>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-10">
						<button type="button" class="btn btn-success" onclick="validate()">Generate Bill</button>
						<button type="button" class="btn btn-default" onclick="goBack()">Cancel</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>