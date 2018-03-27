<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>

<%@taglib uri="/WEB-INF/tag.tld" prefix="a"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
<head>

<link rel="stylesheet" href="css/generalLayout.css">
<meta name="author"
	content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bewertungsuebersicht</title>
<link rel="icon" href="img/Logo.png">
<a:bootstrap></a:bootstrap>
</head>

<body>
	<a:navKunde rolle="${rolle }"></a:navKunde>
	<div class="container">
	
	<c:choose>
	<c:when test="${not empty bewertungen }">
		<h1>Bewertungsübersicht für ${artikelname}</h1>


		<c:forEach items="${bewertungen}" varStatus="loop">
			<a:bewertungsBox bewertung="${bewertungen[loop.index]}"></a:bewertungsBox>

		</c:forEach>
	</c:when>
	<c:otherwise>
		<div class="container">
				<div class="alert alert-info">
					<strong>Info!</strong> Noch keine Bewertungen vorhanden.
				</div>
			</div>
	</c:otherwise>
	</c:choose>



	</div>
	<a:footer></a:footer>
</body>
</html>