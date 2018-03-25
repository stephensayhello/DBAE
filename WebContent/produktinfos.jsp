<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
<%@ taglib prefix="a" uri="/WEB-INF/tag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<title>Produktinfos</title>
<link rel="stylesheet" href="css/generalLayout.css">
<link rel="stylesheet" href="css/produkt_anlegen.css">
<a:bootstrap></a:bootstrap>
</head>
<body>
<script>

$(document).ready(function() {
	var klick = "${klick}";
	if (klick === "") {
		$("#anzeigen").click();
	}
});

function onHidde() {
	document.getElementById("unsichtbar").style.visibility="hidden";
}

function onShow() {
	document.getElementById().style.visibility ="visible";
}

</script>
<a:navKunde rolle="${rolle }"></a:navKunde>

<div class="container">
<h1>Alle verfügbare Produkte anzeigen</h1>
<form action="ProduktUebersichtServlet" method="post">
<button value="submit" class="button-dbae" id="anzeigen">Anzeigen</button>

</form>
<br/>
<br />

<div class="table-responsive">
<table class="table">
			<tr>

				<th>Preis</th>
				<th>Name</th>
				<th>Artikelnr.</th>
				<th>ID</th>
				<th>Menge</th>
				<th>Groesse</th>
				<th>Status</th>

			</tr>
			<c:forEach var="produkt" items="${produkte}">



				<tr>
					<td>${produkt.preisineuro}</td>				
					<td>${produkt.name}</td>
					<td>${produkt.artikelnr}</td>
					<td>${produkt.produkt_id}</td>
					<td>${produkt.menge}</td>
					<td>${produkt.groesse}</td>					
					<td>${produkt.status}</td>

				</tr>





			</c:forEach>
	</table>
</div>
</div>


<div class="container">
	<form action="ProduktUebersichtServlet" method="get" >
	<label for="auswahl" class="input-dbae">Produkt(Auswahl anhand von Name und ID)</label>
	<select name="auswahl" class ="input-dbae">
	<c:forEach  var="produkt" items="${ produkte}" >
	<option value= "${produkt.produkt_id}" >${produkt.name}, ${produkt.produkt_id}</option>
	</c:forEach>
	</select>
	<button value="submit"  class= "button-dbae">Bearbeiten</button>
	<input type ="hidden" value="id" name="pruefe">
	</form>

	<form action="ProduktUebersichtServlet" method="get" >
	<label for="auswahl" class="input-dbae">Artikel einer Produktgruppe anpassen</label><br/>
	<select name="auswahl"  class="input-dbae">
	<c:forEach  var="produkt" items="${produktesortiert}" >
	<option value= "${produkt.artikelnr}" >${produkt.name} ${produkt.artikelnr}</option>
	</c:forEach>
	</select>
	<br/>
	<input type="hidden" value="artikelnr" name="pruefe">
	<button value="submit" class="button-dbae">Bearbeiten</button>
	</form>
</div>


	





</body>
</html>