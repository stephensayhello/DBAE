<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
<%@ taglib prefix="a" uri="/WEB-INF/tag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Produktinfos</title>
<link rel="stylesheet" href="css/generalLayout.css">
<link rel="stylesheet" href="css/produkt_anlegen.css">
<a:bootstrap></a:bootstrap>
</head>
<body>
<script>
function onHidde() {
	document.getElementById("unsichtbar").style.visibility="hidden";
}

function onShow() {
	document.getElementById().style.visibility ="visible";
}

</script>
<a:changenav></a:changenav>
<h1>Alle verfuegbare Produkte anzeigen</h1>
<div class="container">
<form action="ProduktUebersichtServlet" method="post">
<button value="submit" class="button-dbae">Anzeigen</button>
<button class="button-dbae">Loeschen</button>
</form>
<br/>
</div>
<br />
<div class="container"><div class="table-responsive">
<table class="table">
			<tr>

				<th>Preis</th>
				<th>Name</th>
				<th>Artikel-Nr</th>
				<th>Menge</th>
				<th>Groesse</th>
				<th>Status</th>

			</tr>
			<c:forEach var="produkt" items="${produkte}">



				<tr>
					<td>${produkt.preisineuro}</td>				
					<td>${produkt.name}</td>
					<td>${produkt.artikelnr}</td>
					<td>${produkt.menge}</td>
					<td>${produkt.groesse}</td>					
					<td>${produkt.status}</td>

				</tr>





			</c:forEach>
	</table>
</div>
</div>
<div class="container">
	<form action="ProduktUebersichtServlet" method="get">
	<select name="auswahl">
	<c:forEach  var="produkt" items="${ produkte}" >
	<option value= "${produkt.produkt_id}" >${produkt.name} ${produkt.produkt_id}</option>
	</c:forEach>
	</select>
	<button value="submit"  class= "dbae-button">Bearbeiten</button>
	</form>
</div>


	
</div>

<br />


<a:modal messages="${messages}"></a:modal>
</body>
</html>