<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
    <%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<link rel="stylesheet" href="css/generalLayout.css">
<title>Support Anfrage</title>
<link rel="icon" href="img/Logo.png">
<a:bootstrap></a:bootstrap>
</head>
<body>
<a:navKunde rolle="${rolle }"></a:navKunde>

<div class="container">
<h1>F�llen Sie das Formular aus, um eine Anfrage an den Support zu senden.</h1>
<p>Ein Mitarbeiter des Support wird sich dann in K�rze bei Ihnen melden</p>
	<form action="SupportAnfrageServlet" method="post">
	<label for="anfrage"><br>Anfrage</label><input type="text" name="anfrage" class="input-dbae">
	<label for="anfragetext"><br>Text:</label><textarea rows="5" cols="50"  name="anfragetext" class="input-dbae"></textarea>
	
	<label for="grund:">Grund der Anfrage</label>
	<select name="grund" class ="input-dbae">
		<option>Beschwerde</option>
		<option>Probleme beim Bestellungsprozess</option>
		<option>Reklamation</option>
		<option>Sonstiges</option>
		
	</select>
	
	<button value="submit" class="button-dbae">Anfrage Absenden</button>
	</form>
	
</div>
<a:modal messages="${messages}"></a:modal>
<a:footer></a:footer>
</body>
</html>