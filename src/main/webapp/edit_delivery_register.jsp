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

<script src="js/jquery-1.12.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<link href="css/bootstrap.min.css" rel="stylesheet">

<style>
.form-horizontal .radio-inline {
	padding-top: 0px;
}
</style>

<script type="text/javascript">
	function validate() {
		$('#deliveryForm').submit();
	};

	function goBack() {
		window.history.back();
	};
	
	$(document).ready(function() {
		$("#reponseAlert").fadeTo(2000, 500).slideUp(500, function() {
		});
	});
</script>

</head>
<body>
	<jsp:include page="nav.html"></jsp:include>

	<div class="container">
		<div class="page-header">
			<h3>Edit Delivery Register</h3>
		</div>

		<div class="row">
			<form class="form-horizontal" method="post"
				action="DeliveryRegisterController" id="deliveryForm">
				<input type="hidden" name="action" value="edit" /> <input
					type="hidden" name="id" value="<c:out value="${dr.id}" />" />
				<div class="form-group">
					<label for="deliveryDate" class="col-sm-2 control-label">Delivery
						Date</label>
					<div class="col-sm-3">
						<input type="date" class="form-control" id="deliveryDate"
							name="deliveryDate" placeholder="DD/MM/YYYY" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${dr.deliveryDate}" />">
					</div>

					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-4">
						<div class="checkbox">
							<label class="radio-inline">
							<c:choose>
								<c:when test="${dr.episiotomy eq 'Given'}">
									<input type="checkbox"
										name="episiotomy" id="episiotomy" value="Episiotomy" checked="checked">
										Episiotomy
								</c:when>
								<c:otherwise>
									<input type="checkbox"
										name="episiotomy" id="episiotomy" value="Episiotomy">
										Episiotomy
								</c:otherwise>
							</c:choose>
							</label>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="deliveryType" class="col-sm-2 control-label">Delivery
						Type</label>
					<div class="col-sm-3">
						<select id="deliveryType" name="deliveryType" class="form-control">
							<option value=""></option>
							<option value="<c:out value="${dr.deliveryType}" />" selected><c:out value="${dr.deliveryType}" /></option>
							<option value="Vaccum">Vaccum</option>
							<option value="Forceps">Forceps</option>
							<option value="VBAC">VBAC</option>
							<option value="LSCS">LSCS</option>
							<option value="FTND">FTND</option>
							<option value="PTVD">PTVD</option>
						</select>
					</div>

					<label class="col-sm-2 control-label">Sex of Child</label>
					<div class="col-sm-3">
						<div class="radio">
							<label class="radio-inline">
							<c:choose>
								<c:when test="${dr.sexOfChild eq 'Male'}">
									<input type="radio"
								name="sexOfChild" id="gMale" value="Male" checked="checked"> Male
								</c:when>
								<c:otherwise>
									<input type="radio"
								name="sexOfChild" id="gMale" value="Male"> Male
								</c:otherwise>
							</c:choose>
							</label> <label class="radio-inline"> 
							<c:choose>
								<c:when test="${dr.sexOfChild eq 'Female'}">
									<input type="radio"
								name="sexOfChild" id="gFemale" value="Female" checked="checked"> Female
								</c:when>
								<c:otherwise>
									<input type="radio"
								name="sexOfChild" id="gFemale" value="Female"> Female
								</c:otherwise>
							</c:choose>
							</label>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="birthWeight" class="col-sm-2 control-label">Birth
						Weight</label>
					<div class="col-sm-3">
						<input type="number" class="form-control" id="birthWeight"
							name="birthWeight" placeholder="Weight" step="any" value="<c:out value="${dr.birthWeight}" />">
					</div>

					<label for="birthTime" class="col-sm-2 control-label">Birth
						Time</label>
					<div class="col-sm-3">
						<input type="time" class="form-control" id="birthTime"
							name="birthTime" placeholder="Time" value="<c:out value="${dr.birthTime}" />">
					</div>
				</div>

				<div class="form-group">
					<label for="deliveryRemarks" class="col-sm-2 control-label">Delivery
						Remarks</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="deliveryRemarks"
							name="deliveryRemarks" placeholder="Remarks" value="<c:out value="${dr.deliveryRemarks}" />">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-success" onclick="validate()">Save</button>
						<button type="button" class="btn btn-default" onclick="goBack()">Cancel</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
