<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<link rel="stylesheet" href="css/generalLayout.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author"
	content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<title>Produkt bearbeiten (Admin)</title>
<link rel="icon" href="img/Logo.png">
<a:bootstrap></a:bootstrap>

</head>
<body>
	<a:modal messages="${messages}"></a:modal>

	<a:navKunde rolle="${rolle }"></a:navKunde>
	<div class="container">
		<h1 class="header">Produkt bearbeiten</h1>
		<p>Bitte ändern Sie das Produkt</p>

		<hr>

		<div>
			<form action="ProduktgruppenBearbeitenServlet" method="post">


				<label for="name"><b>Name:</b></label><input class="input-dbae"
					type="text" value="${produkt.name}" name="name"> <label
					for="preis"><b>Preis:</b></label><input class="input-dbae"
					type="number" value="${produkt.preis}" name="preis"> <label
					for="anfragetext"><br>Beschreibung:</br></label>
				<textarea rows="5" cols="5" name="beschreibung" class="input-dbae">${produkt.beschreibung}</textarea>



				<button type="submit" class="button-dbae">Produkt ändern</button>


				<a:modal messages="${messages}"></a:modal>


			</form>

			<form action="ProduktgruppenBearbeitenServlet" method="get">
				<button type="submit" class="button-dbae">Produkt löschen</button>
			</form>
		</div>
	</div>


	<a:footer></a:footer>
</body>
</html>