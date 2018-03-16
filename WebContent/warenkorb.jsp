<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
<a:navigation></a:navigation>
<c:forEach var="produkt" items="${warenkorbinhalt}" >
		${produkt.produkt_id}
		${produkt.name}
		${produkt.beschreibung}
		${produkt.anzahl}
		${produkt.preis}
		${produkt.status}
		${produkt.groesse}
		
		
		
	</c:forEach>
<form action="WarenkorbServlet" method="post">
	




<input type ="submit">
</form>

</body>
</html>