<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
    <%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/generalLayout.css">
<title>Tagesangebot</title>
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
<!-- zeige ein produkt und die Bewertung dazu -->

<div class="container">
<h1>Nur heute im Tageangebot !</h1>
	<form action="TagesAngebotServlet" method="post">
	<button class="button-dbae">Anzeigen</button>
	</form>

<div id="Form" style ='display: none'>
<p>Hier kommt später ein Tag rein</p>
<a:tagesangebot produkt="${produkt} " durchschnitt="${durchschnitt}"></a:tagesangebot>
<div class="table-responsive">
<table class="table">
			<tr>

				<th>Punkte</th>
				<th>Kommentar</th>
				

			</tr>
			<c:forEach var="bewertung" items="${bewertungen}">



				<tr>
					<td>${bewertung.punkte}</td>				
					<td>${bewertung.kommentar}</td>
					
				</tr>





			</c:forEach>
	</table>
</div>
</div>
</div>
</body>
</html>