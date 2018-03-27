<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/generalLayout.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<title>login</title>
<link rel="icon" href="img/Logo.png">
<a:bootstrap></a:bootstrap>
</head>
<body>
	<a:navKunde rolle="${rolle }"></a:navKunde>

	<form action="LoginServlet" method="post" style="border: 1px solid #ccc">
		<div class="container">
			<h1>Login</h1>
			<p>Please fill in this form to Login.</p>
			<hr>
			<div class="login">
				<label for="email"><b>E-mail:</b></label> <input class="input-dbae"
					type="text" placeholder="E-mail" name="email" required> <label
					for="psw"><b>Password:</b></label> <input class="input-dbae"
					type="password" placeholder="Enter Password" name="psw" required>
				<div class="clearfix">

					<button type="submit" class="button-dbae">Login</button>
				</div>
			</div>
			<a:modal messages="${messages}"></a:modal>

		</div>
	</form>
	
	<a:footer></a:footer>
</body>
</html>