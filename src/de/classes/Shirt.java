package de.classes;

public class Shirt extends Produkt {
	
	private String groesse;

	public Shirt(int id, String name, String beschreibung, double preis, String groesse, int menge) {
		super(id, name, beschreibung, preis, menge);
		this.groesse = groesse;
	}
	
	public Shirt(String name, String beschreibung, double preis, String groesse, int menge) {
		super(name, beschreibung, preis, menge);
		this.groesse = groesse;
	}

	public String getGroesse() {
		return groesse;
	}

	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}

}
