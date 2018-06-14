<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Internal Employee</title>
</head>
<body>
<form border="1">
<div>
		Transfer FROM Account Number: <input type="text" id="accountNoFrom"></br> 
	
		Transfer TO Account Number: <input type="text" id="accountNoTo"></br> 
		Amount <input type="text" id="amountTo" ></br> 
		
		
		</form>
		<%-- Employee Name:${name} <br> Employee Phone:${phonenumber}<br>
		Employee user name:${username}<br> Employee address:${address}<br>
		Employee Email:${email}<br> --%>
		  	 
	</p>
	<button onclick="debit()">Debit</button></br> </br> 
	<button onclick="credit()">Credit</button></br> </br>  
	
	</div>
	<c:url var="logoutAction" value="/j_spring_security_logout"></c:url>

	<form action="${logoutAction}" method="post">
		<input type="submit" value="Logout" />
	</form>
</body>
</html>