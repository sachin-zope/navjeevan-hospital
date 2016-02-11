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
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>

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
	if(!$("#admitDate").val()) {
		alert("Please select admit date");
		return false;
	}
	
	if(!$("#pName").val()) {
		alert("Please enter patient name");
		return false;
	}
	
	if(!$("#pAddress").val()) {
		alert("Please enter patient address");
		return false;
	}
	
	if(!$("#age").val()) {
		alert("Please enter age");
		return false;
	}
	
	if($('#diagnosis').val() == "-1") {
		alert("Please select Diagnosis");
		return false;
	}
	
	if($('#treatment').val() == "-1") {
		alert("Please select Treatment");
		return false;
	}
	
	$('#indoorForm').submit();
};


$(document).ready(function () {
	
	$('input[name=pName]').on('keyup', function(){
		var $this = $(this), value = $this.val(); 
        $this.val( value.toUpperCase() );
	});
	
	$('input[name=pAddress]').on('keyup', function(){
		var $this = $(this), value = $this.val(); 
        $this.val( value.toUpperCase() );
	});
	
	$('input[name="admitDate"]').change(function(){
        var doa = new Date(this.value);
        
        if($("#dischargeDate").val()) {
        	var dod =  new Date($("#dischargeDate").val());
        	if(doa.getTime() > dod.getTime()) {
    			alert("Admit date should be equal to or earlier than Discharge Date");
    			this.value = "";
    		}
        }
        
        if($("#mtpOperationDate").val()) {
        	var dop =  new Date($("#mtpOperationDate").val());
        	if(doa.getTime() > dop.getTime()) {
    			alert("Admit date should be equal to or earlier than Operation Date");
    			this.value = "";
    		}
        }
    });
	
	$('input[name="dischargeDate"]').change(function(){
        var dod = new Date(this.value);
        
        if($("#admitDate").val()) {
        	var doa = new Date($("#admitDate").val());
        	if(doa.getTime() > dod.getTime()) {
    			alert("Discharge date should be equal to or later than Admit Date");
    			this.value = "";
    		}
        }
        
        if($("#mtpOperationDate").val()) {
        	var dop =  new Date($("#mtpOperationDate").val());
        	if(dod.getTime() < dop.getTime()) {
    			alert("Discharge date should be equal to or later than Operation Date");
    			this.value = "";
    		}
        }
    });
	
    $("#reponseAlert").fadeTo(2000, 500).slideUp(500, function(){
    });
    
	$('select[name=diagnosis]').change(function(e){
	  if ($(this).val() == 'other'){
	    $('#diagnosisOther').show();
	  }else{
	    $('#diagnosisOther').hide();
	  }
	});
});
</script>

</head>
<body>
	<jsp:include page="nav.html"></jsp:include>

	<div class="container">
		<div class="page-header">
			<h3>Indoor Register</h3>
		</div>

		<div class="row">
			<form class="form-horizontal" method="post"
				action="IndoorRegisterController" id="indoorForm">
				<input type="hidden" name="id" value="<c:out value="${ir.id}" />"/>
				<div class="form-group">
					<label for="admitDate" class="col-sm-2 control-label">Admit
						Date</label>
					<div class="col-sm-3">
						<input type="date" class="form-control" id="admitDate"
							name="admitDate" placeholder="Admit Date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${ir.admitDate}" />" required>
					</div>

					<label for="dischargeDate" class="col-sm-2 control-label">Discharge
						Date</label>
					<div class="col-sm-3">
						<input type="date" class="form-control" id="dischargeDate"
							name="dischargeDate" placeholder="Discharge Date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${ir.dischargeDate}" />">
					</div>
				</div>

				<div class="form-group">
					<label for="pName" class="col-sm-2 control-label">Name</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="pName" name="pName"
							placeholder="Patient Name" value="<c:out value="${ir.patientName}" />" required>
					</div>

					<label class="col-sm-1 control-label"></label>
					<div class="col-sm-4">
						<div class="radio">
							<label class="radio-inline"> <input type="radio"
								name="gender" id="male" value="male"> Male
							</label> <label class="radio-inline"> <input type="radio"
								name="gender" id="female" value="female" checked> Female
							</label>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="pAddress" class="col-sm-2 control-label">Address</label>
					<div class="col-sm-4">
						<textarea class="form-control" rows="3" id="pAddress"
							name="pAddress" placeholder="Address" required><c:out value="${ir.patientAddress}" /></textarea>
					</div>

					<label for="age" class="col-sm-1 control-label">Age </label>
					<div class="col-sm-3">
						<input type="number" class="form-control" id="age" name="age"
							placeholder="Age" max="100" min="1" value="<c:out value="${ir.age}" />" required>
					</div>
				</div>

				<div class="form-group">
					<label for="diagnosis" class="col-sm-2 control-label">Diagnosis</label>
					<div class="col-sm-3">
						<select name="diagnosis" id="diagnosis" class="form-control">
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
							placeholder="Fees" value="<c:out value="${ir.fees}" />">
					</div>

					<label for="remarks" class="col-sm-2 control-label">Remarks</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="remarks"
							name="remarks" placeholder="Remarks" value="<c:out value="${ir.remarks}" />">
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-success" onclick="validate()">Save</button>
						<button type="button" class="btn btn-default">Cancel</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
