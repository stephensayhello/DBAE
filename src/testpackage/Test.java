package testpackage;

import de.classes.Adresse;
import de.classes.Kunde;
import de.classes.Nutzer;
import de.classes.Produkt;
import de.databaseOperations.AdresseOperations;
import de.databaseOperations.KundenOperations;
import de.databaseOperations.NutzerOperations;
import de.databaseOperations.ProduktOperations;
import de.logik.Regex;
import de.utilities.SaltedHash;
import de.utilities.mail;

public class Test {

	private static void erstelleTestKunde() throws Exception {
		String saltedhash = SaltedHash.getSaltedHash("passwort");
		Adresse adresse = new Adresse("strasse", "88", 1111, "einort");

		Kunde kunde = new Kunde(saltedhash, "email", adresse, "vorname", "nachname");

		// KundenOperations.anlegen(kunde);

	}

	public static void main(String[] args) {
		String email = "benjamin.gajewski@yahoo.de";
		Nutzer nutzer = NutzerOperations.nutzerAusDbHolen(email);
		System.out.println(NutzerOperations.nutzeristKunde(nutzer));
		
	

	}

}