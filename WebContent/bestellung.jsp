<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
<head>
<link rel="stylesheet" href="css/generalLayout.css">
<link rel="stylesheet" href="css/profilnav.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
<a:bootstrap></a:bootstrap>
</head>
<body>
<a:navKunde rolle="${rolle }"></a:navKunde>
<div id = "container">
<a:profilnav></a:profilnav>
${kundeeingeloggt.vorname}
</div>


</body>
</html>