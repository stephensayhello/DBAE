package de.classes;

import java.text.NumberFormat;
import java.util.Locale;

import de.databaseOperations.ProduktOperations;

/**
 * Die Klasse bildet das Gegenstück zu entsprechenden Tabelle.
 * 
 * @author Benjamin Gajewski	
 *
 */
public class Produkt {
	// Attribute
	/**
	 * DB-ID.
	 */
	private int produkt_id;
	
	/**
	 * Der Produktname.
	 */
	private String name;
	/**
	 * Die Produktbeschreibung.
	 */
	private String beschreibung;
	
	
	/**
	 * Anzahl von Produkten f&uer den Warenkorb.
	 */
	private int anzahl;
	/**
	 * Produktpreis.
	 */
	private double preis;
	/**
	 * Die produktmenge die auf Lager ist.
	 */
	private int menge;
	/**
	 * Sagt aus, ob ein produkt verf&uegbar ist
	 */
	private String status;
	/**
	 * Artikel Nummer
	 */
	private int artikelnr;
	/**
	 *  Der Gesamtpreis
	 */
	private double preismitanzahl;
	/**
	 * Hilfsvariable für Darstellung.
	 */
	private String preismitanzahlineuro;
	/**
	 * Hilfsvariable für Darstellung.
	 */
	private String preisineuro;
	/**
	 * Versanddauer in Tage.
	 */
	public int versanddauer;
	/**
	 * Bildpfad
	 */
	private String imagePath = "img/dummy.jpg";
// get und set
	
	public int getVersanddauer() {
		return versanddauer;
	}
	public void setVersanddauer(int versanddauer) {
		this.versanddauer = versanddauer;
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
	// Eine idee fï¿½r den Status
	public void pruefeStatus() {
		if(menge >= anzahl) {
			this.setStatus("Nicht lieferbar");
		}
	}
	public String getPreismitanzahlineuro() {
		System.out.println("preismitanzeur" + preismitanzahlineuro);
		return preismitanzahlineuro;
	}
	public void setPreismitanzahlineuro(double preis) {
		System.out.println("daqhhwqu");
		this.preismitanzahlineuro = NumberFormat.getCurrencyInstance(Locale.GERMANY).format(preis);
	}
	public String getPreisineuro() {
		return preisineuro;
	}
	public void setPreisineuro(double preis) {
		this.preisineuro = NumberFormat.getCurrencyInstance(Locale.GERMANY).format(preis);
	}
	public int getArtikelnr() {
		return artikelnr;
	}
	public void setArtikelnr(int artikelnr) {
		this.artikelnr = artikelnr;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	// konstruktoren
	/**
	 * Konstruktor
	 * @param produkt_id DB-id.
	 * @param name selbsterkl&aerend.
	 * @param beschreibung selbsterkl&aerend.
	 * @param preis selbsterkl&aerend.
	 * @param menge selbsterkl&aerend.
	 * @param artnr selbsterkl&aerend.
	 * @param versanddauer in Tagen
	 * @param status lieferbarkeit.
	 */
	public Produkt(int produkt_id, String name, String beschreibung, double preis, int menge, int artnr, int versanddauer, String status) {

		this.setProdukt_id(produkt_id);
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.menge = menge;
		this.setArtikelnr(artnr);
		this.anzahl = 0;
		this.setStatus(status);
		this.setPreisineuro(preis);
		this.setVersanddauer(versanddauer);
	}
	/**
	 * Konstruktor
	 * @param produkt_id DB-id.
	 * @param name selbsterkl&aerend.
	 * @param beschreibung selbsterkl&aerend.
	 * @param preis selbsterkl&aerend.
	 * @param menge selbsterkl&aerend.
	 * @param artnr selbsterkl&aerend.
	 * @param versanddauer in Tagen
	 * @param status lieferbarkeit.
	 * @param imagepath Bildpfad
	 */
	public Produkt(int produkt_id, String name, String beschreibung, double preis, int menge, int artnr, int anzahl, int versanddauer,String status, String imagepath) {
		
		this.setProdukt_id(produkt_id);
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.menge = menge;
		this.setArtikelnr(artnr);
		this.setAnzahl(anzahl);
		this.setStatus(status);
		this.setPreismitanzahl(anzahl*preis);
		this.setPreismitanzahlineuro(anzahl*preis);
		this.setPreisineuro(preis);
		System.out.println("blob");
		this.setVersanddauer(versanddauer);
		this.setImagePath(imagepath);
	}

	/**
	 * Konstruktur f&uer neue Produkte.
	 * @param name selbsterk&aerend
	 * @param beschreibung selbsterk&aerend
	 * @param preis selbsterk&aerend
	 * @param menge selbsterk&aerend
	 * @param artnr selbsterk&aerend
	 * @param status Lieferbarkeit.
	 */
	public Produkt(String name, String beschreibung, double preis, int menge, int artnr,String status) {

		this.setProdukt_id(ProduktOperations.hoechsteID());
		this.name = name;
		this.beschreibung = beschreibung;
		this.preis = preis;
		this.menge = menge;
		this.anzahl=0;
		this.setStatus(status);
		this.setPreisineuro(preis);

	}

	
	
}
