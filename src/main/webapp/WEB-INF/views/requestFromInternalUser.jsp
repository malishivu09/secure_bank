<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form class="form-horizontal">
    <div class="form-group">
        <label for="userName" class="control-label col-xs-2">User Name to request for :</label>
        <div class="col-xs-3">
            <input type="text" 
             class="form-control" id="username" placeholder="username">
        </div>
    </div>
    <div class="form-group">
        <label for="inputType" class="control-label col-xs-2">Request Type</label>
        <div class="col-xs-3">
        	<select id="requestType">
        		<option>View</option>
        		<option>Transaction</option>
        	</select>
        </div>
    </div>
     <div class="form-group">
        <label for="email" class="control-label col-xs-3"></label>
        <div class="col-xs-1">
            <input type="button" class="form-control"  placeholder="request"  onclick="sendRequest()" value="Request">
        </div>
</div>
</form>
</body>
</html>