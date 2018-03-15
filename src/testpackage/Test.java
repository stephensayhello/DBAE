package testpackage;

import de.classes.Adresse;
import de.classes.Kunde;
import de.classes.Nutzer;
import de.classes.Produkt;
import de.classes.Shirt;
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
	
	private static void zudummfuerinsanceof (Produkt produkt){
		System.out.println(produkt instanceof Shirt);
	}

	public static void main(String[] args) {
		Shirt shirt = new Shirt(1,"adolf","klasse",10.00,"xl",3);
		zudummfuerinsanceof(shirt);
		System.out.println(shirt.getProdukt_id());
	

	}

}