package de.classes;

import java.util.Date;
import java.util.List;

import de.databaseOperations.BestellungOperations;

/**
 * Diese Klasse bildet die Bestellungen aus der DB ab.
 * 
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

	private String bearbeitungsstatus;

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
	 * Konstruktor Die ID wird aus der DB geholt.
	 * 
	 * @param bestellliste
	 *            Liste von produkten
	 * @param kunde
	 *            der bestellene Kunde.
	 */
	public Bestellung(List<Produkt> bestellliste, Kunde kunde) {

		this.bestellnummer = BestellungOperations.hoechsteID();
		this.bestellliste = bestellliste;
		this.date = new Date();
		this.kunde = kunde;
	}

	public Bestellung(int bstnr, String bestelldatum, String bearbeitungsstatus) {
		this.bestellnummer = bstnr;
		this.datefromdb = bestelldatum;
		this.bearbeitungsstatus = bearbeitungsstatus;
	}

	public String getBearbeitungsstatus() {
		return bearbeitungsstatus;
	}

	public void setBearbeitungsstatus(String bearbeitungsstatus) {
		this.bearbeitungsstatus = bearbeitungsstatus;
	}
}
