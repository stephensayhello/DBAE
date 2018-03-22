<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
    <%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
		hide_elements('form');
		hide_elements('Form');
	} else {
		show_elements('form');	
	}
	
});

function show_elements()
{
 var elementNames = show_elements.arguments;
 for (var i=0; i<elementNames.length; i++)
  {
    var elementName = elementNames[i];
    document.getElementById(elementName).style.display='block';
  }
}

function hide_elements()
{
 var elementNames = hide_elements.arguments;
 for (var i=0; i<elementNames.length; i++)
  {
    var elementName = elementNames[i];
    document.getElementById(elementName).style.display='none';
  }
}

</script>
<a:navKunde rolle="${rolle }"></a:navKunde>
<h1>Kunden Übersicht.</h1> 
<p>Wählen Sie einen Kunden aus, um diesen zu löschen oder Passwort zurück zu setzen.</p>
<form action="KundenOperationsServlet" method="post">
<button class="button-dbae" id="Anzeigen" onclick="show_elements('Form')">Anzeigen</button>
</form>
<br />
<div id="Form">
<div class="table-responsive">
<table class="table">
			<tr>
				<th>Kunden_ID</th>
				<th>Vorname</th>
				<th>Nachname</th>
				<th>E-Mail</th>

			</tr>
			<c:forEach var="kunde" items="${kunden}">



				<tr>
					<td>${kunde.nutzer_id}</td>
					<td>${kunde.vorname }</td>				
					<td>${kunde.nachname}</td>
					<td>${kunde.email}</td>

				</tr>





			</c:forEach>
	</table>
</div>
<p>Leer optische Abgrenzungen
<form action="KundenOperationsServlet" method="get">
<label for="Auswahl" ><br> Auswahl:<br/></label>
<select name="auswahl" class="input-dbae">
<c:forEach var="kunde" items="${kunden}">
<option value="kunde.email">${kunde.email}</option>
</c:forEach>
</select>
<label for="passwort"><br>Initalpasswort setzen:<br></label>
<input type="password" class="input-dbae" name="passwort" placeholder="Passwort">
<input type="hidden" value="passwort" name="auslesen">
<button class="button-dbae">Passwort zurück setzen</button>
</form>
<br/>
<br/>

<p>Leer
<form action="KundenOperationsServlet" method="get">
<label for="Auswahl" ><br> Auswahl:<br/></label>
<select name="auswahl" class="input-dbae">
<c:forEach var="kunde" items="${kunden}">
<option value="kunde.email">${kunde.email}</option>
</c:forEach>
</select>
<input type="hidden" value="loeschen" name="auslesen">
<button class="button-dbae">Kunden loeschen?</button>
</form>
</div>
<a:modal messages="${messages}"></a:modal>
</body>
</html>