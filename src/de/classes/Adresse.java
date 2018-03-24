package de.classes;

import de.databaseOperations.AdresseOperations;
/**
 * Diese Klasse bildet das DB-objekt Adresse ab.
 * @author Stephen Galla
 *
 */
public class Adresse {
	/**
	 * DB ID.
	 */
	private int adress_id;
	/**
	 * Die Straße
	 */
	private String strasse;
	/**
	 * Die Hausnummer.
	 */
	private String hausnummer;
	/**
	 * Die Postleitzahl.
	 */
	private int plz;
	/**
	 * Der Ort.
	 */
	private String ort;
	
	// Get und Set
	
	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnummer() {
		return hausnummer;
	}
	
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}


	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	

	public int getAdress_id() {
		return adress_id;
	}

	public void setAdress_id(int adress_id) {
		this.adress_id = adress_id;
	}
	
	// Konstruktoren
	
	/**
	 * Konstruktur
	 * Die ID wird aus der DB geholt und automatisch generiert.
	 * @param strasse die Straße
	 * @param hausnr die Hausnummer
	 * @param Plz Postleitzahl.
	 * @param ort Der Wohnort.
	 */
	public Adresse(String strasse, String hausnr, int Plz, String ort) {
		this.adress_id = AdresseOperations.hoechsteID();
		this.strasse = strasse;
		this.hausnummer = hausnr;
		this.plz = Plz;
		this.ort = ort;
	}
	/**
	 * Konstruktur
	 * @param adress_id Datenbank_id.
	 * @param strasse die Straße
	 * @param hausnr die Hausnummer
	 * @param Plz Postleitzahl.
	 * @param ort Der Wohnort.
	 */
	public Adresse(int adress_id, String strasse, String hausnummer, int Plz, String ort) {
		this.adress_id = adress_id;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.plz = Plz;
		this.ort = ort;
	}
	

	
	

}
