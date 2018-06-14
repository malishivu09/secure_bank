<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false"%>	
	<hr>

		<div class="centerpanel" id="centralpanel" style="margin-left:100px;">
		<br>
		<table class="table table-bordered"data-toggle="table"  data-cache="false" data-height="299">
        <thead>
         <tr>
            <th data-field="name">Requester Account Number</th>
              <th data-field="name">Amount requested</th>
               <th data-field="name">Action</th>
        </tr>
       <c:forEach var="requestPayment" items="${listRequestPayment}" varStatus="status">
       		  <tr>
            <th data-field="id">${requestPayment.fromAccountNumber}</th>
            <th data-field="name">${requestPayment.amount}</th>
            <th data-field="price">
            	<ul>
            	<li role="presentation" ><a href="#" onclick="actionRequest(${requestPayment.fromAccountNumber},1,${requestPayment.amount},'${requestPayment.transaction_id }')">Approve</a></li>
  				<li role="presentation"><a href="#" onclick="actionRequest(${requestPayment.fromAccountNumber},0,${requestPayment.amount},'${requestPayment.transaction_id }')">Decline</a></li>
  			</ul>
  			</th>
        </tr>
       </c:forEach>

    </thead>
</table>