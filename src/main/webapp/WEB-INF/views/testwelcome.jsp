<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Page</title>


<link rel="stylesheet" type="text/css"
	href="/SecureBankingGroup13/resources/css/test.css" />
</head>

<body>
	<div>
		<form:form method="POST" action="/securebankinggroup13/testsubmit"
			commandName="testmodel">
			<p class="test">Welcome to Spring MVC+Hibernate Project!!</p>
			<h3>Showing transaction Details from Hibernate Layer </h3></br>
			<h3>Transaction Source: ${transactionSource}</h3>
			<h3>Transaction Target: ${transactionTarget}</h3>
			<h3>Transaction ID : ${transactionId}</h3>
		
			
		</form:form>
	</div>
</body>
</html>