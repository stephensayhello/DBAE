package de.classes;

import de.databaseOperations.NutzerOperations;
/**
 * Diese Klasse bildet die Rolle Kunde ab.
 * @author Stephen Galla
 *
 */
public class Kunde extends Nutzer {
	/**
	 * Eine Klasse objekt Adresse @see @Adresse
	 */
	private Adresse adresse;
	/**
	 * Vorname.
	 */
	private String vorname;
	/**
	 * Der nachname.
	 */
	private String nachname;
// get und set
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	
	// konstruktoren
	/**
	 * Konstruktoren
	 * @see @Nutzer
	 * @param kundennr {@inheritDoc @Nutzer}
	 * @param passwort {@inheritDoc @Nutzer}
	 * @param email {@inheritDoc @Nutzer}
	 * @param adresse Adressenobjekt
	 * @param vorname Vorname
	 * @param nachname Nachname
	 */
	public Kunde(int kundennr, String passwort, String email, Adresse adresse, String vorname, String nachname) {
		super(kundennr, passwort, email);
		this.adresse = adresse;
		this.vorname = vorname;
		this.nachname = nachname;
	}
	/**
	 * Konstruktoren
	 * @see @Nutzer
	 * @param kundennr {@inheritDoc @Nutzer}
	 * @param passwort {@inheritDoc @Nutzer}
	 * @param email {@inheritDoc @Nutzer}
	 * @param adresse Adressenobjekt
	 * @param vorname Vorname
	 * @param nachname Nachname
	 */
	public Kunde( String passwort, String email, Adresse adresse, String vorname, String nachname) {
		super(NutzerOperations.hoechsteID(), passwort, email);
		this.adresse = adresse;
		this.vorname = vorname;
		this.nachname = nachname;
	}
	
	
	
	
}
