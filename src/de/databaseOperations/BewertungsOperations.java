package de.databaseOperations;

import java.util.List;

import de.classes.Bewertung;
import de.classes.Kunde;

public class BewertungsOperations {
	
	private final String BEWERTUNG_NACH_ID = "SELECT * FROM bewertung WHERE bstnr = ?, kundennr = ?;";
	private final String BEWERTUNGEN = "SELECT * FROM bewertung;";
	
	
	public static Bewertung holeBewertung(Kunde kunde) {
		return null;
	}
	
	
	public static List<Bewertung> holeAlleBewetung() {
		return null;
	}
}
