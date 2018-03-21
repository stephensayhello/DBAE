<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<link rel="stylesheet" href="css/generalLayout.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Produkt bearbeiten (Admin)</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
<<<<<<< HEAD
	<a:navKunde rolle="${rolle }"></a:navKunde>
	<div class = "">
		<h1 class="header">Produkt bearbeiten</h1>
		<p>Bitte ändern Sie das Produkt</p>
		<hr>

		<div>
			<form action="ProduktBearbeitenServlet" method="post"
				>


				<label for="name"><b>Name:</b></label> <input class="input-dbae"
					type="text" placeholder="${produkt.name }" name="name"> <label
					for="menge"><b>Menge:</b></label><input class="input-dbae"
					type="text" placeholder="${produkt.menge}" name="menge"> <label
					for="preis"><b>Preis:</b></label><input class="input-dbae"
					type="text" placeholder="${produkt.preis }" name="preis"> <label
					for="versanddauer"><b>Versanddauer:</b></label><input
					class="input-dbae" type="text" name="versanddauer">


=======
<a:navKunde rolle="${rolle }"></a:navKunde>
<script>
<!-- je nach dem Sachsen ausblenden -> Tag !-->
</script>

	<form action="ProduktBearbeitenServlet" method="post" style="border: 1px solid #ccc">
		<div class="container">
			<h1>Produkt bearbeiten</h1>
			<p>Bitte ändern Sie das Produkt</p>
			<hr>
			<div class="login">
				<label for="name" ><b>Name:</b></label> <input class="input-dbae" type="text" placeholder="${produkt.name }" name="name">
				<label for="beschreibung"><b>Beschreibung:</b></label> <input class="input-dbae" type="text" placeholder="${produkt.beschreibung }" name="beschreibung">
				<label for="menge"><b>Menge:</b></label><input class="input-dbae" type="text" placeholder ="${produkt.menge}" name="menge">
				<label for="preis"><b>Preis:</b></label><input class="input-dbae" type="text" placeholder="${produkt.preis }" name="preis">
				<label for="auswahl"><br>Lieferstatus:<br/></label><select name="auswahl" class="input-dbae">
					<option value="Lieferbar" class="input-dbae">Lieferbar</option>
					<option value="nicht lieferbar" class="input-dbae">Nicht lieferbar</option>
				</select>
				<label for="versanddauer"><b>Versanddauer:</b></label><input class="input-dbae" type="text" name="versanddauer">
				
				<div class="clearfix">

					<button type="submit" class="button-dbae">Produkt ändern</button>
					 
					
				</div>
			</div>
			<a:modal messages="${messages}"></a:modal>
>>>>>>> 3cfa36fa6bf4a3e8c4c56bc2cee428cf6466a157

				<button type="submit" class="button-dbae">Produkt ändern</button>


				<a:modal messages="${messages}"></a:modal>


			</form>

			<form action="ProdouktBearbeitenServlet" method="get">
				<button type="submit" class="button-dbae">Produkt löschen</button>
			</form>
		</div>
	</div>


<<<<<<< HEAD

=======
<br />
<div class="input-dbae">
<form action ="ProduktBearbeitenServlet" method="get">
<button type ="submit" class="button-dbae">Produkt löschen</button>
</form>
</div>
>>>>>>> 3cfa36fa6bf4a3e8c4c56bc2cee428cf6466a157
</body>
</html>