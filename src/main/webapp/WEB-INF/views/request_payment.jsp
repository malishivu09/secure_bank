<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js">
$(document).ready(function() {
    $('#integerForm').bootstrapValidator();
});
</script>
</head>
<body>

<c:url var="transferUrl" value="/MerchantHome/requestPayment/send"></c:url>

 <form:form class="navbar-form navbar-left" role="search" action ="${transferUrl}" commandName="amount">
  <div class="form-group">
    <input  id="requestaccount" type="text" class="form-control" name="payee_account_number" placeholder="Enter account number of user"/>
    <input  id="requestamount" type="text" class="form-control" name="transfer_amount" placeholder="Enter amount to be paid"/>
  </div>
  <button type="button" onclick="submitmerchantrequestpayment()"class="btn btn-default">Submit</button>
</form:form>
 
</body>
</html>