package de.classes;

public class Shirt extends Produkt {

	private String groesse;

	public Shirt(int id, String name, String beschreibung, double preis, String groesse, int menge, int artnr,
			int anzahl, int versanddauer, String status) {
		super(id, name, beschreibung, preis, menge, artnr, anzahl, versanddauer, status);
		this.groesse = groesse;
		this.setPreismitanzahl(anzahl * preis);

		this.setPreismitanzahlineuro(anzahl * preis);
	}

	public Shirt(int id, String name, String beschreibung, double preis, String groesse, int menge, int artnr,
			int versanddauer, String status) {
		super(id, name, beschreibung, preis, menge, artnr, versanddauer, status);
		this.groesse = groesse;
	}

	public String getGroesse() {
		return groesse;
	}

	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}

}
