package testpackage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import de.classes.Admin;
import de.classes.Adresse;
import de.classes.Bestellung;
import de.classes.Bewertung;
import de.classes.Kunde;
import de.classes.Nutzer;
import de.classes.Produkt;
import de.classes.Shirt;
import de.classes.Warenkorb;
import de.databaseOperations.AdminOperations;
import de.databaseOperations.AdresseOperations;
import de.databaseOperations.BestellungOperations;
import de.databaseOperations.BewertungsOperations;
import de.databaseOperations.KundenOperations;
import de.databaseOperations.NutzerOperations;
import de.databaseOperations.ProduktOperations;
import de.databaseOperations.ProduktUpdateOperations;
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

	private static void zudummfuerinsanceof(Produkt produkt) {
		System.out.println(produkt instanceof Shirt);
	}

	public static void main(String[]args) {
		
	}
}
