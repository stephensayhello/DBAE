<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="css/signup.css">
<link rel="stylesheet" href="css/generalLayout.css">
<link rel="stylesheet" href="css/artikeluebersicht.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
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

	<script>
		$(document).ready(function() {
			var test = "${pruefeLogin}";
			if (test) {

			}
		});
	</script>
	<a:navKunde rolle="${rolle }"></a:navKunde>
	<div class="container">
		<form action="Artikeluebersicht" method="get">
			<label class="checkbox-inline"><input type="checkbox" name="Produkt" value="1"> Schuhe<br></label>
			<label class="checkbox-inline"><input type="checkbox" name="Produkt" value="2"> Hosen<br></label>
			<label class="checkbox-inline"><input type="checkbox" name="Produkt" value="3"> Shirts<br></label>
			<br>
			<input class="button-dbae" type="submit" value="absenden" style="margin:10px;">
		</form>
	</div>



	<div class="container">
		<c:forEach items="${produktesortiertnachartnr}" varStatus="loop">
			<c:if test="${((loop.index )+1) % 3 == 1}">
				<div class="row"></div>
			</c:if>
			<a:artikel produkt="${produktesortiertnachartnr[loop.index]}"></a:artikel>
			<c:if test="${((loop.index )+1) % 3 == 0}"></c:if>
	
	</c:forEach>
	</div>
	
	

	<form action="Artikeluebersicht" method="get">
		<button type="submit" id="holeArtikel"></button>
	</form>



	<!-- <div class="clearfix">
<form action="produkt_anlegen.jsp">
    <button type="submit" class="button-dbae">produkt anlegen</button> 
</form>-->
<a:footer></a:footer>
</body>
</html>