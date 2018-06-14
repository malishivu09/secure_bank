<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Edit Details</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
 <script type="text/javascript">


 </script>
</head>
<body>
<!-- String username = request.getParameter("email");
			String password = request.getParameter("password");
			boolean enabled = true;
			int pii = 233;
			String publicKey =  request.getParameter("publickey");
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phoneNumbe -->
			
			
<form class="form-horizontal">
 <div class="form-group">
 
  <div class="form-group">
        <label for="name" class="control-label col-xs-2">name</label>
        <div class="col-xs-3">
            <input type="name" class="form-control" id="name" placeholder="name">
        </div>
    </div>
        <label for="username" class="control-label col-xs-2">Username :</label>
        <div class="col-xs-3">
            <input type="text" id="username" class="form-control" id="username" placeholder="username" >
        </div>
    </div>
    
    <div class="form-group">
        <label for="password" class="control-label col-xs-2">password :</label>
        <div class="col-xs-3">
            <input type="password" id="password" class="form-control" id="password" placeholder="password" >
        </div>
    </div>
        <div class="form-group">
        <label for="publickey" class="control-label col-xs-2">publickey :</label>
        <div class="col-xs-3">
            <input type="text" id="publickey" class="form-control" id="publickey" placeholder="publickey" >
        </div>
    </div>
    <div class="form-group">
        <label for="inputEmail" class="control-label col-xs-2">Email</label>
        <div class="col-xs-3">
            <input type="text" id="email" class="form-control" id="email" placeholder="email" >
        </div>
    </div>
    <div class="form-group">
        <label for="phonenumber" class="control-label col-xs-2">Phone Number</label>
        <div class="col-xs-3">
            <input type="text" class="form-control" id="phonenumber" placeholder="phonenumber" >
        </div>
    </div>
    <div class="form-group">
        <label for="address" class="control-label col-xs-2">Address</label>
        <div class="col-xs-3">
            <input type="text" class="form-control" id="address" placeholder="address" >
        </div>
    </div>
   
    <div class="form-group">
        <label for="email" class="control-label col-xs-2">Add Internal User</label>
        <div class="col-xs-3">
            <input type="button" class="form-control"  placeholder="email"  onclick="saveInternalUser()">
        </div>
    </div>
    
</form>
	<form action="${logoutAction}" method="post">
		<input type="submit" value="Logout" />
	</form>
</body>
</html>