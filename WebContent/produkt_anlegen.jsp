<%@ page language="java" contentType="text/html; charset=Windows-1252"
	pageEncoding="Windows-1252"%>
<%@taglib uri="/WEB-INF/tag.tld" prefix="a"%>
<!DOCTYPE html >
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author" content="Paul Blanke, Stephen Galla, Benjamin Gajewski">
<title>Neue Produkt einpflegen(Admin)</title>
<link rel="stylesheet" href="css/generalLayout.css">
<link rel="stylesheet" href="css/produkt_anlegen.css">


<a:bootstrap></a:bootstrap>


<script type="text/javascript">
	function adjustSizeForProduct(kategorie) {
		var specificSizes = [];

		if (kategorie == 1) {
			specificSizes = [ "S", "M", "L", "XL", "XXL" ];
		} else if (kategorie == 2) {
			specificSizes = [ 28, 30, 32, 34, 36, 38, 40, 42 ];
		} else if (kategorie == 3) {
			for (var i = 38; i < 46; i++) {
				specificSizes.push(i);
			}
		}

		//document.getElementById('groessenCheck').options.length = 0;

		var sel = document.getElementById('groessenCheck');
		var html = "";
		for (var i = 0; i < specificSizes.length; i++) {
			html += "<div class='container'><div class='row'><div class='col-md-12'><div class='input-group'><span class='input-group-addon'><input type='checkbox' name='checkGroesse' value="+specificSizes[i]+"></span><span class='input-group-addon'><label for='tbox'>"
					+ specificSizes[i]
					+ "</label></span><input name='inputMenge' type='number' class='form-control' placeholder='Menge'><input name='p_versanddauer' type='number' class='form-control' placeholder ='Versanddauer'></div></div></div></div>";
		}

		sel.innerHTML = html;
	}
</script>


</head>
<body>
	<a:navKunde rolle="${rolle }"></a:navKunde>

	<div class="container">


		<form action="ProduktAnlegenServlet" method="post"
			enctype="multipart/form-data">
			<h1>Produkt hinzufügen</h1>
			<p>bitte ausfüllen um produkt hinzuzufügen</p>
			<hr>

			<div class="container">
				<label for="fileUpload" class="customFileUpload"> <input
					type="file" id="fileUpload" name="uploadFile" /></label> 
			</div>


			<div class="prod0">
				<label for="p_name">Bezeichnung</label> <input class="input-dbae"
					type="text" name="p_name" size=3> <label
					for="p_beschreibung">Beschreibung</label> <input class="input-dbae"
					type="text" name="p_beschreibung" size=3>



			</div>
			<div class="prod1">


				<label for="p_preis">Preis</label> <input class="input-dbae"
					type="number" step="0.01" name="p_preis" size=3>


			</div>



			<div class="prod2">
				<label for="p_kategorie">Kategorie</label> <select
					name="p_kategorie" class="form-control" id="p_kategorie"
					onchange="adjustSizeForProduct(this.value)">
					<option value=0></option>
					<option value=1>Shirt</option>
					<option value=2>Hose</option>
					<option value=3>Schuhe</option>
				</select>

				<div id="groessenCheck"></div>


				<!-- 				 <label for="p_groesse">Größe</label> <select class="form-control"
					id="p_groesse" name="p_groesse"></select> -->
			</div>

			<div>
				<button type="reset" class="button-dbae">Eingabe löschen</button>
				<button type="submit" class="button-dbae">Produkt
					hinzufügen</button>
			</div>


		</form>
	</div>
	<a:footer></a:footer>
</body>
</html>