<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<script>
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
<div id="text_html">
<p>
<button onclick="hide_elements('text_html')">Text verstecken</button>
</p> 
<p> Hihi versteckt.</p>

</div>
<br />
<form>
<label for="auswahl"><br>Auswahl<Br/></label><input type="checkbox" name="auswahl" value="Kunde A">Testen</input>
<br />

<button>Absenden</button>
</form>

<form action = "KundenOperationsServlet" method="get">
<label class= "input-dbae">Bitte wählen Sie einen Kunden aus</label>
<c:forEach var="kunde" items="${kunden}">
<input type="checkbox" name="auswahl">


</c:forEach>




</form>

<br />



<p id="linkShow_xhtml">
		<a href="#"  title="Anzeigen" onclick="show_elements('text_xhtml');">Text anzeigen</a>
	</p>
	<div id="text_xhtml" style='display: none;'>
	<p>
		Text der bei einem klick auf Text anzeigen erscheint.
	</p>
	</div>



</body>
</html>