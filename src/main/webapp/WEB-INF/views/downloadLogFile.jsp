<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false"%>	
	<hr>

		<div class="centerpanel" id="centralpanel" style="margin-left:100px;"><b><br></>System Log</b>
		<br>
		<table class="table table-bordered"data-toggle="table"  data-cache="false" data-height="299">
        <thead>
         <tr>
            <th data-field="name">Access Log</th>
        </tr>
       <c:forEach var="logLine" items="${syslog}" varStatus="status">
       		  <tr>
            <th data-field="id">${logLine}</th>
        </tr>
       </c:forEach>

    </thead>
</table>