<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	function setViewDetails() {
		
		$.get("UserHome/viewExternalUserDetails", function(data){
			document.getElementById("centralpanel").innerHTML = data;
		});
		
		//window.location = "/SecureBankingGroup13/UserHome/viewExternalUserDetails";
	}
	
	
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
	    	
	    	
	    	$.post("UserHome/editExternalUserDetails/save",
		   				  {
		   				    name:name,
		   				    phonenumber:phonenumber,
		   				    address:address,
		   				    email:email
		   				  },
		   				  function(data){
		   				    alert(data);
		   				  });    	
	    
		return;	
	}

	
	function setEditDetails() {
		$.get("UserHome/editExternalUserDetails",function(data){
				document.getElementById("centralpanel").innerHTML = data;
		});
		//window.location = "/SecureBankingGroup13/UserHome/editExternalUserDetails";
	}
	function setTransferLocation() {
		document.getElementById("centralpanel").innerHTML = "<img src='resources/images/loader.gif' width='20%'/>";
		$.get("UserHome/transfer",function(data){
			document.getElementById("centralpanel").innerHTML = data;
		});
		//window.location = "/SecureBankingGroup13/UserHome/transfer";
	}

	function setTransactionView()
	{
		$.get("UserHome/transactionView",function(data){
			document.getElementById("centralpanel").innerHTML = data;
		});
		//window.location = "/SecureBankingGroup13/UserHome/transactionView";
	}

	function view() {
		// document.getElementById("centralpanel").innerHTML = location.href='viewInternalUserDetails';
		$.get("UserHome/viewInternalUserDetails", function(data) {

			document.getElementById("centralpanel").innerHTML = data;
		});
	}
	function setCredit() {
		$.get("UserHome/credit", function(data){
			document.getElementById("centralpanel").innerHTML = data;
		});
		//window.location = "/SecureBankingGroup13/UserHome/credit";
	}
	function setDebit() {
		$.get("UserHome/debit",function(data){
			document.getElementById("centralpanel").innerHTML = data;
		});
		//window.location = "/SecureBankingGroup13/UserHome/debit";
	}
		
		function setRequestPayment() {
		$.get("UserHome/requestPayment",function(data){
			document.getElementById("centralpanel").innerHTML = data;
		});
	}


		function actionTransfer()
		{

			var account = $("#account").val();
			var amount = $("#amount").val();
			var otp = $("#otp").val();	
		
			//validations
			 var a = /^[0-9]{0,6}(\.[0-9]{1,2})?$/; 
		 	 var ac = /^[0-9]/;
		 	 
	    	if (!a.test(amount)) { 
	    		alert("Please enter a valid amount (only two digits after decimal)");
	    		return;
	    		
	    	} 
	    	if (!ac.test(account)) { 
	    		alert("Please enter a valid Account Number(only Numbers. NO characters)");
	    		return;
	    		
	    	} 
	    	
			$.post("UserHome/transfer/send",{payee_account_number:account,transfer_amount:amount,otp:otp},function(data){
				alert(data);
				location.reload();
			});
		}
		
		function debitTransfer()
		{

			var withdraw = $("#withdraw").val();
		
			//validations
			 var a = /^[0-9]{0,6}(\.[0-9]{1,2})?$/; 
		
	    	if (!a.test(withdraw)) { 
	    		alert("Please enter a valid amount (only two digits after decimal)");
	    		return;
	    		
	    	} 
			$.post("UserHome/debit/send",{debit_amount:withdraw},function(data){

				if(data.indexOf("upload")>=0){
					document.getElementById("centralpanel").innerHTML=data;
				}else{
					alert(data);
					location.reload();
				}
				
			});
		}
		
		function creditTransfer()
		{

			var creditamount = $("#creditamount").val();
		
			//validations
			 var a = /^[0-9]{0,6}(\.[0-9]{1,2})?$/; 
		 	 
		 	 
	    	if (!a.test(creditamount)) { 
	    		alert("Please enter a valid amount (only two digits after decimal)");
	    		return;
	    		
	    	} 
			$.post("UserHome/credit/send",{amount:creditamount},function(data){
				alert(data);
				location.reload();
			});
		}

		

		function actionRequest(t,f,a,i){
			$.post("UserHome/requestAction",{id:t,flag:f,amount:a,transaction:i},function(data){
				document.getElementById("centralpanel").innerHTML = data;
			});
		}
				
</script>
</head>
<body>
<h2>
		<b><a>Secure Banking System - External User Home</a> </b>
	</h2>
	<ul class="nav nav-pills" role="tablist">
  <li role="presentation" ><a href="#" onclick="setViewDetails()">View Details</a></li>
  <li role="presentation"><a href="#" onclick="setEditDetails()">Edit Details</a></li>
    <li role="presentation"><a href="#" onclick="setTransferLocation()">Transfer</a></li>
    <li role="presentation"><a href="#" onclick="setDebit()">Debit</a></li>
    <li role="presentation"><a href="#" onclick="setCredit()">Credit</a></li>
     <li role="presentation"><a href="#" onclick="setRequestPayment()">Payment</a></li>
    <li role="presentation"><a href="#" onclick="setTransactionView()">Transaction</a></li>
 
	 
    <c:if test="${pageContext.request.userPrincipal.name != null}">
	
	<c:url var="logoutAction" value="/j_spring_security_logout"></c:url>
		
   <li role="presentation"><a href="${logoutAction}" >logout</a></li>
 
	</c:if>
  


</ul>
	
	<hr>

		<div class="centerpanel" id="centralpanel" style="margin-left:50px;"><b><br></>User Dashboard</b>
	<br>
	<br>

</body>
</html>