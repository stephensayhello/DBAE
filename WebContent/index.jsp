<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
	
    <%@taglib uri="/WEB-INF/tag.tld" prefix="a" %> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
<head>

<link rel="stylesheet" href="css/generalLayout.css">
<meta name="author" content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Startseite</title>
<a:bootstrap></a:bootstrap>
</head>

<body>
<a:navKunde rolle="${rolle }"></a:navKunde>
<div class="container">
<h1>Willkommen im SportWebShop. <br/> Wir freuen uns, dass Sie den Weg zu uns gefunden haben</h1>
<p class="input-dbae">Sport. Ein Thema, was unseren Alltag prägt. Wir unterstützen  Sie auf Ihrem Weg zum einen gesünderen und besseren Lebenstil !
 Mit unseren Produkt steigern wir Ihre Sport Qualität enorm und verhelfen Ihnen zu neuen Bestleistungen.</p>
<br>
<h2>Newsfeed</h2>
<p class="input-dbae">	 - 	Forscher haben bestätigt, dass Schwimmen eine gelenkschonende Alternativsport ist im Vergleich zum Laufsport.
<br>
<p class="input-dbae">	- 	Sparen Sie mit uns 10 % für Ihren ersten Marathon. Senden Sie uns dazu eine Kontaktanfrage und bekommen Sie noch heute 
Ihren persönlichen Gutscheincode.
<br>
<p class="input-dbae">Nutzen Sie außerdem unser Kontaktformular bei Fragen zu unseren Produkten. Wir helfen Ihnen rund um die Uhr innerhalb von 2 Stunden!
<br />


<p> - ... Oder werfen Sie einen Blcik auf unserer Tagesangebot: 
		 Nur begrenzt gültig innerhalb von 24 Std. Sie sparen bis zu 25 % gegenüber dem UVP.	
		 <br />
<form action="TagesAngebotServlet" method="post">
<button class="button-dbae">zum Tagesangebot</button>
</form>
</div>
<a:footer></a:footer>
</body>
</html>