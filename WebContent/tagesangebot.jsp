<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
    <%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
<a:navKunde rolle="${rolle }"></a:navKunde>
<!-- zeige ein produkt und die Bewertung dazu -->
Nur heute im Tages Angebot!
<div class="container">

	<a:artikel produkt="${produkte[2]}"></a:artikel>
</div>


</body>
</html>