package de.classes;

/**
 * Diese Klasse bildet den Nutzer, der als Ausgangsbasis der Vererbung eine Elternklasse
 * bildet.
 * 
 * @author Stephen Galla
 *
 */
public class Nutzer {
	/**
	 * DB-ID.
	 */
	private int nutzer_id;
	/**
	 * Die E_Mail.
	 */
	private String email;
	/**
	 * Das passwort in Klartext.
	 */
	private String passwort;

	
// get und Set
	
	
	public int getNutzer_id() {
		return nutzer_id;
	}

	public void setNutzer_id(int nutzer_id) {
		this.nutzer_id = nutzer_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	/**
	 * Konstruktor
	 * @param nutzerid DB-Id
	 * @param passwort Das verwendete Passwort
	 * @param email Die E-mail als Benutzername
	 */
	public Nutzer(int nutzerid, String passwort, String email) {

		this.nutzer_id = nutzerid;
		this.email = email;
		this.passwort = passwort;

	}

	
}
