<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="css/signup.css">
<link rel="stylesheet" href="css/generalLayout.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>

<a:bootstrap></a:bootstrap>
</head>
<body>
<a:navigation></a:navigation>

<div class="clearfix">
<form action="produkt_anlegen.jsp">
    <button type="submit" class="button-dbae">produkt anlegen</button> 
</form>
</div>
<br/>


<div class="">
<h1>Produktauswahl:</h1>
<p>Bitte wählen Sie eine Kathegorie aus, 
um sich weitere Artikel dieser Kathegorie anzeigen zu lassen.</p>
<form action="ProduktAuswahlServlet" method="post">
<label for="auswahl">Auswahl der Kathegorie:</label><br/>	
<select  name="auswahl">
	<option>Schuhe</option>
	<option>T-Shirts</option>
	<option>Hosen</option>
</select>
<br/>
<button type="submit" class="button-dbae">Kathegorieauswahl</button>
</form>
</div>
<br / >
<a:modal messages="${messages}"></a:modal>
</body>
</html>