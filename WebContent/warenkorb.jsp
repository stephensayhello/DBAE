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
<table style="width:100%"> 
<tr>
    <th>ID</th>
    <th>Name</th> 
    <th>Anzahl</th>
    <th>Groesse</th>
    <th>Preis</th>
    <th>Status</th>
    
  </tr>
<c:forEach var="produkt" items="${warenkorbinhalt}" >
		
		 
   
  <tr><td> ${produkt.produkt_id} </td>
		<td> ${produkt.name} </td>
		<td> ${produkt.anzahl}	</td>	
		<td> ${produkt.groesse} </td>
		<td> ${produkt.preis)}   </td>
		<td> ${produkt.status}  </td>
		</tr>
		
		
 

		
	</c:forEach></table>
<form action="WarenkorbServlet" method="post">
	




<input type ="submit">
</form>

</body>
</html>