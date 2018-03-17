package testpackage;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import de.classes.Admin;
import de.classes.Adresse;
import de.classes.Kunde;
import de.classes.Nutzer;
import de.classes.Produkt;
import de.classes.Shirt;
import de.databaseOperations.AdminOperations;
import de.databaseOperations.AdresseOperations;
import de.databaseOperations.KundenOperations;
import de.databaseOperations.NutzerOperations;
import de.databaseOperations.ProduktOperations;
import de.logik.Regex;
import de.utilities.CreatePDF;
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

	public static void main(String[] args) throws InvalidPasswordException, IOException {
			Nutzer nutzer = NutzerOperations.nutzerAusDbHolen("admin@test.de");
			Admin admin = AdminOperations.holeAdminausDB(nutzer);
			System.out.println(admin.getNutzer_id());
			
			System.out.println(admin.getPasswort());
		
	}

}