<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Secure Banking System</title>
</head>
<body>

<h3>Transactions</h3>
<c:if test="${!empty listTransations}">
	<table class="tg">
	<tr>
		<th width="80">Transaction ID</th>
		<th width="120">Transaction Date</th>
		<th width="120">Transaction Type</th>
		<th width="60">Transaction Status</th>
		<th width="60">Transaction Amount</th>
	</tr>
	<c:forEach items="${listTransations}" var="transactions">
		<tr>
			<td>${transactions.transactionId}</td>
			<td>${transactions.transactionTime}</td>
			<td>${transactions.transactionType}</td>
			<td>${transactions.transactionStatus}</td>
			<td>${transactions.transactionAmount}</td>
		</tr>
	</c:forEach>
	</table>
</c:if>


</body>
</html>