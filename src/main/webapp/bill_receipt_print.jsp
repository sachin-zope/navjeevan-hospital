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
	margin: 0 auto;
}

table { 
	border-collapse: collapse;
	border-spacing: 0;
} 

td {
	padding: 3px;
}

.particularsHeading {
	border-top: #000000 solid 2px;
	border-bottom: #000000 solid 2px;
	border-right: #000000 solid 2px;
	text-align: center;
	font-weight: bold;
	widht: 50%;
}

.amountHeading {
	border-top: #000000 solid 2px; 
	border-bottom: #000000 solid 2px;
	text-align: center;
	font-weight: bold;
	widht: 50%;
}

.particular {
	border-bottom: #000000 solid 1px;
	border-right: #000000 solid 2px;
	widht: 50%;
}

.amount {
	border-bottom: #000000 solid 1px;
	text-align: right;
	padding-right: 30px;
	widht: 50%;
}

.totalHeading {
	border-bottom: #000000 solid 2px;
	border-right: #000000 solid 2px;
	text-align: right;
	padding-right: 80px;
	font-weight: bold;
	widht: 50%;
}

.totalAmount {
	border-bottom: #000000 solid 2px;
	text-align: right;
	padding-right: 30px;
	font-weight: bold;
	widht: 50%;
}

.inwords {
	border-bottom: #000000 solid 1px;
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
	
	<table border="0" width="96%">
		<tr>
			<td>
				<table border="0" width="100%">
					<tr>
						<td width="100%" colspan="2" align="center"><span style="font-size: 18px;"><strong>Navjeevan Hospital</strong></span>
						<br><span style="font-size: 16px;">Address: Opp. Vani Mangal Karyalaya, Kailas Nagar, Bhadgaon Road Chalisgaon 424101.
						<br>Phone: 02589 224010</span></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="0" width="100%">
					<tr>
						<td width="50%">Bill No. : <c:out value="${print.billNo}" /></td>
						<td width="50%" align="right">Date : <fmt:formatDate pattern="dd/MM/yyyy" value="${print.billDate}" /></td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<table border="0" width="100%">
					<tr>
						<td width="15%">Name :</td>
						<td width="85%" style="border-bottom: #000000 solid 1px;"><c:out value="${print.patientName}" /></td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<table border="0" width="100%">
					<tr>
						<td width="15%">Sex :</td>
						<td width="35%" style="border-bottom: #000000 solid 1px;"><c:out value="${print.sex}" /></td>
						<td width="20%" align="right">Age :</td>
						<td width="30%" style="border-bottom: #000000 solid 1px;"><c:out value="${print.age}" /></td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<table border="0" width="100%">
					<tr>
						<td width="20%">Diagnosis :</td>
						<td width="80%" style="border-bottom: #000000 solid 1px;"><c:out value="${print.diagnosis}" /></td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<table border="0" width="100%">
					<tr>
						<td width="13%">DOA :</td>
						<td width="20%" style="border-bottom: #000000 solid 1px;"><fmt:formatDate pattern="dd/MM/yyyy" value="${print.admitDate}" /></td>
						<td width="13%" align="right">DOS :</td>
						<td width="20%" style="border-bottom: #000000 solid 1px;"><fmt:formatDate pattern="dd/MM/yyyy" value="${print.operationDate}" /></td>
						<td width="13%" align="right">DOD :</td>
						<td width="21%" style="border-bottom: #000000 solid 1px;"><fmt:formatDate pattern="dd/MM/yyyy" value="${print.dischargeDate}" /></td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td>
				<table border="	0" width="100%">
					<tr>
						<td class="particularsHeading">Particulars</td>
						<td class="amountHeading">Amount</td>
					</tr>
					
					<tr>
						<td class="particular">Indoor Hospital Stay</td>
						<td class="amount"> <c:out value="${print.indoorCharges}" /> </td>
					</tr>
					
					<c:if test="${print.sonography > 0}">
						<tr>
							<td class="particular">Sonography</td>
							<td class="amount"><c:out value="${print.sonography}" /></td>
						</tr>
					</c:if>
					
					<c:if test="${print.consultantCharges > 0}">
						<tr>
							<td class="particular">Consultant Charges</td>
							<td class="amount"><c:out value="${print.consultantCharges}" /></td>
						</tr>
					</c:if>
					
					<c:if test="${print.bloodTransmissionCharges > 0}">
						<tr>
							<td class="particular">Blood Transfusion Charges</td>
							<td class="amount"><c:out value="${print.bloodTransmissionCharges}" /></td>
						</tr>
					</c:if>

					<c:if test="${print.procedureCharges > 0}">
						<tr>
							<td class="particular">Procedure Charges</td>
							<td class="amount"><c:out value="${print.procedureCharges}" /></td>
						</tr>
					</c:if>

					<c:if test="${print.operationCharges > 0}">
						<tr>
							<td class="particular">Operation Charges</td>
							<td class="amount"><c:out value="${print.operationCharges}" /></td>
						</tr>
					</c:if>

					<c:if test="${print.episiotomyCharges > 0}">
						<tr>
							<td class="particular">Episiotomy Charges</td>
							<td class="amount"><c:out value="${print.episiotomyCharges}" /></td>
						</tr>
					</c:if>

					<c:if test="${print.nursingCharges > 0}">
						<tr>
							<td class="particular">Nursing Charges</td>
							<td class="amount"><c:out value="${print.nursingCharges}" /></td>
						</tr>
					</c:if>

					<c:if test="${print.otCharges > 0}">
						<tr>
							<td class="particular">O.T. Charges</td>
							<td class="amount"><c:out value="${print.otCharges}" /></td>
						</tr>
					</c:if>

					<tr>
						<td class="particular">Other Charges</td>
						<td class="amount"><c:out value="${print.otherCharges}" /></td>
					</tr>
					
					<tr>
						<td class="totalHeading">Total - </td>
						<td class="totalAmount"><c:out value="${print.total}" /></td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<table border="0" width="100%">
					<tr>
						<td width="25%" valign="top">Rs. In Words :</td>
						<td width="75%" class="inwords"><c:out value="${print.inWords}" /> ONLY</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td>
				<table border="0" width="100%">
					<tr>
						<td style="text-align: right; padding-top: 20px; padding-right: 10px;">NAVJEEVAN HOSPITAL</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<hr style="border-top: dotted 1px;" />
	<br>
	<table border="1" width="96%">
		<tr>
			<td>
				<table class="noborder" width="100%">
					<tr>
						<td width="100%" colspan="2" align="center"><span style="font-size: 18px;"><strong>Navjeevan Hospital</strong></span>
						<br><span style="font-size: 16px;">Address: Opp. Vani Mangal Karyalaya, Kailas Nagar, Bhadgaon Road Chalisgaon 424101.
						<br>Phone: 02589 224010</span></td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<table class="noborder" width="100%">
					<tr>
						<td width="50%"><strong>Receipt No.:</strong> <c:out value="${receipt.receiptNo}" /></td>
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
						<td width="70%" style="border-bottom: #000000 solid 1px;"><c:out value="${receipt.inWords}" /> ONLY</td>
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