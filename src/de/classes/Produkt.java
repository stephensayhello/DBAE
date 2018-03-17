package de.classes;

import java.text.NumberFormat;
import java.util.Locale;

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
	
	//anzahl  f�r den warenkorb
	private int anzahl;
	/**
	 * @param preis:
	 *            Der Produktpreis;
	 */
	private double preis;
	/**
	 * @param groesse:
	 *            Die Produktgr��e bzw. l, 44 etc.
	 */
	private int menge;
	/**
	 * @param sichtbarkeit:
	 *            Produkt wird gezeigt ja/nein.
	 */
	/**
	 * @param status:
	 *            der Produktstatus zB. nicht lieferbar.
	 */
	private String status;

	private int artikelnr;
	
	private double preismitanzahl;
	
	private String preismitanzahlineuro;

	// konstruktor f�rs holen aus der db oder anlengen mit id
	public Produkt(int produkt_id, String name, String beschreibung, double preis, int menge, int artnr) {

		this.setProdukt_id(produkt_id);
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.menge = menge;
		this.setArtikelnr(artnr);
		anzahl = 0;
		this.setStatus("Lieferbar");
	}
	public Produkt(int produkt_id, String name, String beschreibung, double preis, int menge, int artnr, int anzahl) {
		
		this.setProdukt_id(produkt_id);
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.menge = menge;
		this.setArtikelnr(artnr);
		this.setAnzahl(anzahl);
		this.setStatus("Lieferbar");
		this.setPreismitanzahl(anzahl*preis);
		this.setPreismitanzahlineuro(anzahl*preis);
		
		
	}

	// konstruktor f�rs anlegen eines produkts
	public Produkt(String name, String beschreibung, double preis, int menge, int artnr) {

		this.setProdukt_id(ProduktOperations.hoechsteID());
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.menge = menge;
		anzahl=0;
		this.setStatus("Lieferbar");

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Die Methode ver�ndert den Produktstatus
	 * 
	 * @param wechsel:
	 *            Indikator f�r Wechsel.
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
	// Eine idee f�r den Status
	public void pruefeStatus() {
		if(menge >= anzahl) {
			this.setStatus("Nicht lieferbar");
		}
	}
	public String getPreismitanzahlineuro() {
		return preismitanzahlineuro;
	}
	public void setPreismitanzahlineuro(double preis) {
		this.preismitanzahlineuro = NumberFormat.getCurrencyInstance(Locale.GERMANY).format(preis);
	}
}
