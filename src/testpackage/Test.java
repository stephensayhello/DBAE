package testpackage;

import de.classes.Adresse;
import de.classes.Kunde;
import de.classes.Nutzer;
import de.classes.Produkt;
import de.databaseOperations.AdresseOperations;
import de.databaseOperations.KundenOperations;
import de.databaseOperations.NutzerOperations;
import de.databaseOperations.ProduktOperations;
import de.utilities.mail;

public class Test {
	
	
	private static void erstelleTestKunde(){
	
		Adresse adresse = new Adresse("strasse", 88, 1111, "einort");
		
		Kunde kunde = new Kunde("email", "passwort", adresse, "vorname", "nachname");
		
		KundenOperations.anlegen(kunde);
		
	}
	
	
	
	public static void main(String[] args) {

		mail.SendMailTLS("benjamin.gajewski@yahoo.de", "blabla", "testtesttest");
		
		
	}

//	public static void main(String[] args) {
//		NutzerOperations nutzeroperation = new NutzerOperations();
//		int id = nutzeroperation.hoechsteID();
//		Nutzer nutzer = new Nutzer(id, "arsch@yahoo.de", "blablabla");
//
//		nutzeroperation.anlegen(nutzer);
//	}
}