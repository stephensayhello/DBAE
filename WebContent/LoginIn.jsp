<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="nav" uri="/WEB-INF/tag.tld" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>WebShop</h1>
<nav:navigation />
<p>Herzlichen Willkommen. Hier können Sie sich als neukunde regestieren lassen.</p>
<form action="NeuKundeAnmeldung" method="post">
<label for="Name">Name:</label><input type="text" name="name"><br />
<label for="Adresse">Adresse:</label><input type="text" name="adresse"><br />
<label for="Mail">EMail:</label><input type="text" name="mail"><br />
<label for="Passwort">Passwort:</label><input type="password" name="passwort"><br />
<input type="button" value="Anmelden">
</form>

</body>
</html>