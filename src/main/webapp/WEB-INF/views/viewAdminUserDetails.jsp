<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Montserrat'
	rel='stylesheet' type='text/css'>


<title>Admin</title>
</head>
<body>
<h1> Admin Information</h1>

<ul class="list-group">
  <li class="list-group-item">Employee Name : ${name} </li>
  <li class="list-group-item">Phone : ${phonenumber}</li>
  <li class="list-group-item">Username : ${username}</li>
  <li class="list-group-item">Address : ${address} </li>
  <li class="list-group-item">Email : ${email}</li>
</ul>
</body>
</html>