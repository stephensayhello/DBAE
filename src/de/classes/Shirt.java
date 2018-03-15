package de.classes;

public class Shirt extends Produkt {

	private String groesse;

	public Shirt(int id, String name, String beschreibung, double preis, String groesse, int menge, int artnr) {
		super(id, name, beschreibung, preis, menge, artnr);
		this.groesse = groesse;
	}

	public String getGroesse() {
		return groesse;
	}

	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}

}
