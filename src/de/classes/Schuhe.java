package de.classes;

public class Schuhe extends Produkt {
	
	private int groesse;
  
	public Schuhe(int id, String name, String beschreibung, double preis, int groesse, int menge, int artnr, int anzahl, int versanddauer, String status, String imagepath) {
		super(id, name, beschreibung, preis, menge,  artnr, anzahl, versanddauer,status, imagepath);
		this.groesse = groesse;
		this.setPreismitanzahl(anzahl*preis);
		
		
	}
	public Schuhe(int id, String name, String beschreibung, double preis, int groesse, int menge, int artnr, int versanddauer, String status) {
		super(id, name, beschreibung, preis, menge,  artnr, versanddauer, status);
		this.groesse = groesse;
	}
	
	public Schuhe(String name, String beschreibung, double preis, int groesse, int menge, int artnr, String status) {
		super(name, beschreibung, preis, menge, artnr, status);
		this.groesse = groesse;
	}

	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

}
