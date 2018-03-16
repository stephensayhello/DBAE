<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/generalLayout.css">
<title>Insert title here</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
	<a:navigation></a:navigation>
	<table style="width: 100%">
		<tr>
			
			<th>Name</th>
			<th>Artikel-Nr</th>
			<th>Preis</th>
			<th>Anzahl</th>
			<th>Groesse</th>
			<th>Status</th>

		</tr>
		<c:forEach var="produkt" items="${warenkorbinhalt}">



			<tr>
				<td>${produkt.name}</td>
			    <td>${produkt.preis}</td>
				<td>${produkt.artikelnr}</td>
				<td>${produkt.anzahl}</td>
				<td>${produkt.groesse}</td>
				<td>${produkt.status}</td>

			</tr>





		</c:forEach>
	</table>
	<br/>
	<p>Der Gesamtpreis ihrer Bestellung lautet:</p>
	<br>
	<table>
		
		<th>Gesamtpreis</th>
	


		<tr>
			
			<td> ${warenkorbgesamtpreis} </td>

		</tr>


	</table>
	<a:modal messages="${messages}"></a:modal>
	<form action="WarenkorbServlet" method="post">




		
		<button type="submit" class="button-dbae">Bestellen</button><br/>
		<button class="button-dbae"> Warenkorb leeren</button>
	</form>

</body>
</html>