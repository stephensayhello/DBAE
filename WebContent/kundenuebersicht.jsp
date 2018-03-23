<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
    <%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/generalLayout.css">
<title>Kunden �bersicht</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
<script>

$(document).ready(function() {
	var klick = "${klick}";
	if(klick=="show") {
		show_elements('Form');
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
<h1>Kunden �bersicht.</h1> 
<p>W�hlen Sie einen Kunden aus, um diesen zu l�schen oder Passwort zur�ck zu setzen.</p>
<form action="KundenOperationsServlet" method="post">
<button class="button-dbae" id="Anzeigen" onclick="show_elements('Form')" id="anzeigen">Anzeigen</button>
</form>
<br />
<div id="Form" class="container" style ='display: none'>

<table class="table">
			<tr>
				<th>ID</th>
				<th>E-Mail</th>
				<th>Vorname</th>
				

			</tr>
</table>			
<form action="KundenOperationsServlet" method="get">
<table class="table">			
			<c:forEach var="kunde" items="${kunden}">

				<td><input type="radio" value="${kunde.email}" name="auswahl"><td><td>${kunde.nutzer_id}</td><td>   ${kunde.email} </td>
				<td>  ${kunde.vorname}</td><br/> </input><td/>
					
				
			</c:forEach>
</table>
	<label ><br>Passwort<br/></label><input class="input-dbae"  type="password" name="passwort">
	<button class ="button-dbae"><br>Passwort �ndern</br></button>
	<input type="hidden" value="passwort">
</form>









<br />
<br />
<form action="KundenOperationsServlet" method="get">
<label for="Auswahl" ><br> Auswahl:<br/></label>
<select name="loeschen" class="input-dbae">
<c:forEach var="kunde" items="${kunden}">
<option value="kunde.email">${kunde.email}</option>
</c:forEach>
</select>
<input type="hidden" value="loeschen" name="auslesen">
<button class="button-dbae">Kunden loeschen?</button>
</form>
</div>

</body>
</html>