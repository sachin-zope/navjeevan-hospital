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
		$('#mtpForm').submit();
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
			<h3>Edit MTP Register</h3>
		</div>

		<div class="row">
			<form class="form-horizontal" method="post"
				action="MTPRegisterController" id="mtpForm">
				<input type="hidden" name="action" value="edit" /> <input
					type="hidden" name="id" value="<c:out value="${mtpr.id}" />" />
				<div class="form-group">
					<label for="durationOfPregnancy" class="col-sm-2 control-label">Duration
						of Pregnancy</label>
					<div class="col-sm-2">
						<input type="number" class="form-control"
							id="durationOfPregnancy" name="durationOfPregnancy" min="1"
							max="40" value="<c:out value="${mtpr.durationOfPregnancy}" />">
					</div>
					<div class="col-sm-1">
						<label class="col-sm-2 control-label">Week's</label>
					</div>

					<label for="mtpOperationDate" class="col-sm-2 control-label">Operation
						Date</label>
					<div class="col-sm-3">
						<input type="date" class="form-control" id="mtpOperationDate"
							name="mtpOperationDate" placeholder="Operation Date"
							value="<fmt:formatDate pattern="yyyy-MM-dd" value="${mtpr.operationDate}" />" readonly>
					</div>
				</div>
				<div class="form-group">
					<label for="religion" class="col-sm-2 control-label">Religion</label>
					<div class="col-sm-3">
						<select name="religion" class="form-control">
							<option value="<c:out value="${mtpr.religion}" />" selected><c:out
									value="${mtpr.religion}" /></option>
							<option value="Hindu">Hindu</option>
							<option value="Muslim">Muslim</option>
							<option value="Shikh">Shikh</option>
						</select>
					</div>

					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-4">
						<div class="radio">

							<c:choose>
								<c:when test="${mtpr.married == 'Married'}">
									<label class="radio-inline"> <input type="radio"
										name="married" id="married" value="Married" checked>
										Married
									</label>
									<label class="radio-inline"> <input type="radio"
										name="married" id="unmarried" value="Unmarried">
										Unmarried
									</label>
								</c:when>
								<c:otherwise>
									<label class="radio-inline"> <input type="radio"
										name="married" id="married" value="Married"> Married
									</label>
									<label class="radio-inline"> <input type="radio"
										name="married" id="unmarried" value="Unmarried" checked>
										Unmarried
									</label>
								</c:otherwise>
							</c:choose>

						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="mindication" class="col-sm-2 control-label">Indication</label>
					<div class="col-sm-3">
						<select name="mindication" class="form-control">
							<option value=""></option>
							<option value="<c:out value="${mtpr.mindication}" />" selected><c:out
									value="${mtpr.mindication}" /></option>
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
							<option value="D">D</option>
							<option value="E">E</option>
						</select>
					</div>

					<label for="procedure" class="col-sm-2 control-label">Procedure</label>
					<div class="col-sm-3">
						<select name="procedure" class="form-control">
							<option value=""></option>
							<option value="<c:out value="${mtpr.procedure}" />" selected><c:out
									value="${mtpr.procedure}" /></option>
							<option value="D & E">D & E</option>
							<option value="Second trimester termination">Second
								trimester termination</option>
							<option value="Medication abortion">Medication abortion</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="alongWith" class="col-sm-2 control-label">Along
						With</label>
					<div class="col-sm-3">
						<select name="alongWith" class="form-control">
							<option value=""></option>
							<option value="<c:out value="${mtpr.alongWith}" />" selected><c:out
									value="${mtpr.alongWith}" /></option>
							<option value="Coper T">Coper T</option>
							<option value="Tubectomy">Tubectomy</option>
							<option value="Injectable Contraceptive">Injectable
								Contraceptive</option>
							<option value="Barrier Contraceptive">Barrier
								Contraceptive</option>
						</select>
					</div>

					<label for="totalChildrens" class="col-sm-2 control-label">Total
						Childrens</label>
					<div class="col-sm-1">
						<input type="number" class="form-control" id="mChildrens"
							name="mChildrens" placeholder="M" min="0" max="10"
							value="<c:out value="${mtpr.mChildrens}" />">
					</div>

					<div class="col-sm-1">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					</div>

					<div class="col-sm-1">
						<input type="number" class="form-control" id="fChildrens"
							name="fChildrens" placeholder="F" min="0" max="10"
							value="<c:out value="${mtpr.fChildrens}" />">
					</div>
				</div>

				<div class="form-group">
					<label for="doneby" class="col-sm-2 control-label">Done
						By Dr.</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="doneby"
							name="doneby" placeholder="Done by Dr."
							value="<c:out value="${mtpr.doneByDr}" />">
					</div>

					<label for="opinionby" class="col-sm-2 control-label">Opinion
						Given By</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="opinionby"
							name="opinionby" placeholder="Opinion Given By"
							value="<c:out value="${mtpr.opinionGivenBy}" />">
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
