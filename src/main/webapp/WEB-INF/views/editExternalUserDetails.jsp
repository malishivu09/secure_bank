<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

<title>Insert title here</title>
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

<script style="text/javascript">
(function($,W,D)
{
    var JQUERY4U = {};

    JQUERY4U.UTIL =
    {
        setupFormValidation: function()
        {
            //form validation rules
            $("#form").validate({
                rules: {
                    name: "required",
                    address: "required",
                    email: {
                        required: true,
                        email: true
                    },
                    password: {
                        required: true,
                        minlength: 5
                    },
                   
                },
                messages: {
                    name: "Please enter your name",
                    address: "Please enter your address",
                    password: {
                        required: "Please provide a password",
                        minlength: "Your password must be at least 5 characters long"
                    },
                    email: "Please enter a valid email address",
                    
                },
                submitHandler: function(form) {
                    form.submit();
                }
            });
        }
    }

    //when the dom has loaded setup form validation rules
    $(D).ready(function($) {
        JQUERY4U.UTIL.setupFormValidation();
    });

})(jQuery, window, document);


</script>
</head>
<body>
	<c:url var="addAction" value="/UserHome/editExternalUserDetails/save"></c:url>

	<form id="form"action="${addAction}" method="post" commandName="userDetails" class="form-horizontal"  name="form" method="post">


		 <div class="form-group">
		<label for="name" path="name" class="control-label col-xs-2"><spring:message text="Name : " /></label>
			 <div class="col-xs-3">
				<input path="name" id="name"value="${userDetails.name}" class="form-control" placeholder="name"required />
			</div>
		</div>	
		
		 <div class="form-group">
				<label for="address" path="address" class="control-label col-xs-2">	<spring:message text="Address : " /></label>
			 <div class="col-xs-3">
				<input path="address" id="address" value="${userDetails.address}" class="form-control" placeholder="Address"  required/>
			 </div>
		</div>	 
			
		 <div class="form-group">
		 		<label for="phoneNumber" path="phoneNumber" class="control-label col-xs-2"><spring:message text="Phone Number : " /></label>	
		 	 <div class="col-xs-3">	
		 		<input type="number" min="0" id="phonenumber" path="phoneNumber"value="${userDetails.phoneNumber}" class="form-control" placeholder="Phone"  required/>
			</div>
		</div>
		<div class="form-group">
			<label for="email" path="email" class="control-label col-xs-2"><spring:message text="E-mail id :" /></label>
			<div class="col-xs-3">		
				<input type="email" id="email" path="email" value="${userDetails.email}" class="form-control" placeholder="Email" required/>
			</div>
		</div>	
		 <div class="form-group">
        <label  class="control-label col-xs-3"></label>
        <div class="col-xs-1">	
				<input type="button" class="form-control" onclick="save()" value="save"/>
		</div>
		</div>
		
	</form>
	<%-- <c:url var="logoutAction" value="/j_spring_security_logout"></c:url>

	<form action="${logoutAction}" method="post">
		<input type="submit" value="Logout" />
	</form> --%>

</body>
</html>