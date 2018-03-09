package de.classes;

/**
 * Die Klasse f�r den Kunden.
 * 
 * @author Paul Blanke
 *
 */
public class Kunde extends Nutzer {
	public Kunde(int nutzer_id, String email, String passwort) {
		super( email, passwort);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param kundenr:
	 *            Die Kundennr.
	 */
	private int kundennr;
	/**
	 * @param Zahlung:
	 *            Ein Objekt der Klasse zahlung.
	 */
	private Zahlung zahlung;
	/**
	 * @param status:
	 *            Der kundenstatus also bestandskunde oder Neukunde.
	 */
	private boolean neukunde;

	private Adresse adresse;

	private Adresse lieferadresse;
	
	private String vorname;
	private String nachname;

	

	// get und set Methoden.

	public int getKundennr() {
		return kundennr;
	}

	public void setKundennr(int kundennr) {
		this.kundennr = kundennr;
	}

	public Zahlung getZahlung() {
		return zahlung;
	}

	public void setZahlung(Zahlung zahlung) {
		this.zahlung = zahlung;
	}

	// konstruktor

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


	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	

	
}
