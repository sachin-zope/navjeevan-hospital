<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $("#dummy").submit();
})
</script>
</head>
<body>
<%
	String month = (String) session.getAttribute("REPORT_MONTH");
	String year = (String) session.getAttribute("REPORT_YEAR");
%>

<form action="IndoorRegisterController" method="get" id="dummy">
<input type="hidden" name="action" value="report">
<input type="hidden" name="type" value="complete">
<input type="hidden" name="month" value="<%=month%>">
<input type="hidden" name="year" value="<%=year%>">
</form>
</body>
</html>