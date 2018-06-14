<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Montserrat'
	rel='stylesheet' type='text/css'>
	
<script type="text/javascript">


 //VIEW USERS
 function view(){
	 $.get( "AdminHome/viewAdminUserDetails", function( data ) {
		 
			document.getElementById("centralpanel").innerHTML=data;
			});
	 }
 ///MANAGE
 function manageInternalUser(){
	 $.get("AdminHome/manageInternalUser", function( data ) {
		 
			document.getElementById("centralpanel").innerHTML=data;
			});
	 }
 //ADD INTERNAL
 

 function  addInternalUser(){
		document.getElementById("centralpanel").innerHTML=null;
		// document.getElementById("centralpanel").innerHTML = location.href='viewInternalUserDetails';
		$.get("AdminHome/addInternalUser", function( data ) {
			 
			document.getElementById("centralpanel").innerHTML=data;
			});
		 }
 function saveInternalUser(){
		//should call backend server to save user details	
			var username =$("#username").val();
			var password =$("#password").val();
			
			var publickey = $("#publickey").val();
			var email = $("#email").val();
			var name = $("#name").val();
			var address = $("#address").val();
			var phonenumber =$("#phonenumber").val();
	
		 $.post("AdminHome/addInternalUser",
				  {
			 username:username,
			 password:password,
			 publickey:publickey,
				    email:email,
				    name: name,
				    address:address,
				    phonenumber:phonenumber
				    
				  },
				  function(data){
				    alert(data);
				  });
	}
 //EDIT
 function edit(){
		document.getElementById("centralpanel").innerHTML=null;
		// document.getElementById("centralpanel").innerHTML = location.href='viewInternalUserDetails';
		$.get( "AdminHome/editAdminUserDetails", function( data ) {
			 
			document.getElementById("centralpanel").innerHTML=data;
			});
		 }
 
 

 
 //SAVE
 function save(){
 	//should call backend server to save user details	
 	 
 
 	
 	 var name = $("#name").val();
 	 var phonenumber = $("#phonenumber").val();
 	 var address= $("#address").val();
 	 var email = $("#email").val();
 	
 	 var re =  /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	 var ph = /^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{4}$/;
	 var ad = /^[a-zA-Z0-9\s,'-]*$/;
	 var na = /^[a-zA-Z ]{2,30}$/;
	 
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
    	
    	
 	 
 	 $.post("AdminHome/editAdminUserDetails",
 			  {
 			    name:name,
 			    phonenumber:phonenumber,
 			    address:address,
 			    email:email
 			  },
 			  function(data){
 			    alert(data);
 			  });
 	 
 	 
 	 
 	 
 }
//external user request
function externalUserRequest(){
	$.get("AdminHome/externalUserRequest", function(data){
		//set centralPanel content
		document.getElementById("centralpanel").innerHTML=data;
	});
}
//approve externalUserRequest
function actionExternalUser(u,f){
	
	var role = $('#'+u).val();
	alert(role); //get role
	
	$.post("AdminHome/actionExternalUser",{user:u, flag:f,role:role},function(data){
		alert(data);
		location.reload();
	});
}
//approve internalUserRequest
function actionInternalUser(i,e,f,a){
	$.post("AdminHome/actionInternalUser",{internal:i,external:e,flag:f,atype:a},function(data){
		alert(data);
		location.reload();
	});
}

function internalUserRequest(){
	$.get("AdminHome/internalUserRequest", function(data){
		document.getElementById("centralpanel").innerHTML=data;
	});
}

function getPendingTransactions(){
	$.get("AdminHome/pendingTransactions", function(data){
		document.getElementById("centralpanel").innerHTML=data;
	});
}
function getPIIInformation(){
	$.get("AdminHome/piiInformation", function(data){
		document.getElementById("centralpanel").innerHTML=data;
	});
}

function actionTransaction(t,f){
	$.post("AdminHome/actionTransaction",{id:t,flag:f},function(data){
		alert(data);
		location.reload();
	});
}
function generateLogFile(){
	$.post("AdminHome/downloadLogFile",function(data){
		document.getElementById("centralpanel").innerHTML=data;
	});
}


 
</script>
 <c:url var="logoutAction" value="/j_spring_security_logout"></c:url>
</head>
<body>
	<h2>
		<b><a>Secure Banking System - Admin User Home</a> </b>
	</h2>
	<ul class="nav nav-pills" role="tablist">
  <li role="presentation" ><a href="#" onclick="view()">View Details</a></li>
  <li role="presentation"><a href="#" onclick="edit()">Edit Details</a></li>
    <li role="presentation"><a href="#" onclick="externalUserRequest()">External User Requests</a></li>
    <li role="presentation"><a href="#" onclick="internalUserRequest()">Internal User Requests</a></li>
    <li role="presentation"><a href="#" onclick="manageInternalUser()">Manage Internal User</a></li>
    <li role="presentation"><a href="#" onclick="getPendingTransactions()">Transaction Pending</a></li>
  <li role="presentation"><a href="#" onclick="generateLogFile()">Log File</a></li>    
    <c:if test="${pageContext.request.userPrincipal.name != null}">
	
	<c:url var="logoutAction" value="/j_spring_security_logout"></c:url>
		
   <li role="presentation"><a href="${logoutAction}" >Logout</a></li>
 
	</c:if>
  
</ul>

	
	<hr>

		<div class="centerpanel" id="centralpanel"><b><br></>Administrator Dashboard</b>
	<br>
	<br>
</body>
</html>