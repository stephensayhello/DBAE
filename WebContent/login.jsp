<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/generalLayout.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
	<a:navigation></a:navigation>

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
</body>
</html>