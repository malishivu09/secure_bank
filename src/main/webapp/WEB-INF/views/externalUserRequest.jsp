<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>External User Requests</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
 <script type="text/javascript">


 </script>
</head>
<body>
<form class="form-horizontal">
    <div class="form-group">
        <label for="inputEmail" class="control-label col-xs-2">Name</label>
        <div class="col-xs-10">
            <input type="text" id="name" class="form-control" id="inputEmail" placeholder="Name" value=${name}>
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword" class="control-label col-xs-2">Phone Number</label>
        <div class="col-xs-10">
            <input type="text" class="form-control" id="phonenumber" placeholder="phone" value=${phonenumber} >
        </div>
    </div>
    <div class="form-group">
        <label for="address" class="control-label col-xs-2">Address</label>
        <div class="col-xs-10">
            <input type="text" class="form-control" id="address" placeholder="phone" value=${address}>
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="control-label col-xs-2">Email</label>
        <div class="col-xs-10">
            <input type="email" class="form-control" id="email" placeholder="email" value=${email}>
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="control-label col-xs-2">Save</label>
        <div class="col-xs-10">
            <input type="button" class="form-control"  placeholder="email"  onclick="save()" value="Save">
        </div>
    </div>
    
</form>
	<form action="${logoutAction}" method="post">
		<input type="submit" value="Logout" />
	</form>
</body>
</html><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Edit Admin Details</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
 <script type="text/javascript">


 </script>
</head>
<body>
<form class="form-horizontal">
    <div class="form-group">
        <label for="inputEmail" class="control-label col-xs-2">Name</label>
        <div class="col-xs-10">
            <input type="text" id="name" class="form-control" id="inputEmail" placeholder="Name" value=${name}>
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword" class="control-label col-xs-2">Phone Number</label>
        <div class="col-xs-10">
            <input type="text" class="form-control" id="phonenumber" placeholder="phone" value=${phonenumber} >
        </div>
    </div>
    <div class="form-group">
        <label for="address" class="control-label col-xs-2">Address</label>
        <div class="col-xs-10">
            <input type="text" class="form-control" id="address" placeholder="phone" value=${address}>
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="control-label col-xs-2">Email</label>
        <div class="col-xs-10">
            <input type="email" class="form-control" id="email" placeholder="email" value=${email}>
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="control-label col-xs-2">Save</label>
        <div class="col-xs-10">
            <input type="button" class="form-control"  placeholder="email"  onclick="save()" value="Save">
        </div>
    </div>
    
</form>
	<form action="${logoutAction}" method="post">
		<input type="submit" value="Logout" />
	</form>
</body>
</html>