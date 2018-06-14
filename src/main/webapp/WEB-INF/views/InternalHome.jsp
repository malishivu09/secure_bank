
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Montserrat'
	rel='stylesheet' type='text/css'>
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
	// document.getElementById("centralpanel").innerHTML = location.href='viewInternalUserDetails';
	 $.get( "InternalHome/viewInternalUserDetails", function( data ) {
		 
		document.getElementById("centralpanel").innerHTML=data;
		});
	 }
 //EDIT
 function edit(){
		document.getElementById("centralpanel").innerHTML=null;
		// document.getElementById("centralpanel").innerHTML = location.href='viewInternalUserDetails';
		$.get( "InternalHome/editInternalUserDetails", function( data ) {
			 
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
    	
    	
	 
	 $.post("InternalHome/editInternalUserDetails",
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

 //PERFORM
 function perform(){
	//should call backend server to save user details	
	document.getElementById("centralpanel").innerHTML=null;
	
	 $.get( "InternalHome/performTransactionInternalUser", function( data ) {
		 
			document.getElementById("centralpanel").innerHTML=data;
			});
}
 //view 
 function viewRequest(){
		//should call backend server to save user details	
	 $.get( "InternalHome/requestFromInternalUser", function( data ) {
		 
			document.getElementById("centralpanel").innerHTML=data;
			});
	}
 //DEBIT
 function debit(){
	//should call backend server to save user details	
	 var accountNo = $("#accountNo").val();
	 var amount = $("#amount").val();
	var type = "debit";
	 $.post("InternalHome/performTransactionInternalUser",
			  {
			    accountNo:accountNo,
			    amount:amount,
			    type:type
			  },
			  function(data){
			    alert(data);
			  });
}
 //CREDIT
 function credit(){
		//should call backend server to save user details	
		 var accountNo = $("#accountNo").val();
		 var amount = $("#amount").val();
		var type = "credit";
		 $.post("InternalHome/performTransactionInternalUser",
				  {
				    accountNo:accountNo,
				    amount:amount,
				    type:type
				  },
				  function(data){
				    alert(data);
				  });
	}
 
 //REQUEST ACCESS
 function  performAction(){
		//should call backend server to save user details	
		
		 $.get("InternalHome/requestFromInternalUser", function( data ) 
				  {
			 document.getElementById("centralpanel").innerHTML=data;
				 
				 
				  });
	}
 function  sendRequest(){
		//should call backend server to save user details	
		 var username = $("#username").val();
		 var requestType = $("#requestType option:selected").text();
		 
		 $.post("InternalHome/requestFromInternalUser",
				  {
					 username:username,
				    requestType:requestType
				  },
				  function(data){
				    alert(data);
				  });
	}
//VIEW USER DETAILS WHICH YOU HAVE PERMISSION

function viewUserDetails(){
	//should call backend server to save user details	
 		$.get( "InternalHome/selectUser", function( data ) {

		document.getElementById("centralpanel").innerHTML=data;
		});
}

function viewUser(){
	//should call backend server to save user details	
	var username = $("#ddlViewBy option:selected" ).text();
		//alert(username);

 		$.post("InternalHome/viewUserDetails",
		  {
	 			username:username   
		  },
		  function(data){
				document.getElementById("centralpanel").innerHTML=data;
		
		  });
}


//VIEW TRANSACTIONS

function viewTransaction(){
	//should call backend server to save user details	
	var username = $("#ddlViewBy option:selected" ).text();
		//alert(username);
		
	var accountNo = $("#ddlViewBy option:selected" ).text();
	alert(accountNo);

		$.post("InternalHome/viewUserTransactions",
	  {
			accountNo:accountNo  
	  },
	  function(data){
			document.getElementById("centralpanel").innerHTML=data;
	  });
}

function viewUserTransactions(){
	//should call backend server to save user details	
	$.get( "InternalHome/selectUser", function( data ) {

		document.getElementById("centralpanel").innerHTML=data;
		});
	/*  */
}

</script>
</head>
<body>
	<h2>
		<b><a>Secure Banking System</a> </b>
	</h2>
	<ul class="nav nav-pills" role="tablist">
  <li role="presentation" ><a href="#" onclick="view()">View Details</a></li>
  <li role="presentation"><a href="#" onclick="edit()">Edit Details</a></li>
<!--   <li role="presentation"><a href="#" onclick="perform()">Perform Transaction</a></li> -->
  <li role="presentation"><a href="#" onclick="performAction()">Request Access</a></li>
  <li role="presentation"><a href="#" onclick="viewUserDetails()">View User Details</a></li>
   <li role="presentation"><a href="#" onclick="viewUserTransactions()">View User Transactions</a></li>
    
    <c:if test="${pageContext.request.userPrincipal.name != null}">
	
	<c:url var="logoutAction" value="/j_spring_security_logout"></c:url>
		
   <li role="presentation"><a href="${logoutAction}" >logout</a></li>
 
	</c:if>
  
  
  
</ul>  
	<hr>
		<div class="centerpanel" id="centralpanel"><b>InternalUser Dashboard</b></div>

	<br>
	<br>
</body>
</html>