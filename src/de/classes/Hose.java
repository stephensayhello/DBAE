package de.classes;

public class Hose extends Produkt {

	private int groesse;

	public Hose(int id, String name, String beschreibung, double preis, int groesse, int menge,int artnr) {
		super(id, name, beschreibung, preis, menge, artnr);
		this.groesse = groesse;
	}
	public Hose(int id, String name, String beschreibung, double preis, int groesse, int menge,int artnr, int anzahl) {
		super(id, name, beschreibung, preis, menge, artnr, anzahl);
		this.groesse = groesse;
		this.setPreismitanzahl(anzahl*preis);
		if (anzahl > menge){
			 this.setStatus(" Nicht Lieferbar");
			}else { 
				this.setStatus("lieferbar");
			}
		this.setPreismitanzahlineuro(anzahl*preis);
	}
	
	public Hose(String name, String beschreibung, double preis, int groesse, int menge, int artnr) {
		super(name, beschreibung, preis, menge, artnr);
		this.groesse = groesse;
	}

	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

}
