package de.classes;

/**
 * Die Klasse bildet das Produkt p ab.
 * 
 * @author Paul Blanke, stephen Galla
 *
 */
public class Produkte {
	// Attribute
	/**
	 * @param ean:
	 *            Die Produktnummer.
	 */
	private int produkt_id;
	/**
	 * @param art:
	 *            Produktart.
	 */
	private String art;
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
	 *            absoluter Pfad f�r ein sp�teres Produkt Bild.
	 */

	/**
	 * @param preis:
	 *            Der Produktpreis;
	 */
	private double preis;
	/**
	 * @param groesse:
	 *            Die Produktgr��e bzw. l, 44 etc.
	 */
	private int groesse;
	/**
	 * @param menge:
	 *            Die Menge auf Lager.
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
	

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
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

	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// Konstruktoren.

	public Produkte(String art2, String name2, String beschreibung2, double preis2, int groesse2, int menge2) {

	}

	// Methoden
	/**
	 * Die Methode ver�ndert den Produktstatus
	 * 
	 * @param wechsel:
	 *            Indikator f�r Wechsel.
	 * @return der neue Status.
	 */
	public String getStatus(int wechsel) {

		if (wechsel == 1) {
			this.setStatus("lieferbar");
		} else if (wechsel == 2) {
			this.setStatus("Vor�bergehendend nicht verf�gbar.");
		} else if (wechsel == 3) {
			this.setStatus("Zur zeit nicht lieferbar.");
		}

		return this.status;
	}

}
