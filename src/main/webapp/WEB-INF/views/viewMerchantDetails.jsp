<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<h3>View Merchant Details</h3>
	<c:if test="${!empty userDetails}">
		<table class="tg">
			<tr>
				<td>Name :</td>
				<td>${userDetails.name}</td>
			</tr>
			<tr>
				<td>Address :</td>
				<td>${userDetails.address}</td>
			</tr>
			<tr>
				<td>Phone Number :</td>
				<td>${userDetails.phoneNumber}</td>
			</tr>
			<tr>
				<td>e-mail id :</td>
				<td>${userDetails.email}</td>
			</tr>
		</table>
	</c:if>
</body>
</html>