package de.classes;
/**
 * Das ist die Klasse Admin.
 * @author Paul Blanke.
 *
 */
public class Admin extends Nutzer {

	
	private String name;
	
	
	
	public Admin(int nutzer_id, String email, String passwort,String name) {
		super( nutzer_id, email, passwort);
		this.name = name;
		
	}


// Konstruktoren.	
	
	
	
	
	
// Methoden
	
	
	/**
	 *  Diese Methode loescht einen vorgegebenden Kunden.
	 * @param kunde: der zu loeschende kunde.
	 */
	public void loescheKunde(Kunde kunde) {
		
	}
	
	
	
	/**
	 * Diese Methode set beim einen Kunden das Passwort zur�ck.
	 * 
	 * @param kunde: der zu aendere Kunde.
	 * @return der geaenderte Kunde.
	 */
	public Kunde anederePasswort(Kunde kunde) {
		return kunde;
	}
	
	/**
	 *  Diese Methode loescht ein Produkt.
	 * @param produkte: das Produkt.
	 */
	public void loescheProdukte(Produkt produkte) {
		
	}
	/**
	 * Diese Methode ver�ndert die Daten eines Prdoukt bsp.
	 * den lieferstatus etc.
	 * @param produkte: Das �ndere Produkt.
	 * @return das fertige Produkt.
	 */
	public Produkt anedereDaten(Produkt produkte) {
		return produkte;
	}
}
