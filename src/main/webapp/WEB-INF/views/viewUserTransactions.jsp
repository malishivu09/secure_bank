<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false"%>	
	<hr>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Internal Employee</title>
</head>
<body>

<h1>Select User</h1>
<sricpt type="text/javascript">
</sricpt>
<h1> User Transactions</h1>
	<div class="centerpanel" id="centralpanel" style="margin-left:100px;"><b><br></>View user transactions</b>
		<br>
		<table class="table table-bordered"data-toggle="table"  data-cache="false" data-height="299">
        <thead>
         <tr>
            <th data-field="name">Transaction ID</th>
              <th data-field="name">Source Account</th>
               <th data-field="name">Target Account</th>           
               <th data-field="name">Transaction Amount</th>
               <th data-field="name">Transaction Type</th>
               <th data-field="name">Transaction Time</th>
           
        </tr>
       <c:forEach var="transaction" items="${transactions}" varStatus="status">
       		  <tr>
            <th data-field="id">${transaction.transactionId}</th>
            <th data-field="name">${transaction.accountByAccountSource.accountNumber}</th>
              <th data-field="name">${transaction.accountByAccountTarget.accountNumber}</th>
               <th data-field="name">${transaction.transactionAmount}</th>
               <th data-field="name">${transaction.transactionType}</th>
               <th data-field="name">${transaction.transactionTime}</th>
            <th data-field="price">
            	
  			</th>
  			</tr>
  			</c:forEach>
  			

    </thead>
</table>
   </br>
   </div>
   
	<c:if test="${pageContext.request.userPrincipal.name != null}">
<%-- 	Hi ${pageContext.request.userPrincipal.name} --%><br>
	<c:url var="logoutAction" value="/j_spring_security_logout"></c:url>
	
	<form action="${logoutAction}" method="post">
		<input type="submit" value="Logout" />
	</form>
	</c:if>
</body>
</html>