package de.classes;

import java.util.Date;
import java.util.List;

import de.databaseOperations.BestellungOperations;
/**
 * Diese Klasse bildet die Bestellungen aus der DB ab.
 * @author Paul Blanke.
 *
 */
public class Bestellung {
	/**
	 * DB-ID.
	 */
	private int bestellnummer;
	/**
	 * Eine Liste aller Produkt die zu einer Bestellung dazu gehören.
	 */
	private List<Produkt> bestellliste;
	/**
	 * Das Datum der Bestellung.
	 */
	private Date date;
	/**
	 * Der Kunde zu dem die bestellung gehört.
	 */
	private Kunde kunde;
	/**
	 * Hilfsvariable. 
	 */
	private String datefromdb;




	public int getBestellnummer() {
		return bestellnummer;
	}

	public void setBestellnummer(int bestellnummer) {
		this.bestellnummer = bestellnummer;
	}

	public List<Produkt> getBestellliste() {
		return bestellliste;
	}

	public void setBestellliste(List<Produkt> bestellliste) {
		this.bestellliste = bestellliste;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public void createDate() {

	}

	public String getDatefromdb() {
		return datefromdb;
	}

	public void setDatefromdb(String datefromdb) {
		this.datefromdb = datefromdb;
	}
	// konstruktoren
	
	/**
	 * Konstruktor
	 * Die ID wird aus der DB geholt.
	 * @param bestellliste Liste von produkten
	 * @param kunde der bestellene Kunde.
	 */
	public Bestellung(List<Produkt> bestellliste, Kunde kunde) {

		this.bestellnummer = BestellungOperations.hoechsteID();
		this.bestellliste = bestellliste;
		this.date = new Date();
		this.kunde = kunde;
	}
	/**
	 * Konstruktor
	 * @param bstnr DB-ID
	 * @param bestelldatum aus der DB.
	 */
	public Bestellung(int bstnr, String bestelldatum) {
		this.bestellnummer = bstnr;
		this.datefromdb = bestelldatum;
	}
	/**
	 * Kontruktor
	 * @param bestellnummer DB-ID
	 * @param bestellliste liste von produkten
	 * @param kunde Der Kunde der bestellt hat.
	 * @param datefromdb Datum aus der DB.
	 */
	public Bestellung(int bestellnummer, List<Produkt> bestellliste, Kunde kunde, String datefromdb) {

		this.bestellnummer = bestellnummer;
		this.bestellliste = bestellliste;
		this.date = null;
		this.kunde = kunde;
		this.setDatefromdb(datefromdb);
	}
	/**
	 * Kontruktor
	 * @param bestellnummer DB-ID.
	 * @param bestellliste Eine Liste von produkten
	 * @param kunde Der Kunde, der bestellt hat @Kunde
	 */
	public Bestellung(int bestellnummer, List<Produkt> bestellliste, Kunde kunde) {

		this.bestellnummer = bestellnummer;
		this.bestellliste = bestellliste;
		this.date = new Date();
		this.kunde = kunde;
	}
	/**
	 * Konstruktor
	 * @param bstnr DB-iD
	 * @param bestelldatum Das Datum einer Bestellung
	 * @param kunde Kundenobjekt @Kunde
	 */
	public Bestellung(int bstnr, String bestelldatum, Kunde kunde) {
		this.bestellnummer=bstnr;
		this.datefromdb=bestelldatum;
		this.kunde=kunde;
	}
}
