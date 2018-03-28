<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
<head>
<link rel="stylesheet" href="css/generalLayout.css">

<link rel="stylesheet" href="css/bestellungadmin.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<title>Bestellungsuebersicht(admin)</title>
<link rel="icon" href="img/Logo.png">
<a:bootstrap></a:bootstrap>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			var klick = "${klick}";
			if (klick === "") {
				$("#anzeigen").click();
			}
		});
	</script>
	<a:navKunde rolle="${rolle }"></a:navKunde>
	<div class="container">





		
			<c:forEach var="bestellung" items="${Bestellungen}">
			<h2>Bestellnummer: ${bestellung.bestellnummer}</h2>
			<table class="table table-bordered table-striped">
				<tr>
					<td colspan="5"><p id="wichtig">
					<table class="table table-bordered">
						<tr><td>Datum:</td> <td>${bestellung.datefromdb}</td></tr>
						<tr><td>Kundennummer:</td> <td> ${bestellung.kunde.nutzer_id}</td></tr>
						<tr><td>Status:</td> <td> ${bestellung.bearbeitungsstatus}</td></tr>
						<tr><td>Name:</td> <td> ${bestellung.kunde.vorname} ${bestellung.kunde.nachname}</td></tr>
						<tr><td>Anschrift:</td> <td> ${bestellung.kunde.adresse.strasse} ${bestellung.kunde.adresse.hausnummer},  ${bestellung.kunde.adresse.plz} ${bestellung.kunde.adresse.ort}</td></tr>
						</table>
						<h3>Produkte</h3>
						</td>
						
				</tr>

				<th>Name</th>
				<th>Preis</th>
				<th>Artikelnr</th>
				<th>Anzahl</th>
				<th>Größe</th>
				<c:forEach var="produkt" items="${bestellung.bestellliste}">
					<tr>
						<td>${produkt.name}</td>
						<td>${produkt.preismitanzahlineuro}</td>
						<td>${produkt.artikelnr}</td>
						<td>${produkt.anzahl}</td>
						<td>${produkt.groesse}</td>
					</tr>
		


				
				</c:forEach>
				</table>
			</c:forEach>
		

	
	<form action="AdminBestelluebersichtServlet" method="get">
		<input type=submit id="anzeigen">
	</form>
    
	<form action="AdminBestelluebersichtServlet" method="post" >
	<label for="auswahl" class="input-dbae">Status bearbeiten: Bitte wählen Sie eine Bestellnummer aus</label>
	<select name="auswahl" class ="input-dbae">
	<c:forEach  var="bestellung" items="${Bestellungen}" >
	<option value= "${bestellung.bestellnummer}" >${bestellung.bestellnummer}</option>
	</c:forEach>
	</select>
	<label for="versanddauer"><b>Neuer Status:</b></label><input
					class="input-dbae" type="text" name="status">
	
	<button value="submit"  class= "button-dbae">Bearbeitungsstatus aendern</button>
	
	</form>
	</div>

</body>
</html>