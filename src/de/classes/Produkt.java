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
	
	//anzahl  für den warenkorb
	private int anzahl;
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
	private boolean status;

	private int artikelnr;
	
	private double preismitanzahl;

	// konstruktor fürs holen aus der db oder anlengen mit id
	public Produkt(int produkt_id, String name, String beschreibung, double preis, int menge, int artnr) {

		this.setProdukt_id(produkt_id);
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.menge = menge;
		this.setArtikelnr(artnr);
		anzahl = 0;
	}
	public Produkt(int produkt_id, String name, String beschreibung, double preis, int menge, int artnr, int anzahl) {
		
		this.setProdukt_id(produkt_id);
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.menge = menge;
		this.setArtikelnr(artnr);
		this.setAnzahl(anzahl);
		
		
	}

	// konstruktor fürs anlegen eines produkts
	public Produkt(String name, String beschreibung, double preis, int menge, int artner) {

		this.setProdukt_id(ProduktOperations.hoechsteID());
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.menge = menge;
		anzahl=0;

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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean bool) {
		this.status = bool;
	}

	/**
	 * Die Methode verï¿½ndert den Produktstatus
	 * 
	 * @param wechsel:
	 *            Indikator fï¿½r Wechsel.
	 * @return der neue Status.
	 */


	public int getProdukt_id() {
		return produkt_id;
	}

	public void setProdukt_id(int produkt_id) {
		this.produkt_id = produkt_id;
	}
	
	public static Object[] getGroessen() {
		return null;
	}

	public int getArtikelnr() {
		return artikelnr;
	}

	public void setArtikelnr(int artikelnr) {
		this.artikelnr = artikelnr;
	}
    public boolean lieferbar(int menge){
    	if(menge == 0){
    		return false;
    	}else{
    		return true;
    	}
    }
	public int getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}
	public double getPreismitanzahl() {
		return preismitanzahl;
	}
	public void setPreismitanzahl(double preismitanzahl) {
		this.preismitanzahl = preismitanzahl;
	}
}
