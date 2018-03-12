<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tag.tld" prefix="a"%>
<!DOCTYPE html >
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>produkte_anlegen</title>
<link rel="stylesheet" href="css/produkt_anlegen.css">
<a:bootstrap></a:bootstrap>


<script type="text/javascript">
	function adjustSizeForProduct(kategorie) {
		var specificSizes = [];

		if (kategorie == 1) {
			specificSizes = [ "S", "M", "L", "XL", "XXL" ];
		} else if (kategorie == 2) {
			specificSizes = [ 28, 30, 32, 34, 36, 38, 40, 42 ];
		} else {
			for (var i = 38; i < 46; i++) {
				specificSizes.push(i);
			}
		}

		document.getElementById('p_groesse').options.length = 0;

		var sel = document.getElementById('p_groesse');
		for (var i = 0; i < specificSizes.length; i++) {
			var opt = document.createElement('option');
			opt.innerHTML = specificSizes[i];
			opt.value = specificSizes[i];
			sel.appendChild(opt);
		}
	}
</script>



</head>
<body>
	<a:navigation></a:navigation>

	<div class="name">

		<form action="ProduktAnlegenServlet" method="post">
			<h1>Produkt hinzufügen</h1>
			<p>bitte ausfüllen um produkt hinzuzufügen</p>
			<hr>
			<div class = "prod0">
				<label for="p_name">Bezeichnung</label> <input type="text"
					name="p_name" size=3> <label for="p_beschreibung">Beschreibung</label>
				<input type="text" name="p_beschreibung" size=3>
				

			</div>
			<div class = "prod1">


				<label for="p_preis">Preis</label> <input type="number" step="0.01"
					name="p_preis" size=3> <label for="p_menge">Menge</label> <input
					type="number" name="p_menge" size=3>

			</div>
			<div class= "prod3">
				<label for = "p_kategorie">Kategorie</label> <select name="p_kategorie"
					onchange="adjustSizeForProduct(this.value)">
					<option value=0></option>
					<option value=1>Shirt</option>
					<option value=2>Hose</option>
					<option value=3>Schuhe</option>
				</select> <label>größe</label> <select id="p_groesse" name="p_groesse"></select>
			</div>

			<div>
				<button type="reset" class="signupbtn">Eingabe löschen</button>
				<button type="submit" class="signupbtn">Produkt hinzufügen</button>
			</div>


		</form>
	</div>
</body>
</html>