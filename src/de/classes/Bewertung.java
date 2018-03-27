package de.classes;

import java.util.Date;
/**
 * Diese Klasse bildet die bewertungen ab.
 * @author Paul Blanke
 *
 */
public class Bewertung {
	/**
	 * DB-Id
	 */
	private int bewertungsid;
	/**
	 * Die ID des produktes
	 */
	private int artikelnr;
	/**
	 * Die ID des kunden
	 */
	private int kundennr;
	/**
	 * Gesamtzahl von Max 5.
	 */
	private int punkte;
	/**
	 * Die beschreibung
	 */
	private String text;
	/**
	 * Dasd Datum in String Form
	 */
	private String datum;
	/**
	 * Objekt der Klasse @Kunde
	 */
	private Kunde kunde;
	/**
	 * Objekt der Klasse @Produkt
	 */
	private Produkt produkt;


	/**
	 * Konstruktor
	 * @param bewertungsid DB-Id
	 * @param artikelnr @Produkt
	 * @param kundennr Ein Objekt @Kunde
	 * @param punkte die punkte von max. 5
	 * @param text beschreibung der Bewertung
	 */
	public Bewertung(int bewertungsid, int artikelnr, int kundennr, int punkte, String text) {
		super();
		this.bewertungsid = bewertungsid;
		this.artikelnr = artikelnr;
		this.kundennr = kundennr;
		this.punkte = punkte;
		this.text = text;
		this.datum = new Date().toString();
	}
	/**
	 * Konstruktor
	 * @param bewertungsid DB-Id
	 * @param produkt ein Objekt der Klasse @Produkt
	 * @param kunde ein Objekt der Klasse @Kunde
	 * @param punkte Anzahl der Punkte von max. 5
	 * @param datum Das Datum der bewertung
	 * @param text die beschreibung der Bewertung
	 */
	public Bewertung(int bewertungsid, Produkt produkt, Kunde kunde, int punkte, String datum, String text) {
		super();
		this.bewertungsid = bewertungsid;
		this.setProdukt(produkt);
		this.kunde = kunde;
		this.punkte = punkte;
		this.text = text;
		this.datum = new Date().toString();
	}
// get und Setter
	public int getBewertungsid() {
		return bewertungsid;
	}

	public void setBewertungsid(int bewertungsid) {
		this.bewertungsid = bewertungsid;
	}

	public int getArtikelnr() {
		return artikelnr;
	}

	public void setArtikelnr(int artikelnr) {
		this.artikelnr = artikelnr;
	}

	public int getKundennr() {
		return kundennr;
	}

	public void setKundennr(int kundennr) {
		this.kundennr = kundennr;
	}

	public int getPunkte() {
		return punkte;
	}

	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}


	public Kunde getKunde() {
		return kunde;
	}


	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}


	public Produkt getProdukt() {
		return produkt;
	}


	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

}
