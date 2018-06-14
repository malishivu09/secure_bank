<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Edit Details</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
 <script >

 $(document).ready(function() {
	    $('#integerForm').bootstrapValidator();
	});
 </script>
</head>
<body>
<form class="form-horizontal"  name="form" method="post">
    <div class="form-group">
        <label for="inputEmail" class="control-label col-xs-2">Name</label>
        <div class="col-xs-3">
            <input type="text" name="name" id="name" class="form-control" id="inputEmail" placeholder="Name" value=${name} required>
        </div>
    </div>
    
    <div class="form-group">
        <label for="inputPassword" class="control-label col-xs-2">Phone Number</label>
        <div class="col-xs-3">
            <input type="number" min="0" class="form-control" name="phonenumber"id="phonenumber" placeholder="Phone" value=${phonenumber} required>
        </div>
    </div>
    
      
    
    <div class="form-group">
        <label for="address" class="control-label col-xs-2">Address</label>
        <div class="col-xs-3">
            <input type="text" class="form-control" name="address" id="address" placeholder="Address" value=${address} required>
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="control-label col-xs-2">Email</label>
        <div class="col-xs-3">
            <input type="email" class="form-control" name="email" id="email" placeholder="Email" value=${email} required>
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="control-label col-xs-3"></label>
        <div class="col-xs-1">
            <input type="button" class="form-control"  placeholder="email"   onclick="save()"value="Save">
        </div>
    </div>
    

</body>
</html>