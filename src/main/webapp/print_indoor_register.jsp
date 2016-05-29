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
<title>Indoor Register Report</title>

<style type="text/css">

th, td {
	font-size: 11px;
	padding: 10px;
}
</style>
</head>

<body>
	<div>
		<h3>
			Indoor Register Report -
			<%= session.getAttribute("REPORT_MONTH") %>,
			<%= session.getAttribute("REPORT_YEAR") %></h3>
	</div>

	<table border="1" cellspacing="0" width="70%">
		<thead>
			<tr>
				<th>IPD No</th>
				<th>Dates</th>
				<th>Name &amp; Address</th>
				<th>Diagnosis</th>
				<th>Treatment</th>
				<th>Fees</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${irs}" var="ir">
				<tr>
					<td><c:out value="${ir.ipdNo}" /></td>
					<td>DOA: <fmt:formatDate pattern="dd-MM-yyyy"
							value="${ir.admitDate}" /><br> DOD: <fmt:formatDate
							pattern="dd-MM-yyyy" value="${ir.dischargeDate}" /></td>
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
							<td><c:out value="${ir.patientName}" /><br> <c:out
									value="${ir.patientAddress}" />&nbsp;&nbsp;&nbsp; &nbsp; <c:out
									value="${ir.gender}" />/<c:out value="${ir.age}" /></td>
						</c:otherwise>
					</c:choose>

					<td><c:out value="${ir.diagnosis}" /></td>
					<td><c:out value="${ir.treatment}" /></td>
					<td><c:out value="${ir.fees}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>