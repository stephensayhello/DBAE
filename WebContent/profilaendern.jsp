<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
<head>
<link rel="stylesheet" href="css/generalLayout.css">
<link rel="stylesheet" href="css/profilnav.css">
<link rel="stylesheet" href="css/signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
<a:navigation></a:navigation>
<div id = "container">
<a:profilnav></a:profilnav>
<div class="container">
<h1>Pers�nliche Daten �ndern:</h1><br/>
<form action="ProfilAendernServlet"  method="post">

	<div class="name">
	<label for="vorname"><b>Vorname:</b> </label><input type="text" name="vorname" class="input-dbae" required-size="3">
	<label for="nachname"><b>Nachname:</b> </label><input type="text" name="nachname" class="input-dbae" required-size="3">
	<label for="mail"><b>E-Mail:</b> </label><input type="text" name="mail" class= "input-dbae" required-size="3">
	</div>
	
	<div class="adresse">
	<label for="strasse">Strasse: </label><input type="text" name="strasse" class="input-dbae" required-size="3">
	<label for="hausnr">Hausnummer: </label><input type="text" name="hausnr" class="input-dbae" required-size="3">
	<label for="plz">Postleitzahl: </label><input type="text" name="plz" class="input-dbae" required-size="3">
	<label for="ort">Ort: </label><input type="text" name="ort" class="input-dbae" required-size="3">
	</div>
	<button type="submit" class="button-dbae">�ndern</button>
</form>

<a:modal messages="${messages}"></a:modal>
</div>
</div>
</body>
</html>