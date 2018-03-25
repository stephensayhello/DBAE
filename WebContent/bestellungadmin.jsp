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

<title>Insert title here</title>
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





		<table class="table">
			<c:forEach var="bestellung" items="${Bestellungen}">
				<tr>
					<td><p id="wichtig">
							<b>Bestellnummer: ${bestellung.bestellnummer}</b>
						</p>
						<br> <b>Datum: ${bestellung.datefromdb}</b><br>
						<br> <b>Kundennummer: ${bestellung.kunde.nutzer_id}</b><br>
						<br> <b>Status: ${bestellung.bearbeitungsstatus}</b><br>
						<br> <b>Anschrift: ${bestellung.kunde.adresse.strasse}${bestellung.kunde.adresse.hausnummer}${bestellung.kunde.adresse.plz}${bestellung.kunde.adresse.ort}</b><br>
						</td>
						
				</tr>


				<c:forEach var="produkt" items="${bestellung.bestellliste}">
					<tr>
						<td>Name: ${produkt.name}</td>
						<td>Preis: ${produkt.preismitanzahlineuro}</td>
						<td>Artikelnr.: ${produkt.artikelnr}</td>
						<td>Anzahl: ${produkt.anzahl}</td>
						<td>Größe: ${produkt.groesse}</td>
					</tr>




				</c:forEach>
			</c:forEach>
		</table>
	
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