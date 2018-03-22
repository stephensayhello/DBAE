<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
<%@ taglib prefix="a" uri="/WEB-INF/tag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/generalLayout.css">
<link rel="stylesheet" href="css/login.css">
<title>KundenInformation</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
<a:navKunde rolle="${rolle }"></a:navKunde>

<!-- Eine JS Funktion im Test :P -->
<script>
function onHidde() {
	document.getElementById("unsichtbar").style.visibility="hidden";
}

function onShow() {
	document.getElementById().style.visibility ="visible";
}
</script>
<div class="container">
<h1>Eine Liste aller angemeldeten Kunden:</h1>
<p>Durch Auswahl eines Kunden wird das Passwort des Kunden zurück gesetzt.</p>


<form action="KundenOperationsServlet" method="post">
<button value="submit" class="button-dbae" onclick="onHidde()">Anzeigen</button>
</form>
</div>



<div class="container" id="unsichtbar"  style = 'hidden'>
<div class="container"><div class="table-responsive">
		<table class="table">
			<tr>
				
				<th>Vorname</th>
				<th>Nachname</th>
				
			</tr>
			<c:forEach var="kunde" items="kunden">



				<tr>
					
					<td>${kunde.vorname}</td>
					<td>${kunde.nachname}</td>
				</tr>





			</c:forEach>
	</table>
</div>
</div>
<br/>
<br/>
<p>bla bla </p>Formatierung geht nicht xD
<div class="container">
 <form action = "KundenOperationsServlet" action = "post">
 	<select name ="auswahl">
 		<c:forEach var="kunde" items="kunden">
 		<option value='${kunde}'>${kunde}</option>
 		</c:forEach>
 	</select>
 		<label for="passwort">Passwort</label><input type="text" name="passwort">
 	<button type="submit" class="dbae-button">Passwort zurueck setzen</button>
 </form>

</div>
<br/>
<div class="container">
 <form action = "KundenOperationsServlet" action = "post">
 	<select name ="loeschen">
 		<c:forEach var="kunde" items="kunden">
 		<option value= '${kunde}'>${kunde}</option>
 		</c:forEach>
 	</select>
 	<br />
 
 	<button type="submit" class="dbae-button">Kunden entfernen</button>
 </form>

</div>
</div>

</body>
</html>