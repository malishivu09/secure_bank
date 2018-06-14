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
            <th data-field="name">Name</th>
              <th data-field="name">User Name</th>
              <th data-field="name">Phone Number</th>
               <th data-field="name">Email</th>           
              
               <th data-field="name">Action</th>
        </tr>
       <c:forEach var="user" items="${users}" varStatus="status">
       		  <tr>
            <th data-field="id">${user.name}</th>
              <th data-field="username">${user.username}</th>
            <th data-field="name">${user.phoneNumber}</th>
              <th data-field="name">${user.email}</th>
               
            <th data-field="price">
            	<ul>
            	<li role="presentation" ><a href="#" onclick="actionDelete(${user.username},1)">delete</a></li>
  				
  			</ul>
  			</th>
        </tr>
       </c:forEach>

    </thead>
</table>