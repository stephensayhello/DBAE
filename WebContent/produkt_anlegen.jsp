<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tag.tld" prefix="a"%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="css/signup.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<a:bootstrap></a:bootstrap>

<script>
	// auswahl funktion
	function auswahl(s1, s2) {
		var s1 = document.getElementById(s1);
		var s2 = document.getElementById(s2);

		//initialisierung von der größenauswahl als leer
		s2.innerHTML = "";
		if (s1.value == "shirt") {
			//array besteht aus "value|label" die dann ins slct2 eingefügt werden
			var optionArray = [ "|", "xs|XS", "s|S", "m|M", "l|L", "xl|XL",
					"xxl|XXL" ];
		} else if (s1.value == "hose") {
			var optionArray = [ "|", "28|28", "30|30", "32|32", "34|34",
					"36|36", "38|38" ];
		} else if (s1.value == "schuhe") {
			var optionArray = [ "|", "36|36", "37|37", "38|38", "39|39",
					"40|40", "41|41", "42|42", "43|43", "44|44", "45|45",
					"46|46", "47|47" ];
		}
		//"VALUE|label" mit split an der stelle | aufteilen und in array setzen 
		for ( var option in optionArray) {
			var pair = optionArray[option].split("|");
			var newOption = document.createElement("option");
			newOption.value = pair[0];
			newOption.innerHTML = pair[1];
			s2.options.add(newOption);
		}
	}
</script>

</head>
<body>
	<a:navigation></a:navigation>

	<div class="name">

		<form action="ProduktAnlegenServlet" method="get">
			<h1>Produkt hinzufügen</h1>
			<p>bitte ausfüllen um produkt hinzuzufügen</p>
			<hr>
			<div >
				<label >Bezeichnung</label> <input type="text" name="p_name">

				<label >Beschreibung</label> <input type="text" name="p_beschreibung">

				<label>Preis</label> 
				<input type="number" step="0.01" name="p_preis">
				
				<label>Menge</label>
				<input type="number" name="p_menge">
				
<div>
				<!-- select box für produkte: abhören der auswahl und weiterleitung in auswahl() funktionen -->
				<label>Kathegorie</label> <select id="p_art" name="p_art"
					onchange="auswahl(this.id,'p_groesse')">
					<option value=""></option>
					<option value="shirt">Shirt</option>
					<option value="hose">Hose</option>
					<option value="schuhe">Schuhe</option>
				</select>

				<!-- select box für größen -->
				<label>größe</label> <select id="p_groesse" name="p_groesse"></select>
</div>

<div>
				<button type="reset" class="signupbtn">Eingabe löschen</button>
				<button type="submit" class="signupbtn">Produkt hinzufügen</button>
</div>

			</div>
		</form>
	</div>
</body>
</html>