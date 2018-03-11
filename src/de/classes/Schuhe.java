package de.classes;

public class Schuhe extends Produkt {

	private int groesse;

	public Schuhe(int id, String name, String beschreibung, double preis, int groesse, int menge) {
		super(id, name, beschreibung, preis, menge);
		this.groesse = groesse;
	}
	
	public Schuhe(String name, String beschreibung, double preis, int groesse, int menge) {
		super(name, beschreibung, preis, menge);
		this.groesse = groesse;
	}

	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

}
