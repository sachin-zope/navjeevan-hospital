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


<script type="text/javascript">
	function validate() {
		$('#otForm').submit();
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
			<h3>Edit OT Register</h3>
		</div>

		<div class="row">
			<form class="form-horizontal" method="post"
				action="OTRegisterController" id="otForm">
				<input type="hidden" name="action" value="edit" /> <input
					type="hidden" name="id" value="<c:out value="${otr.id}" />" />
				<div class="form-group">
					<label for="operationDate" class="col-sm-2 control-label">Operation
						Date</label>
					<div class="col-sm-3">
						<input type="date" class="form-control" id="operationDate"
							name="operationDate" placeholder="DD/MM/YYYY" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${otr.operationDate}" />" required>
					</div>

					<label for="NameOfSurgeon" class="col-sm-2 control-label">Name
						of Surgeon</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="NameOfSurgeon"
							name="NameOfSurgeon" placeholder="Name Of Surgeon" value="<c:out value="${otr.nameOfSurgeon}" />" required>
					</div>
				</div>

				<div class="form-group">
					<label for="assistant" class="col-sm-2 control-label">Assistant</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="assistant"
							name="assistant" placeholder="Assistant" value="<c:out value="${otr.assistant}" />">
					</div>

					<label for="anaesthetist" class="col-sm-2 control-label">Anaesthetist</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="anaesthetist"
							name="anaesthetist" placeholder="Anaesthetist" value="<c:out value="${otr.anaesthetist}" />">
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
