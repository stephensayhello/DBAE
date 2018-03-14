<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<script>
		$(document).ready(function() {
			var test = "${test}";
			if (test === "") {
				$("#holeArtikel").click();
			}
		});
	</script>

	<a:navigation></a:navigation>

<div class="container">
	<c:forEach items="${produkte}" varStatus="loop">
		<c:if test="${(loop.index +1) % 3 == 1}">
			<div class="row">
		</c:if>
		<a:artikel produkt="${produkte[loop.index]}"></a:artikel>
		<c:if test="${(loop.index +1) % 3 == 0}">
			</div>
		</c:if>
	</c:forEach>
</div>

	<form action="Artikeluebersicht" method="get">
		<button type="submit" id="holeArtikel"></button>
	</form>

	<p>${test}</p>


	<!-- <div class="clearfix">
<form action="produkt_anlegen.jsp">
    <button type="submit" class="button-dbae">produkt anlegen</button> 
</form>
</div> -->
</body>
</html>