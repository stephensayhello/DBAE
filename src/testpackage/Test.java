package testpackage;

import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import de.classes.Kunde;
import de.classes.Produkt;
import de.databaseOperations.BewertungsOperations;
import de.databaseOperations.KundenOperations;
import de.databaseOperations.NutzerOperations;
import de.databaseOperations.ProduktOperations;
import de.databaseOperations.ProduktUpdateOperations;
import de.utilities.SaltedHash;

/**
 * Eine Testklasse mit der Funktionen ohne den Weg u&eber den HTMl Code gepr&ueft werden 
 * k&oennen.
 * @author alle
 *
 */
public class Test {

	public static void main(String[]args) {
		
	List<Produkt> produkte = ProduktOperations.ladeProdukteAusDatenbankmitArtnr(5);
	System.out.println(produkte);
	for (Produkt produkt : produkte) {
		ProduktUpdateOperations.entferneProdukt(produkt);
	}
		
	}
}
