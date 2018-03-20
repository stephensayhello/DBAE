package de.classes;

public class Schuhe extends Produkt {
	
	private int groesse;

	public Schuhe(int id, String name, String beschreibung, double preis, int groesse, int menge, int artnr, int anzahl, int versanddauer) {
		super(id, name, beschreibung, preis, menge,  artnr, anzahl, versanddauer);
		this.groesse = groesse;
		this.setPreismitanzahl(anzahl*preis);
		
		
	}
	public Schuhe(int id, String name, String beschreibung, double preis, int groesse, int menge, int artnr, int versanddauer) {
		super(id, name, beschreibung, preis, menge,  artnr, versanddauer);
		this.groesse = groesse;
	}
	
	public Schuhe(String name, String beschreibung, double preis, int groesse, int menge, int artnr) {
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
