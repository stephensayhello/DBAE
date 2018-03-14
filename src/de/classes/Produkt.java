package de.classes;

import de.databaseOperations.ProduktOperations;

/**
 * Die Klasse bildet das Produkt p ab.
 * 
 * @author Paul Blanke, stephen Galla
 *
 */
public class Produkt {
	// Attribute
	private int produkt_id;
	/**
	 * @param art:
	 *            Produktart.
	 */
	private String kategorie;
	/**
	 * @param name:
	 *            Produktname.
	 */
	private String name;
	/**
	 * @param beschreibung:
	 *            Produktbeschreibung.
	 */
	private String beschreibung;
	/**
	 * @param pfad:
	 *            absoluter Pfad fï¿½r ein spï¿½teres Produkt Bild.
	 */

	/**
	 * @param preis:
	 *            Der Produktpreis;
	 */
	private double preis;
	/**
	 * @param groesse:
	 *            Die Produktgrï¿½ï¿½e bzw. l, 44 etc.
	 */
	private int menge;
	/**
	 * @param sichtbarkeit:
	 *            Produkt wird gezeigt ja/nein.
	 */
	private boolean sichtbarkeit;
	/**
	 * @param status:
	 *            der Produktstatus zB. nicht lieferbar.
	 */
	private String status;

	// konstruktor fürs holen aus der db oder anlengen mit id
	public Produkt(int produkt_id, String name, String beschreibung, double preis, int menge) {

		this.setProdukt_id(produkt_id);
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.menge = menge;

	}

	// konstruktor fürs anlegen eines produkts
	public Produkt(String name, String beschreibung, double preis, int menge) {

		this.setProdukt_id(ProduktOperations.hoechsteID());
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.menge = menge;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public boolean isSichtbarkeit() {
		return sichtbarkeit;
	}

	public void setSichtbarkeit(boolean sichtbarkeit) {
		this.sichtbarkeit = sichtbarkeit;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * Die Methode verï¿½ndert den Produktstatus
	 * 
	 * @param wechsel:
	 *            Indikator fï¿½r Wechsel.
	 * @return der neue Status.
	 */
	public String getStatus(int wechsel) {

		if (wechsel == 1) {
			this.setStatus("lieferbar");
		} else if (wechsel == 2) {
			this.setStatus("Vorï¿½bergehendend nicht verfï¿½gbar.");
		} else if (wechsel == 3) {
			this.setStatus("Zur zeit nicht lieferbar.");
		}

		return this.status;
	}

	public int getProdukt_id() {
		return produkt_id;
	}

	public void setProdukt_id(int produkt_id) {
		this.produkt_id = produkt_id;
	}

}
