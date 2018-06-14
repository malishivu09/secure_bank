<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false"%>	
	<hr>

		<div class="centerpanel" id="centralpanel" style="margin-left:100px;"><b><br></>Pending Internal User Requests</b>
		<br>
		<table class="table table-bordered"data-toggle="table"  data-cache="false" data-height="299">
        <thead>
         <tr>
            <th data-field="internal">Internal Username</th>
            <th data-field="external">External Username</th>
              <th data-field="type">Take Action</th>
        </tr>
       <c:forEach var="pMap" items="${pendingMapping}" varStatus="status">
       		  <tr>
            <th data-field="internal">${pMap.userByInternalUsername.username}</th>
            <th data-field="external">${pMap.userByExternalUsername.username}</th>
            <th data-field="action">
            	<ul>
            	<li role="presentation" ><a href="#" onclick="actionInternalUser('${pMap.userByInternalUsername.username}','${pMap.userByExternalUsername.username}',1,'${pMap.accessType}')">ACCEPT REQUEST</a></li>
  				<li role="presentation"><a href="#" onclick="actionInternalUser('${pMap.userByInternalUsername.username}','${pMap.userByExternalUsername.username}',0,'${pMap.accessType}')">DECLINE REQUEST</a></li>
  			</ul>
  			</th>
        </tr>
       </c:forEach>

    </thead>
</table>
   </br>
   </div>
   	<div class="centerpanel" id="centralpanel" style="margin-left:100px;"><b><br></> Internal User Requests</b>
		<br>
		<table class="table table-bordered"data-toggle="table"  data-cache="false" data-height="299">
        <thead>
         <tr>
            <th data-field="internal">Internal Username</th>
            <th data-field="external">External Username</th>
              <th data-field="type">Take Action</th>
        </tr>
       <c:forEach var="pMap" items="${approvedMapping}" varStatus="status">
       		  <tr>
             <th data-field="internal">${pMap.userByInternalUsername.username}</th>
            <th data-field="external">${pMap.userByExternalUsername.username}</th>
            <th data-field="action">
            	<ul>
           <li role="presentation"><a href="#" onclick="actionInternalUser('${pMap.userByInternalUsername.username}','${pMap.userByExternalUsername.username}',0,'${pMap.accessType}')">DECLINE REQUEST</a></li>
  			</ul>
  			</th>
        </tr>
       </c:forEach>

    </thead>
</table>
</div>
