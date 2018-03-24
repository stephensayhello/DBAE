<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
    <%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<link rel="stylesheet" href="css/generalLayout.css">
<title>Support Anfrage</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
<a:navKunde rolle="${rolle }"></a:navKunde>

<div class="container">
<h1>Füllen Sie das Formular aus, um eine Anfrage an den Support zu senden.</h1>
<p>Ein Mitarbeiter des Support wird sich dann in Kürze bei Ihnen melden</p>
	<form action="SupportAnfrageServlet" method="post">
	<label for="anfrage"><br>Anfrage</br></label><input type="text" name="anfrage" class="input-dbae">
	<label for="anfragetext"><br>Text:</br></label><textarea rows="5" cols="50"  name="anfragetext" class="input-dbae"></textarea>
	
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
</body>
</html>