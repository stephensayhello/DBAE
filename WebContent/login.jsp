<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
	<a:navigation></a:navigation>
	<form style="border: 1px solid #ccc">
		<div class="container">
			<h1>Login</h1>
			<p>Please fill in this form to Login.</p>
			<hr>
			<div class="login">
				<label for="nachname"><b>E-mail:</b></label> <input type="text"
					placeholder="E-mail" name="email" required > <label
					for="psw"><b>Password:</b></label> <input type="password"
					placeholder="Enter Password" name="psw" required>
					<div class="clearfix">

				<button type="submit" class="signupbtn">Login</button>
			</div>
			</div>




			
		</div>
	</form>
</body>
</html>