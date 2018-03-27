<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/generalLayout.css">
<meta name="author"
	content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<title>Warenkorb</title>
<link rel="icon" href="img/Logo.png">
<a:bootstrap></a:bootstrap>
</head>
<body>
	<a:navKunde rolle="${rolle }"></a:navKunde>
	<a:modal messages="${messages}"></a:modal>
	<c:choose>
		<c:when test="${not empty warenkorb.inhalt}">
			<div class="container">
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<tr>

							<th>Preis</th>
							<th>Name</th>
							<th>Artikel-Nr</th>
							<th>Anzahl</th>
							<th>Groesse</th>
							<th>Status</th>

						</tr>
						<c:forEach var="produkt" items="${warenkorb.inhalt}">



							<tr>
								<td>${produkt.preismitanzahlineuro}</td>
								<td>${produkt.name}</td>
								<td>${produkt.artikelnr}</td>
								<td>${produkt.anzahl}</td>
								<td>${produkt.groesse}</td>
								<td>${produkt.status}</td>

							</tr>





						</c:forEach>
						<th>Gesamtpreis:</th>



						<tr>

							<td>${warenkorb.gesamtpreisineuro}</td>

						</tr>

					</table>

					<c:if test="${warenversanddauer>0}">
		Der Versand dauert voraussichtlich ${warenversanddauer} Tage.
		</c:if>
					<div>
						<form action="WarenkorbServlet" method="post">





							<button type="submit" class="button-dbae">Bestellen</button>


						</form>
						<form action="WarenkorbServlet" method="get">






							<button class="button-dbae">Warenkorb leeren</button>

						</form>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="container">
				<div class="alert alert-info">
					<strong>Info!</strong> Der Warenkorb ist leer.
				</div>
			</div>

		</c:otherwise>
	</c:choose>

	<a:footer></a:footer>
</body>
</html>