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
	/**
	 * @param status:
	 *            der Produktstatus zB. nicht lieferbar.
	 */
	private String status;

	private int artikelnr;
	
	private double preismitanzahl;
	
	private String preismitanzahlineuro;
	
	private String preisineuro;
	
	public int versanddauer;
	
	private String imagePath = "img/dummy.jpg";

	public int getVersanddauer() {
		return versanddauer;
	}
	public void setVersanddauer(int versanddauer) {
		this.versanddauer = versanddauer;
	}
	// konstruktor fürs holen aus der db oder anlengen mit id
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

	// konstruktor fürs anlegen eines produkts
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
	// Eine idee für den Status
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
}
