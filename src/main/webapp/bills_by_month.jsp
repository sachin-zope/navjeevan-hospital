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
			<h3>Bills By Month</h3>
		</div>
		<div class="row">
			<div>
				<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Serial No</th>
							<th>Total</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${bills}" var="bill">
							<tr>
								<td><c:out value="${bill.serialNo}" /></td>
								<td><c:out value="${bill.total}" /></td>
								<td><a href="BillController?action=edit&id=<c:out value="${bill.id}"/>">Edit</a>
								&nbsp;<a href="BillController?action=print&id=<c:out value="${bill.id}"/>" onclick="window.open(this.href,'_blank',
                                   'titlebar=no, toolbar=no, location=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=500, height=900'); return false;">Print</a>
								&nbsp;<a href="BillController?action=receipt&id=<c:out value="${bill.id}"/>" onclick="window.open(this.href,'_blank',
                                   'titlebar=no, toolbar=no, location=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=900, height=500'); return false;">Receipt</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
	</div>
</body>
</html>