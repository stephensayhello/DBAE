<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/generalLayout.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<title>Insert title here</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
<a:navKunde rolle="${rolle }"></a:navKunde>
<a:modal messages="${messages}"></a:modal>

		<div class="container">
			<h1>Profil</h1>
			<p>Btte geben Sie ihr Passwort ein, um ihr Profil einzusehen.</p>
			<hr>
			<div class="login">
			<form action="ProfilServlet" method="post" style="border: 1px solid #ccc">
				 <label
					for="psw"><b>Password:</b></label> <input class="input-dbae"
					type="password" placeholder = "Enter Password" name="psw" required>
				<div class="clearfix">

					<button type="submit" class="button-dbae">Login</button>
				</div>
					</form>
				
		
			</div>
			</div>
			<a:footer></a:footer>
</body>
</html>