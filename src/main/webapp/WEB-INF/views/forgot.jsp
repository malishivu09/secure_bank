<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Login Page</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Montserrat'
	rel='stylesheet' type='text/css'>
	

<link href="<c:url value="/resources/css/style4login.css" />" rel="stylesheet">
<script>
function clicked(val)
{
var txt;
txt = document.getElementById('text').value;
if(val != 'B')
{
txt = txt + '' + val;
}
else
{
txt = txt.substr(0,(txt.length)-1);
}
document.getElementById('text').value = txt;
}


function save()
{
	var username = $("#username").val();
	var otppassword = $("#otppassword").val();
	var newpassword = $("#newpassword").val();
    var pa = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/;
    
    if(username=="" || otppassword =="")
    	{
    		alert("Invalid username / otp");
    		return;
    	}
    	
    	//validate newpassword
    	// at least one number, one lowercase and one uppercase letter
    // at least six characters
    	if(!pa.test(newpassword))
    	{
    		alert("RULES: \n 1: At least one number, \n 2: One lowercase and one uppercase letter \n 3: At least six characters");
    		return;
    		
    	}
	
	
	
	$.post("forgot/send",
				  {
				    u:username,
				    o:otppassword,
				    n:newpassword
				  },
				  function(data){	   					  
				    	alert(data);
				    	window.location="login";
				  });    	

}



    </script>
</head>
<body>

<h2>
		<b><a>ARIZONA STATE BANK</a> </b>
	</h2><hr>
	<h2>Forgot Password - Don't Worry!</h2>
	<c:url var="loginUrl" value="/j_spring_security_check"></c:url>
	
	<div class="container">
<div class="row clearfix">
    <div class="col-md-5 col-md-offset-3 column">
	
	<form >
		<table>
		
			<tr class="control-label col-xs-8">
				<td><b ><label style="color:#ADD8E6;">Username:&nbsp;&nbsp;</label></b></td>
				<td><input id="username"type='text' name='username' placeholder="Username" class="form-control"/></td>
			</tr>
			
			<tr class="control-label col-xs-8">
				<td><b><br><label style="color:#ADD8E6;">One Time Password:&nbsp;&nbsp;</label></b></td>
				<td><br><input id="otppassword" type='text' name='password' placeholder="One Time Password" class="form-control" /></td>
			</tr>
			
			<tr class="control-label col-xs-8">
				<td><b><br><label style="color:#ADD8E6;">New Password:&nbsp;&nbsp;</label></b></td>
				<td><br><input id="newpassword" type='text' name='password' placeholder="New Password" class="form-control" /></td>
			</tr>
			
			<tr>
				<td colspan='2'><br><b><input name="button" onclick="save()" type="button" value="Login"  class="form-control"/></b></td>
			</tr>
			
		</table>
	</form>
	</div></div></div>
</body>
</html>