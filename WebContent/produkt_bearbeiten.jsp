<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@  taglib prefix="a" uri="/WEB-INF/tag.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<link rel="stylesheet" href="css/generalLayout.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author"
	content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<title>Produkt bearbeiten (Admin)</title>
<link rel="icon" href="img/Logo.png">
<a:bootstrap></a:bootstrap>
</head>
<script type="text/javascript">
	function validateForm(event, form) {
		event.preventDefault();
		if (confirm("Wollen Sie das Produkt wirklich löschen ?")) {
			form.submit();
		} else {
			return false;
		}
	}
</script>
<body>
	<a:modal messages="${messages}"></a:modal>
	<a:navKunde rolle="${rolle }"></a:navKunde>
	<div class="container">
		<h1 class="header">Produkt bearbeiten</h1>
		<p>Bitte ändern Sie das Produkt</p>

		<hr>

		<div>
			<form action="ProduktBearbeitenServlet" method="post">

				<p>Name:${produkt.name}</p>
				<br>
				<p>Preis: ${produkt.preis }</p>
				<br> <label for="menge"><b>Menge:</b></label><input
					class="input-dbae" type="text" value="${produkt.menge}"
					name="menge"> <label for="versanddauer"><b>Versanddauer:</b></label><input
					class="input-dbae" value="${produkt.versanddauer }" type="text"
					name="versanddauer"> <label for="status"><b>Status</b></label>
				<select class="input-dbae" name="status">
					<option value="nix" class="input-dbae">Status</option>
					<option value="Lieferbar" class="input-dbae">Lieferbar</option>
					<option value="Nicht Lieferbar" class="input-dbae">Nicht
						Lieferbar</option>
				</select>




				<button type="submit" class="button-dbae">Produkt ändern</button>


				<a:modal messages="${messages}"></a:modal>


			</form>

			<form action="ProduktBearbeitenServlet" method="get"
				onSubmit="return validateForm(event, this)">
				<button type="submit" class="button-dbae">Produkt löschen</button>
			</form>
		</div>
	</div>


	<a:footer></a:footer>
</body>
</html>