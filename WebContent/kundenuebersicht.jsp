<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<link rel="stylesheet" href="css/generalLayout.css">
<title>Kunden Übersicht</title>
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
<h1>Alle verfuegbare Produkte anzeigen</h1>
<form action="KundenOperationsServlet" method="get">
<button value="submit" class="button-dbae" id="anzeigen">Anzeigen</button>

</form>
<br/>
</div>
<br />
<div class="container">
<div class="table-responsive">
<table class="table">
			<tr>

				<th>KNR</th>
				<th>Name</th>
				<th>Email</th>
				
			</tr>
			<c:forEach var="kunde" items="${kunden}">



				<tr>
					<td>${kunde.nutzer_id}</td>				
					<td>${kunde.nachname}</td>
					<td>${kunde.email}</td>
					

				</tr>





			</c:forEach>
	</table>
</div>
</div>


<div class="container">
	<form action="KundeBearbeitenServlet" method="get" >
	<label for="auswahlkunde" class="input-dbae">Kundenauswahl um neues Passwort zu setzen.</label>
	<select name="auswahlkunde" class ="input-dbae">
	<c:forEach  var="kunde" items="${ kunden}" >
	<option value= "${kunde.nutzer_id}" >${kunde.nachname}, ${kunde.nutzer_id}</option>
	</c:forEach>
	</select>
	<button value="submit"  class= "button-dbae">Bearbeiten</button>

	</form>
	<form action="KundenOperationsServlet" method="post" >
	<label for="auswahl" class="input-dbae">Kunde löschen</label>
	<select name="auswahl" class ="input-dbae">
	<c:forEach  var="kunde" items="${kunden}" >
	<option value= "${kunde.nutzer_id}" >${kunde.nachname}, ${kunde.nutzer_id}</option>
	</c:forEach>
	</select>
	<button value="submit"  class= "button-dbae">Kunde löschen</button>
	
	</form>

	
</div>
	


<a:footer></a:footer>

</body>	
</html>