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
<meta name="author"
	content="Paul Blanke, Stephen Galla, Benjamin Gajewski">

<title>Profilansicht</title>
<link rel="icon" href="img/Logo.png">
<a:bootstrap></a:bootstrap>
</head>
<body>
	<a:navKunde rolle="${rolle }"></a:navKunde>
	<div class="container">
		<a:profilnav></a:profilnav>






		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Hallo ${kundeeingeloggt.vorname}! Hier
					sind ihre Profilinformationen.</h3>
			</div>
			<div class="panel-body">
				<div class="row">



					<table class="table table-user-information table-striped">
						<tbody>
							<tr>
								<td>Vorname:</td>
								<td>${kundeeingeloggt.vorname}</td>
							</tr>
							<tr>
								<td>Nachname:</td>
								<td>${kundeeingeloggt.nachname}</td>
							</tr>
							<tr>
								<td>Emailadresse:</td>
								<td>${kundeeingeloggt.email}</td>
							</tr>

							
							<tr>
								<td>Anschrift:</td>
								<td>${kundenadresse.strasse}${kundenadresse.hausnummer}</td>
							</tr>
							<tr>
								<td>Postleitzahl:</td>
								<td>${kundenadresse.plz}</td>
							</tr>
							<tr>
								<td>Ort:</td>
								<td>${kundenadresse.ort}</td>
							</tr>


						</tbody>
					</table>


				</div>
			</div>


		</div>
	</div>


	<a:footer></a:footer>
</body>
</html>