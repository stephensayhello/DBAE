<%@ page language="java" contentType="text/html; charset=Windows-1252"
    pageEncoding="ISO-8859-1"%>
    <%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<link rel="stylesheet" href="css/generalLayout.css">
<title>Tagesangebot</title>
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
<a:navKunde rolle="${rolle} "></a:navKunde>
<div class="container">
<h1>Nur für heute im Tagesangebot ! Bestellen Sie jetzt.</h1>
<form action="TagesAngebotServlet" method="get">
<button class="button-dbae" id="Anzeigen">Ansehen</button>
</form>
</div>
</body>
</html>