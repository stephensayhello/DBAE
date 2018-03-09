package de.classes;
/**
 * Das ist die Klasse Admin.
 * @author Paul Blanke.
 *
 */
public class Admin extends Nutzer {

	private int adminkennung;
	
	
	public int getAdminkennung() {
		return adminkennung;
	}

	public void setAdminkennung(int adminkennung) {
		this.adminkennung = adminkennung;
	}

// Konstruktoren.	
	
	
	public Admin() {
		
	}
	
	public Admin(String name, String email, int adminkennung) {
		this.setName(name);
		this.setEmail(email);
		this.setAdminkennung(adminkennung);
		
	}
// Methoden
	
	
	/**
	 *  Diese Methode loescht einen vorgegebenden Kunden.
	 * @param kunde: der zu loeschende kunde.
	 */
	public void loescheKunde(Kunde kunde) {
		
	}
	
	
	
	/**
	 * Diese Methode set beim einen Kunden das Passwort zurück.
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
	public void loescheProdukte(Produkte produket) {
		
	}
	/**
	 * Diese Methode verändert die Daten eines Prdoukt bsp.
	 * den lieferstatus etc.
	 * @param produkte: Das ändere Produkt.
	 * @return das fertige Produkt.
	 */
	public Produkte anedereDaten(Produkte produkte) {
		return produkte;
	}
	
	/**
	 * Diese Methode legte ein neues Produkt p an.
	 * @return das neue Produkt.
	 */
	public Produkte neuPrdoukt() {
		return new Produkte();
	}
}
