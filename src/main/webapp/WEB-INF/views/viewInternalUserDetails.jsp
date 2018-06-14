<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Internal Employee</title>
</head>
<body>
<h1> User Information</h1>
<ul class="list-group">
  <li class="list-group-item">Employee Name : ${name} </li>
  <li class="list-group-item">Phone : ${phonenumber}</li>
  <li class="list-group-item">Username : ${username}</li>
  <li class="list-group-item">Address : ${address} </li>
  <li class="list-group-item">Email : ${email}</li>
</ul>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
	<!-- Hi ${pageContext.request.userPrincipal.name}<br>-->
	<c:url var="logoutAction" value="/j_spring_security_logout"></c:url>
	
	<!--<form action="${logoutAction}" method="post">
		<input type="submit" value="Logout" />
	</form>-->
	</c:if>
</body>
</html>