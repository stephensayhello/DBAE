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
<a:changenav></a:changenav>
<h1>Alle verfuegbare Produkte anzeigen</h1>
<div class="container">
<form action="ProduktÜbersichtServlet" method="post">
<button value="submit" class="button-dbae">Anzeigen</button>
<button class="button-dbae">Loeschen</button>
</form>
<br/>
</div>
<br />
<div class="container"><div class="table-responsive">

</div>	
</div>

<br />


<a:modal messages="${messages}"></a:modal>
</body>
</html>