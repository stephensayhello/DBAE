<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%> 
<%@ taglib prefix="b" uri="/WEB-INF/tagstesten.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<!-- Einbindung für Bootstrap -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Das neueste kompilierte und minimierte CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- Optionales Theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<!-- Das neueste kompilierte und minimierte JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


<title>DBAE Projekt</title>
</head>
<body>
<a:logo></a:logo>
<br/>
<br/>
<br />
<!-- Achtung eine Nav bar mit Bootstrap -->

<b:navigation></b:navigation>


<form action="EingabeServlet" method="post" id="formLogin" >
<label>Name:</label><br><input type="text" name="bname"><br/>
<label>Passwort:</label><br/><input type="password" name="passwort">
<br/><input type="submit">
</form>
<br>
<p>Willkommen im SportWeb. Ihr Fachgeschäft für jede Sportart! Unkomplizierter und schneller Onlineeinkauf.</p><br>

<br>
<a:fussleiste />
</body>
</html>