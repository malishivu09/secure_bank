<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Merchant Details</title>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>
</head>
<body>

	<form  class="form-horizontal">

    <div class="form-group">
        <label for="inputEmail" class="control-label col-xs-2">Name</label>
        <div class="col-xs-3">
            <input type="text" name="name" id="name" class="form-control"  placeholder="Name" value=${userDetails.name} >
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword" class="control-label col-xs-2">Phone Number</label>
        <div class="col-xs-3">
            <input   class="form-control" name="phonenumber" id="phonenumber" placeholder="phone" value=${userDetails.phoneNumber} >
        </div>
    </div>
    <div class="form-group">
        <label for="address" class="control-label col-xs-2">Address</label>
        <div class="col-xs-3">
            <input type="text" class="form-control" name="address" id="address" placeholder="Address" value=${userDetails.address} >
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="control-label col-xs-2">Email</label>
        <div class="col-xs-3">
            <input type="email" name="email" class="form-control"  id="email" placeholder="email" value=${userDetails.email} >
        </div>
    </div>
 <div class="form-group">
        <label  class="control-label col-xs-3"></label>
        <div class="col-xs-1">
            <input type="button" class="form-control"  onclick="save()"value="Save">
        </div>
    </div>
    
			<%-- <tr>
				<td>Name :</td>
				<td><input type="text" id="name" value = "${userDetails.name}"></td>
			</tr>
			<tr>
				<td>Address :</td>
				<td><input type="text" id="address" value = "${userDetails.address}"></td>
			</tr>
			<tr>
				<td>Phone Number :</td>
				<td><input type="text" id="phonenumber" value = "${userDetails.phoneNumber}"></td>
			</tr>
			<tr>
				<td>e-mail id :</td>
				<td><input type="text" id="email"  value = "${userDetails.email}" ></td>
			</tr> --%>

			
	</form>
	<%-- <c:url var="logoutAction" value="/j_spring_security_logout"></c:url>

	<form action="${logoutAction}" method="post">
		<input type="submit" value="Logout" />
	</form> --%>

</body>
</html>