<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/generalLayout.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Produkt bearbeiten (Admin)</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
<a:changenav></a:changenav>
	<form action="ProduktBearbeitenServlet" method="post" style="border: 1px solid #ccc">
		<div class="container">
			<h1>Produkt bearbeiten</h1>
			<p>Bitte ändern Sie das Produkt</p>
			<hr>
			<div class="login">
				<label for="name"><b>Name:</b></label> <input class="input-dbae" type="text" placeholder="${produkt.name }" name="name">
				<label for="menge"><b>Menge:</b></label><input class="input-dbae" type="text" placeholder ="${produkt.menge}" name="menge">
				<label for="preis"><b>Preis:</b></label><input class="input-dbae" type="text" placeholder="${produkt.preis }" name="preis">
				
				<div class="clearfix">

					<button type="submit" class="button-dbae">Produkt ändern</button>
					 
					
				</div>
			</div>
			<a:modal messages="${messages}"></a:modal>

		</div>
	</form>

<br />
<form action ="ProdouktBearbeitenServlet" method="get">
<button type ="submit" class="button-dbae">Produkt löschen</button>
</form>

</body>
</html>