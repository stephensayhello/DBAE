<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
<head>
<link rel="stylesheet" href="css/generalLayout.css">
<link rel="stylesheet" href="css/provilnav.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="Paul Blanke, Stephen Galla, Benjamin Gajewski">

<title>Insert title here</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
	<a:navKunde rolle="${rolle }"></a:navKunde>
	<div class="container">
		<a:profilnav></a:profilnav>
		<h1>Hallo ${kundeeingeloggt.vorname}! Hier sind ihre
			Profilinformationen.</h1>
		<ul>
			<li>Vorname: ${kundeeingeloggt.vorname}</li>
			<li>Nachname: ${kundeeingeloggt.nachname}</li>
			<li>Emailadresse: ${kundeeingeloggt.email}</li>
			<li>Anschrift: ${kundenadresse.strasse}
				${kundenadresse.hausnummer}</li>
			<li>Postleitzahl: ${kundenadresse.plz} Ort: ${kundenadresse.ort}</li>
		


		</ul>
	</div>
</body>
</html>