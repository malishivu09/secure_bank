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

<c:url var="creditUrl" value="/UserHome/credit/send"></c:url>

<%-- <form id="integerForm" class="form-horizontal" data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
    data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
    data-bv-feedbackicons-validating="glyphicon glyphicon-refresh" action ="creditUrl">
    
    <div class="form-group">
        <label class="col-sm-4 control-label">Type an integer number</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="number"
                data-bv-integer="true"
                data-bv-integer-message="The value is not an integer" />
        </div>
    </div>
    <button type="button" class="btn btn-default" ></button>
    
    
    
</form>
 --%>
 <form:form class="navbar-form navbar-left" role="search" action ="${creditUrl}" commandName="amount">
  <div class="form-group">
    <input  type="text" id="creditamount" class="form-control" name="amount" placeholder="Enter amount"/>
  </div>
  <button type="button" class="btn btn-default" onclick="creditTransfer()">Submit</button>
</form:form>
 
 <%-- <form:form action="${creditUrl}" method="post" commandName="amount">
		<table class="tg">

			<tr>
				<td><form:label path="">
						<spring:message text="Amount : " />
					</form:label></td>
				<td><form:input path="" type="currency" value="0.0" /></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message text="   Send    "/>" /></td>

			</tr>
			</table>
	</form:form> --%>
</body>
</html>