<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery.min.js"></script>

<style type="text/css">
body {
	font-size: 12px;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
}

td {
	padding: 10px;
}

.noborder {
border-width: 0;
}
</style>

<script type="text/javascript">
	$(function(){
        $('#print').click(function(){
            $('#print').hide()
        })
    })
</script>
</head>
<body>
	<div style="padding: 10px; text-align: center; width: 100%;">
		<a href="javascript:window.print()" id="print">Print</a>
	</div>
	
	<table border="1">
		<tr>
			<td>
				<table class="noborder">
					<tr>
						<td width="50%"><span style="font-size: 18px;"><strong>Navjeevan Hospital</strong></span></td>
						<td width="50%" align="right"><span style="font-size: 16px;"><strong>Dr. Narendra Zope</strong></span></td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<table class="noborder">
					<tr>
						<td width="50%"><strong>Receipt No.:</strong></td>
						<td width="50%" align="right"><strong>Date:</strong> <fmt:formatDate pattern="dd/MM/yyyy" value="${receipt.receiptDate}" /></td>
					</tr>
				</table>
			</td>
		</tr>

		<tr>
			<td>
				<table class="noborder">
					<tr>
						<td align="center" colspan="3">-- RECEIVED WITH THANKS FROM --</td>
					</tr>
					<tr>
						<td width="30%"><strong>Mr. / Mrs. / Ms. :</strong></td>
						<td width="60%" style="border-bottom: #000000 solid 1px;"><c:out value="${receipt.patientName}" /></td>
							<td width="10%"></td>
					</tr>
					
					<tr>
						<td width="30%"><strong>THE SUM OF RUPEES :</strong></td>
						<td width="70%" style="border-bottom: #000000 solid 1px;">&#x20B9; <c:out value="${receipt.totalBill}" /> /-</td>
						<td width="10%"></td>
					</tr>
					
					<tr>
						<td width="30%"><strong>IN WORDS RUPEES :</strong></td>
						<td width="70%" style="border-bottom: #000000 solid 1px;"><c:out value="${receipt.inWords}" /></td>
						<td width="10%"></td>
					</tr>
					
					<tr>
						<td width="30%"><strong>BY CASH / CHEQUE / DRAFT NO :</strong></td>
						<td width="70%" style="border-bottom: #000000 solid 1px;"></td>
						<td width="10%"></td>
					</tr>
					
					<tr>
						<td width="30%"><strong>FOR :</strong></td>
						<td width="70%" style="border-bottom: #000000 solid 1px;"></td>
						<td width="10%"></td>
					</tr>
					
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					
					<tr>
						<td align="right" colspan="2">Authorised Signature</td>
						<td width="10%"></td>
					</tr>
					
				</table>
			</td>
		</tr>
	</table>
</body>
</html>