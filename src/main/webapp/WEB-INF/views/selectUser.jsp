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
 <div class="col-xs-"2">

<ul>
	 <li class="list-group-item">
	 <select id="ddlViewBy" onchange="viewDetails()">
	 	   <c:forEach var="user" items="${users}" varStatus="status">
       			 <option >${user.name}</option>
       		</c:forEach>
	 </select>
	 
	 </li>

         <input type="button" placeholder="view"  onclick="viewUser()" value="View">
          <input type="button" placeholder="transactions"  onclick="viewTransaction()" value="transactions">
        </div>
  
</ul>

<sricpt type="text/javascript">


</sricpt>
</body>
</html>