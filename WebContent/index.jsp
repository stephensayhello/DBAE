<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<!-- Einbindung für Bootstrap -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Das neueste kompilierte und minimierte CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- Optionales Theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<!-- Das neueste kompilierte und minimierte JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


<title>DBAE Projekt</title>
</head>
<body>
<h1>Web-Shop</h1>

<!-- Achtung eine Nav bar mit Bootstrap -->
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Titel und Schalter werden für eine bessere mobile Ansicht zusammengefasst -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Navigation ein-/ausblenden</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Titel</a>
    </div>

    <!-- Alle Navigationslinks, Formulare und anderer Inhalt werden hier zusammengefasst und können dann ein- und ausgeblendet werden -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="index.jsp">Startseite</a>
        	<a href="Artikeluebersicht.jsp">Artikel</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Menü <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="index.jsp">Startseite</a></li>
            <li><a href="Artikeluebersicht">Artikelübersicht</a></li>
            <li><a href="#">Irgend</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Abgetrennter Link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="LoinIn.jsp">Login</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Suchen">
        </div>
        <button type="submit" class="btn btn-default">Los</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="LoginIn.jsp">LoginIn</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>


<br/>

<form action="EingabeServlet" method="post" id="formLogin" >
<label>Name:</label><br><input type="text" name="bname"><br/>
<label>Passwort:</label><br/><input type="password" name="passwort">
<br/><input type="submit">
</form>
<br>
<p>Willkommen im SportWeb. Ihr Fachgeschäft für jede Sportart! Unkomplizierter und schneller Onlineeinkauf.</p><br>

<br>
</body>
</html>