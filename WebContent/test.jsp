<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="TestFiltersServlet" method="post">
<label>Eingabe:</label><br/>
<input type="text" name="eingabe">
<br/>
<button value="submit">Los</button>
</form>
<p>${nutzer.email}</p>

</body>
</html>