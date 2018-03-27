<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author"
	content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<link rel="stylesheet" href="css/generalLayout.css">
<title>Kunden Uebersicht</title>
<link rel="icon" href="img/Logo.png">
<a:bootstrap></a:bootstrap>
</head>
<body>
	<script>
		$(document).ready(function() {
			var klick = "${klick}";
			if (klick === "") {
				$("#anzeigen").click();
			}
		});

		function onHidde() {
			document.getElementById("unsichtbar").style.visibility = "hidden";
		}

		function onShow() {
			document.getElementById().style.visibility = "visible";
		}
	</script>
	<a:navKunde rolle="${rolle }"></a:navKunde>

	<div class="container">
		<h1>Kundenübersicht</h1>
		<form action="KundenOperationsServlet" method="get">
			<button value="submit" class="button-dbae" id="anzeigen">Anzeigen</button>

		</form>
		<br />
	</div>
	<br />
	<div class="container">
		<div class="table-responsive">
			<table class="table table-striped table-bordered">
				<tr>

					<th>KNR</th>
					<th>Name</th>
					<th>Email</th>
					<th>Anschrift</th>
					<th>Passwort zurücksetzen</th>
					<th>Kunde Löschen</th>


				</tr>
				<c:forEach var="kunde" items="${kunden}">



					<tr>
						<td>${kunde.nutzer_id}</td>
						<td>${kunde.nachname}</td>
						<td>${kunde.email}</td>
						<td>${kunde.adresse.strasse}${kunde.adresse.hausnummer}${kunde.adresse.plz}${kunde.adresse.ort}</td>
						<td>
							<form action="KundeBearbeitenServlet" method="get">
								<button value="submit" class="button-dbae">
									<span class="glyphicon glyphicon-share-alt"></span>
								</button>
								<input name="auswahlkunde" type="hidden"
									value="${kunde.nutzer_id}" />
							</form>




						</td>
						<td>
							<form action="KundenOperationsServlet" method="post">
								<button value="submit" class="button-dbae">
									<span class="glyphicon glyphicon-trash"></span>
								</button>
								<input name="auswahl" type="hidden" value="${kunde.nutzer_id}" />
							</form>

						</td>

					</tr>





				</c:forEach>
			</table>
		</div>
	</div>


	<a:footer></a:footer>

</body>
</html>