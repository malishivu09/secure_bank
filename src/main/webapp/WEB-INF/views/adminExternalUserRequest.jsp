<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false"%>	
	<hr>

		<div class="centerpanel" id="centralpanel" style="margin-left:100px;"><b><br></>External User Requests</b>
		<br>
		<table class="table table-bordered"data-toggle="table"  data-cache="false" data-height="299">
        <thead>
         <tr>
            <th data-field="id">Username</th>
            <th data-field="name">Email</th>
              <th data-field="name">Address</th>
               <th data-field="name">PhoneNumber</th>
               <th data-field="name">User Type</th>
               
            <th data-field="price">Decision</th>
        </tr>
            <c:forEach var="user" items="${users}" varStatus="status">
       		  <tr>
            <th data-field="id">${user.name}</th>
            <th data-field="name">${user.email}</th>
              <th data-field="name">${user.address}</th>
               <th data-field="name">${user.phoneNumber}</th>
               <th data-field="name">
               		<select id="${user.username}">
               			<option>User</option>
               			<option>Merchant</option>
               		</select>
               </th>
            <th data-field="price">
            	<ul>
            	<li role="presentation" ><a href="#" onclick="actionExternalUser('${user.username}',1)">ACCEPT REQUEST</a></li>
  				<li role="presentation"><a href="#" onclick="actionExternalUser('${user.username}',0)">DECLINE REQUEST</a></li>
  			</ul>
  			</th>
        </tr>
       </c:forEach>

    </thead>
</table>