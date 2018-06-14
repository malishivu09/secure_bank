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
<link href='http://fonts.googleapis.com/css?family=Montserrat'
	rel='stylesheet' type='text/css' />

	
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
	function setViewDetails() {
//		window.location = "/SecureBankingGroup13/MerchantHome/viewMerchantDetails";
		$.get("MerchantHome/viewMerchantDetails", function(data){
			document.getElementById("centralpanel").innerHTML = data;
		});

		
	}
	function setEditDetails() {
	//	window.location = "/SecureBankingGroup13/MerchantHome/editMerchantDetails";
		$.get("MerchantHome/editMerchantDetails", function(data){
			document.getElementById("centralpanel").innerHTML = data;
		});
	
	}
	function setTransferLocation() {
		//window.location = "/SecureBankingGroup13/MerchantHome/transfer";
		$.get("MerchantHome/transferMerchant", function(data){
		//$.get("MerchantHome/transfer", function(data){
			document.getElementById("centralpanel").innerHTML = data;
		});
	}

	function setTransactionView() {
		//window.location = "/SecureBankingGroup13/MerchantHome/transactionView";
			$.get("MerchantHome/transactionView", function(data){
		//$.get("MerchantHome/transactionViewMerchant", function(data){
			document.getElementById("centralpanel").innerHTML = data;
		});
	}
	function setCredit() {
		$.get("MerchantHome/credit",function(data){
			document.getElementById("centralpanel").innerHTML = data;
		});
	}
		//window.location = "/SecureBankingGroup13/MerchantHome/credit";

	function setDebit() {
		$.get("MerchantHome/debit",function(data){
			document.getElementById("centralpanel").innerHTML = data;
		});
	}
	
	function requestPayment(){
		$.get("MerchantHome/requestPayment",function(data){
			document.getElementById("centralpanel").innerHTML = data;
		});
	}
	
	function submitrequestPayment()
	{
		
		var accountrequest = $("#accountrequest").val();
		var amountrequest = $("#amountrequest").val();
		

		//validations
		 var a = /^[0-9]{0,6}(\.[0-9]{1,2})?$/; 
	 	 var ac = /^[0-9]/;
	 	 
    	if (!a.test(amountrequest)) { 
    		alert("Please enter a valid amount (only two digits after decimal)");
    		return;
    		
    	} 
    	if (!ac.test(accountrequest)) { 
    		alert("Please enter a valid Account Number(only Numbers. NO characters)");
    		return;
    		
    	} 
    	
		$.post("MerchantHome/requestPayment/send",{payee_account_number:accountrequest,transfer_amount:amountrequst},function(data){
			alert(data);
			location.reload();
		});
	}
		
		
	
	function submitmerchantrequestpayment()
	{

		var requestaccount = $("#requestaccount").val();
		var requestamount = $("#requestamount").val();
		

		//validations
		 var a = /^[0-9]{0,6}(\.[0-9]{1,2})?$/; 
	 	 var ac = /^[0-9]/;
	 	 
    	if (!a.test(requestamount)) { 
    		alert("Please enter a valid amount (only two digits after decimal)");
    		return;
    		
    	} 
    	if (!ac.test(requestaccount)) { 
    		alert("Please enter a valid Account Number(only Numbers. NO characters)");
    		return;
    		
    	} 
    	
		$.post("MerchantHome/requestPayment/send",{payee_account_number:requestaccount,transfer_amount:requestamount},function(data){
			alert(data);
			location.reload();
		});
	
	
	}
		
	
	
	function save()
	{
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
	    	
	    	
		 //alert(phonenumber);
		 $.post("MerchantHome/editMerchantDetails/save",
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
	
	
	function actionmerchantTransfer()
	{

		var accountTransfer = $("#accountTransfer").val();
		var amountTransfer = $("#amountTransfer").val();
		

		//validations
		 var a = /^[0-9]{0,6}(\.[0-9]{1,2})?$/; 
	 	 var ac = /^[0-9]/;
	 	 
    	if (!a.test(amountTransfer)) { 
    		alert("Please enter a valid amount (only two digits after decimal)");
    		return;
    		
    	} 
    	if (!ac.test(accountTransfer)) { 
    		alert("Please enter a valid Account Number(only Numbers. NO characters)");
    		return;
    		
    	} 
    	
		$.post("MerchantHome/transfer/send",{payee_account_number:accountTransfer,transfer_amount:amountTransfer},function(data){
			alert(data);
			location.reload();
		});
	}
	
	function debitmerchantTransfer()
	{

		var withdrawTransfer = $("#withdrawTransfer").val();

		//validations
		 var a = /^[0-9]{0,6}(\.[0-9]{1,2})?$/; 
		
		
    	if (!a.test(withdrawTransfer)) { 
    		alert("Please enter a valid amount (only two digits after decimal)");
    		return;
    		
    	} 
		$.post("MerchantHome/debit/send",{debit_amount:withdrawTransfer},function(data){
			alert(data);
			location.reload();
		});
	}
	
	function creditmerchantTransfer()
	{

		var creditamountTransfer = $("#creditamountTransfer").val();
	
		//validations
		 var a = /^[0-9]{0,6}(\.[0-9]{1,2})?$/; 
	 	 
		
    	if (!a.test(creditamountTransfer)) { 
    		alert("Please enter a valid amount (only two digits after decimal)");
    		return;
    		
    	} 
		$.post("MerchantHome/credit/send",{amount_merchant:creditamountTransfer},function(data){
			alert(data);
			location.reload();
		});
	}

	
	
	
	
		//window.location = "/SecureBankingGroup13/MerchantHome/debit";
</script>
</head>
<body>
	<h2>
		<b><a>Secure Banking System</a> </b>
	</h2>
	<hr />
	
			
				

					<ul class="nav nav-pills" role="tablist">
  <li role="presentation" ><a href="#" 	onclick='setViewDetails()'>View Details</a></li>
					
			 <li role="presentation"><a href="#"	onclick='setEditDetails()'>Edit Details</a></li>
					
		 <li role="presentation"><a href="#" onclick='setTransferLocation()'>Transfer</a></li>
	<li role="presentation"><a href="#"  onclick='setDebit()'>Debit</a></li>
				 <li role="presentation"><a href="#" onclick='setCredit()'>Credit</a></li>
				 <li role="presentation"><a href="#" onclick='requestPayment()'>Payment</a></li>
			 <li role="presentation"><a onclick='setTransactionView()'>Transaction	View</a></li>
			 <li role="presentation"><a href="#">Authorize Requests</a></li>
			  
    <c:if test="${pageContext.request.userPrincipal.name != null}">
	
	<c:url var="logoutAction" value="/j_spring_security_logout"></c:url>
		
   <li role="presentation"><a href="${logoutAction}" >logout</a></li>
 
	</c:if>
  


	</ul>
		<hr>

		<hr>

		<div class="centerpanel" id="centralpanel"><b><br></>Merchant Dashboard</b>
		<br>
		<br>
						
	<br />
</body>
</html>