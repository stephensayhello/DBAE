package de.classes;
/**
 * Die Klasse bildet das Produkt p ab.
 * @author Paul Blanke
 *
 */
public class Produkte {
// Attribute
	/**
	 *@param ean: Die Produktnummer. 
	 */
	private int ean;
	/**
	 *@param name: Produktname. 
	 */
	private String name;
	/**
	 *@param beschreibung: Produktbeschreibung. 
	 */
	private String beschreibung;
	/**
	 *@param pfad: absoluter Pfad für ein späteres Produkt Bild. 
	 */
	private String pfad;
	/**
	 *@param preis: Der Produktpreis; 
	 */
	private double preis;
	/**
	 *@param groesse: Die Produktgröße bzw. l, 44 etc. 
	 */
	private int groesse;
	/**
	 *@param menge: Die Menge auf Lager. 
	 */
	private int menge;
	/**
	 *@param sichtbarkeit: Produkt wird gezeigt ja/nein. 
	 */
	private boolean sichtbarkeit;
	/**
	 *@param status: der Produktstatus zB. nicht lieferbar. 
	 */
	private String status;
	
	
// get und set methoden.
	public int getEan() {
		return ean;
	}



	public void setEan(int ean) {
		this.ean = ean;
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



	public String getPfad() {
		return pfad;
	}



	public void setPfad(String pfad) {
		this.pfad = pfad;
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

	public Produkte() {
		
	}
	
	public Produkte(int ean, String name, String beschreibung, int groesse, double preis) {
		
		this.setEan(ean);
		this.setName(name);
		this.setBeschreibung(beschreibung);
		this.setGroesse(groesse);
		this.setPreis(preis);
		
		this.setPfad("kein Bild vorhanden");
		this.setMenge(0);
		this.setSichtbarkeit(false);
		this.setStatus("Zur Zeit nicht verfügbar");
		
	}
	
	
// Methoden	
	/**
	 * Die Methode verändert den Produktstatus
	 * @param wechsel: Indikator für Wechsel.
	 * @return der neue Status.
	 */
	public String getStatus(int wechsel) {
		
		if(wechsel == 1) {
			this.setStatus("lieferbar");
		} else if(wechsel == 2) {
			this.setStatus("Vorrübergehendend nicht verfügbar.");
		} else if(wechsel == 3) {
			this.setStatus("Zur zeit nicht lieferbar.");
		}
		
		return this.status;
	}
	
	
	
	
}
