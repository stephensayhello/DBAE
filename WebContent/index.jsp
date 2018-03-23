<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
    <%@taglib uri="/WEB-INF/tag.tld" prefix="a" %> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
<head>

<link rel="stylesheet" href="css/generalLayout.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
<a:navKunde rolle="${rolle }"></a:navKunde>
<h1>Willkommen im SportWebShop</h1>
<h2>Der Newsfeed. Hier finden Sie die neusten Informationen über uns.</h2>
<p> Admin- Kennung: admin@test.de und Projekt2018
<br>


	
<form action="TagesAngebotServlet" method="post">
<button class="button-dbae">zum Tagesangebot</button>
</form>

</body>
</html>