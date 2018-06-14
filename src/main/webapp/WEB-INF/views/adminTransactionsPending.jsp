<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false"%>	
	<hr>

		<div class="centerpanel" id="centralpanel" style="margin-left:100px;"><b><br></>Critical Transaction</b>
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
               <th data-field="name">Action</th>
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
            	<ul>
            	<li role="presentation" ><a href="#" onclick="actionTransaction('${transaction.transactionId}',1)">Approve</a></li>
  				<li role="presentation"><a href="#" onclick="actionTransaction('${transaction.transactionId}',0)">Decline</a></li>
  			</ul>
  			</th>
        </tr>
       </c:forEach>

    </thead>
</table>