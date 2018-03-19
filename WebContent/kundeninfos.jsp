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
<a:changenav></a:changenav>
<h1>Eine Liste aller angemeldeten Kunden:</h1>
<!-- Eine JS Funktion im Test :P -->
<script>
function onclick() {
	document.getElementById().style.visibility="visible";
}
</script>
<div class="container">
<p>Durch Auswahl eines Kunden wird das Passwort des Kunden zurück gesetzt.</p>
<form action="KundenOperationsServlet" method="post">
<button value="submit" class="button-dbae">Anzeigen</button>
</form>
</div>
<br />


<div class="container" id="unsichtbar">
<div class="container"><div class="table-responsive">
		<table class="table">
			<tr>

				<th>E-mail</th>
				<th>Vorname</th>
				<th>Nachname</th>
				
			</tr>
			<c:forEach var="kunde" items="kunden">



				<tr>
					<td>${kunde.email }</td>
					<td>${kunde.vorname}</td>
					<td>${kunde.nachname}</td>
				</tr>





			</c:forEach>
	</table>
</div>
</div>
<br/>
<br/>
<div class="container">
 <form action = "KundenOperationsServlet">
 
 </form>

</div>
</div>

</body>
</html>