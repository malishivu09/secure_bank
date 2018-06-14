<%@page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@page import="net.tanesha.recaptcha.ReCaptchaImpl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%-- <%@ page import="net.tanesha.recaptcha.ReCaptcha.*" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory.*" %> --%>
<html>
<head>
<title>Register User</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<script type="text/javascript">

function registerationclick()
{
	
	 var name = $("#name").val();
	 var phonenumber = $("#phonenumber").val();
	 var password = $("#password").val();
	 var username = $("#username").val();
	 var address= $("#address").val();
	 var email = $("#email").val();
	 var pii=$("#pii").val();
	 
	 var recaptcha_challenge_field =$("#recaptcha_challenge_field").val();
	 var recaptcha_response_field  = $("#recaptcha_response_field").val();
	 
	 //alert(recaptcha_response_field);
	 //alert(recaptcha_challenge_field);
	 
	 var re =  /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	 var ph = /^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{4}$/;
	 var ad = /^[a-zA-Z0-9\s,'-]*$/;
	 var na = /^[a-zA-Z ]{2,30}$/;
	 var pi = /^\d{7}$/;
		
	 
    	if (!re.test(email)) { 
    		alert("Please enter a valid email");
    		return;
    		
    	} 
    	
    	//validate phone
    	if(!ph.test(phonenumber))
    	{
    		alert("Please enter a valid 10 digit phone number");
    		return;
    		
    	}
    	
    	
    	//validate pii
    	if(!pi.test(pii))
    	{
    		alert("Please enter a valid 7 digit pii!");
    		return;
    		
    	}
    	
    	
    	//validate address
    	if(!ad.test(address))
    	{
    		alert("Please enter a valid address");
    		return;
    		
    	}
    	
    	
    	//validate name
	 if(!na	.test(name))
    	{
    		alert("Please enter a valid name (Minimum 2 characters and maximum 30 characters and only text)!");
    		return;
    		
    	}
    	
    	
    	$.post("user/add",
	   				  {
	   				    name:name,
	   				    username:username,
	   				    phonenumber:phonenumber,
	   				    address:address,
	   				    email:email,
	   				    password:password,
	   				    pii:pii,
	   				    recaptcha_challenge_field:recaptcha_challenge_field,
	   					recaptcha_response_field:recaptcha_response_field
	   				  },
	   				  function(data){	   					  
	   				    	alert(data);
	   				  });    
    
	return;	

	
	
	
	
}




</script>

</head>
<body style="">
<div class="center" >
	<h1>${message}</h1>
	
	
	
		<h2>
		<b><a style="margin-left:400px;">ARIZONA STATE BANK </a> </b>
	</h2><hr>
	<h2><U><B style="margin-left:400px;">Register User</B></U></h2>
<br>
<div style="margin-left:400px;">
	<c:url var="addAction" value="/user/add"></c:url>

	<form:form commandName="user" class="form-horizontal" name="form" >
		<table>
			
 			<tr>   
				<td><form:label path="name">
						<spring:message text="Name" />
					</form:label></td>
				<td><input id="name" type="text" path="name" name="name" class="form-control" /></td>
			</tr>
			
		
			<tr>   
				<td><form:label path="email">
						<spring:message text="Email" />
					</form:label></td>
				<td><input id="email" type="email" path="email" name="email"class="form-control" /></td>
			</tr>
		
			
			<tr>   
				<td><form:label path="address">
						<spring:message text="Address" />
					</form:label></td>
				<td><input id="address" type="text"path="address" name="address"class="form-control" /></td>
			</tr>
			
			
			
			
			<tr>   
				<td><form:label path="phoneNumber">
						<spring:message text="Phone Number:" />
					</form:label></td>
				<td><input id="phonenumber" "path="phoneNumber" name="phoneNumber"class="form-control" /></td>
			</tr>
			
			<tr>   
				<td><form:label path="username">
						<spring:message text="Username" />
					</form:label></td>
				<td><input id="username" path="username"  type="text" name="username" class="form-control" /></td>
			</tr>
			
			<tr>
			
				<td><form:label path="password">
						<spring:message text="Password" />
					</form:label></td>
				<td><input id="password" type="password" class="form-control" name="password" path="password"/></td>
			</tr>	
			
				<tr>   
				<td><form:label path="pii">
						<spring:message text="pii" />
					</form:label></td>
				<td><input id="pii" path="pii"  type="text" name="pii" class="form-control" /></td>
			</tr>
			
			
			
			<div style="margin-left:100px;">
			<br>
			<tr>
			<td><br>
			
	<%-- 		<%
			ReCaptcha c;
			if(request.isSecure()){ 
				c = ReCaptchaFactory.newSecureReCaptcha("6LdlDf0SAAAAAB6IK4Hh2LuY-zr-I9cw8FfgfRVp", "6LdlDf0SAAAAAIgzUE78UcILjezztVP0jPGXCQQ0", true);
			 	((ReCaptchaImpl) c).setRecaptchaServer("https://www.google.com/recaptcha/api");
         		out.print(c.createRecaptchaHtml(null, null));
			}else{
				   c = ReCaptchaFactory.newReCaptcha("6LdlDf0SAAAAAB6IK4Hh2LuY-zr-I9cw8FfgfRVp", "6LdlDf0SAAAAAIgzUE78UcILjezztVP0jPGXCQQ0", true);
				   ((ReCaptchaImpl) c).setRecaptchaServer("http://www.google.com/recaptcha/api");
			}
			out.print(c.createRecaptchaHtml(null, null));
        	%>
	 --%>		</td></tr>
			<tr>
				<td colspan="2">
				 <div class="col-xs-6">
				 <br>
						<input  style="background-color:cyan;"type="button" class="form-control" onclick="registerationclick()"value="<spring:message text="Register User"/>" />
				 </div>
				</td>
			</tr></div>
		</table>
	</form:form>
	<br>
	</div>
</div>

</body>
</html>
