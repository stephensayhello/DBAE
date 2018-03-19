package testpackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import de.classes.Admin;
import de.classes.Adresse;
import de.classes.Bestellung;
import de.classes.Kunde;
import de.classes.Nutzer;
import de.classes.Produkt;
import de.classes.Shirt;
import de.classes.Warenkorb;
import de.databaseOperations.AdminOperations;
import de.databaseOperations.AdresseOperations;
import de.databaseOperations.BestellungOperations;
import de.databaseOperations.KundenOperations;
import de.databaseOperations.NutzerOperations;
import de.databaseOperations.ProduktOperations;
import de.databaseOperations.WarenkorbOperations;
import de.logik.Regex;
import de.utilities.CreatePDF;
import de.utilities.SaltedHash;
import de.utilities.mail;

public class Test {

	private static void erstelleTestKunde() throws Exception {
		String saltedhash = SaltedHash.getSaltedHash("passwort");
		Adresse adresse = new Adresse("strasse", "88", 1111, "einort");

		Kunde kunde = new Kunde(saltedhash, "email", adresse, "vorname", "nachname");
        Warenkorb warenkorb = new Warenkorb(kunde);
		System.out.println(warenkorb.getWarenkorb_id());
		Bestellung bestellung = new Bestellung(warenkorb.getInhalt(), kunde);
		BestellungOperations.bestellunganlegen(bestellung);
		

	}
	
	private static void zudummfuerinsanceof (Produkt produkt){
		System.out.println(produkt instanceof Shirt);
	}

	public static void main(String[] args) throws InvalidPasswordException, IOException {
<<<<<<< HEAD
		// YYYY-MM-DD HH:MI:SS
		Produkt produkt = ProduktOperations.ladeProduktausdb(1);
		List<Produkt> produkte = new ArrayList<>();
		produkte.add(0, produkt);
	
		Kunde kunde = KundenOperations.kundeausdbholen(NutzerOperations.nutzerAusDbHolen("paul.blanke1@web.de"));
		Bestellung bestellung = new Bestellung(3, produkte, kunde);
		System.out.println(bestellung.getBestellnummer());
		BestellungOperations.anlegen(bestellung);
=======
		
>>>>>>> 877afe9ba57ac4d1afc014ed765da6b9b0fbdf07
	}
	
	
	
	private void testen() {
		try {
			erstelleTestKunde();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}