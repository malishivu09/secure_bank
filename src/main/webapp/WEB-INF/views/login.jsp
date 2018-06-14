<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<title>Login Page</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Montserrat'
	rel='stylesheet' type='text/css'>
	

<link href="<c:url value="/resources/css/style4login.css" />" rel="stylesheet">
<script>
function clicked(val)
{
var txt;
txt = document.getElementById('text').value;
if(val != 'B')
{
txt = txt + '' + val;
}
else
{
txt = txt.substr(0,(txt.length)-1);
}
document.getElementById('text').value = txt;
}

function forgetPassword(){
	var user = $("#username").val();
	if(user==""){
		alert("Username can't be empty");
		return;
	}else{
		window.location="/SecureBankingGroup13/forgot?user="+user;
	}
}

    </script>
</head>
<body>

<h2>
		<b><a>ARIZONA STATE BANK</a> </b>
	</h2><hr>
	<h2>Login with Username and Password</h2>
	<c:url var="loginUrl" value="/j_spring_security_check"></c:url>
	
	<div class="container">
<div class="row clearfix">
    <div class="col-md-5 col-md-offset-3 column">
	
	<form action="${loginUrl}" method="POST">
		<table>
		
			<tr class="control-label col-xs-8">
				<td><b ><label style="color:#ADD8E6;">Username:&nbsp;&nbsp;</label></b></td>
				<td><input type='text' name='username' id="username"  class="form-control"/></td>
			</tr>
			
			<tr class="control-label col-xs-8">
				<td><b><br><label style="color:#ADD8E6;">Password:&nbsp;&nbsp;</label></b></td>
				<td><br><input id="text" type='password' name='password' class="form-control" /></td>
			</tr>
			<tr class="control-label col-xs-8">
				<td><b><br><a href="#" style="color:#FFA500;" onclick="forgetPassword()">Forgot Password? -Dont worry!&nbsp;&nbsp;</b></a></td>
				
			</tr>
			<tr>
				<td><br><img src="resources/images/image.png" alt="Virtual keyboard" usemap="#keys">
					<map name="keys">
						<area onclick="clicked('1');" coords="10,7,42,42" shape="rect"></area>
						<area onclick="clicked('2');" coords="47,7,79,42" shape="rect"></area>
						<area onclick="clicked('3');" coords="84,7,116,42" shape="rect"></area>
						<area onclick="clicked('4');" coords="121,7,153,42" shape="rect"></area>
						<area onclick="clicked('5');" coords="158,7,190,42" shape="rect"></area>
						<area onclick="clicked('6');" coords="195,7,227,42" shape="rect"></area>
						<area onclick="clicked('7');" coords="232,7,264,42" shape="rect"></area>
						<area onclick="clicked('8');" coords="269,7,301,42" shape="rect"></area>
						<area onclick="clicked('9');" coords="306,7,338,42" shape="rect"></area>
						<area onclick="clicked('0');" coords="343,7,375,42" shape="rect"></area>
						<area onclick="clicked('-');" coords="380,7,412,42" shape="rect"></area>
						<area onclick="clicked('B');" coords="428,7,512,42" shape="rect"></area>
						<area onclick="clicked('q');" coords="10,47,42,80" shape="rect"></area>
						<area onclick="clicked('w');" coords="48,47,80,80" shape="rect"></area>
						<area onclick="clicked('e');" coords="86,47,118,80" shape="rect"></area>
						<area onclick="clicked('r');" coords="124,47,156,80" shape="rect"></area>
						<area onclick="clicked('t');" coords="161,47,193,80" shape="rect"></area>
						<area onclick="clicked('y');" coords="199,47,221,80" shape="rect"></area>
						<area onclick="clicked('u');" coords="232,47,264,80" shape="rect"></area>
						<area onclick="clicked('i');" coords="280,47,312,80" shape="rect"></area>
						<area onclick="clicked('o');" coords="315,47,345,80" shape="rect"></area>
						<area onclick="clicked('p');" coords="348,47,383,80" shape="rect"></area>
						<area onclick="clicked('[');" coords="385,47,420,80" shape="rect"></area>
						<area onclick="clicked(']');" coords="422,47,455,80" shape="rect"></area>
						<area onclick="clicked('\\');" coords="454,47,486,80" shape="rect"></area>
						<area onclick="clicked('a');" coords="34,84,67,116" shape="rect"></area>
						<area onclick="clicked('s');" coords="71,82,106,117" shape="rect"></area>
						<area onclick="clicked('d');" coords="109,84,143,115" shape="rect"></area>
						<area onclick="clicked('f');" coords="150,82,181,116" shape="rect"></area>
						<area onclick="clicked('g');" coords="187,83,218,116" shape="rect"></area>
						<area onclick="clicked('h');" coords="223,82,256,117" shape="rect"></area>
						<area onclick="clicked('j');" coords="261,83,294,117" shape="rect"></area>
						<area onclick="clicked('k');" coords="299,83,332,117" shape="rect"></area>
						<area onclick="clicked('l');" coords="336,82,371,117" shape="rect"></area>
						<area onclick="clicked(';');" coords="374,84,407,117" shape="rect"></area>
						<area onclick="clicked('\'');" coords="412,83,445,117"
							shape="rect"></area>
						<area onclick="clicked('$');" coords="452,84,486,115" shape="rect"></area>
						<area onclick="clicked('z');" coords="50,122,83,154" shape="rect"></area>
						<area onclick="clicked('x');" coords="90,121,122,154" shape="rect"></area>
						<area onclick="clicked('c');" coords="127,121,160,154"
							shape="rect"></area>
						<area onclick="clicked('v');" coords="163,121,198,154"
							shape="rect"></area>
						<area onclick="clicked('b');" coords="203,121,235,154"
							shape="rect"></area>
						<area onclick="clicked('n');" coords="240,121,274,154"
							shape="rect"></area>
						<area onclick="clicked('m');" coords="278,121,311,154"
							shape="rect"></area>
						<area onclick="clicked(',');" coords="346,120,349,154"
							shape="rect"></area>
						<area onclick="clicked('.');" coords="353,120,387,155"
							shape="rect"></area>
						<area onclick="clicked('/');" coords="392,120,424,154"
							shape="rect"></area>
						<area onclick="clicked('=');" coords="431,121,463,154"
							shape="rect"></area>
						<area onclick="clicked(' ');" coords="83,159,431,192" shape="rect"></area>
					</map></td>
			</tr>

			<tr>
				<td colspan='2'><br><b><input name="submit" type="submit"
					value="Login"  class="form-control"/></b></td>
			</tr>
			<tr>
				<td colspan='2'><b><a
					href="${pageContext.request.contextPath}/register"><br><input
						name="Register" type="button" class="form-control" value="Register - Click for new registration !" /></a></b></td>
			</tr>
		</table>
	</form>
	</div></div></div>
</body>
</html>