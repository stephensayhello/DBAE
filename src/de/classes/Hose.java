package de.classes;
/**
 * Diese Klasse ist eine Variante der Produkte
 * @see {@link @Produkt}
 * @author Stephen Galla.
 *
 */
public class Hose extends Produkt {
	/**
	 * Die Grösse der Hose zB. 38
	 */
	private int groesse;

	// get und set
	
	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}
	
	
	/**
	 * Konstruktur 
	 * @see {@link @Produkt}
	 * @param id {@inheritDoc @Produkt}
	 * @param name {@inheritDoc @Produkt}
	 * @param beschreibung {@inheritDoc @Produkt}
	 * @param preis {@inheritDoc @Produkt}
	 * @param groesse Hosengrösse
	 * @param menge {@inheritDoc @Produkt}
	 * @param artnr {@inheritDoc @Produkt}
	 * @param versanddauer {@inheritDoc @Produkt}
	 * @param status {@inheritDoc @Produkt}
	 */
	public Hose(int id, String name, String beschreibung, double preis, int groesse, int menge, int artnr,
			int versanddauer, String status) {
		super(id, name, beschreibung, preis, menge, artnr, versanddauer, status);
		this.groesse = groesse;
	}
	/**
	 * Konstruktur 
	 * @see {@link @Produkt}
	 * @param id {@inheritDoc @Produkt}
	 * @param name {@inheritDoc @Produkt}
	 * @param beschreibung {@inheritDoc @Produkt}
	 * @param preis {@inheritDoc @Produkt}
	 * @param groesse Hosengrösse
	 * @param menge {@inheritDoc @Produkt}
	 * @param artnr {@inheritDoc @Produkt}
	 * @param versanddauer {@inheritDoc @Produkt}
	 * @param status {@inheritDoc @Produkt}
	 */
	public Hose(int id, String name, String beschreibung, double preis, int groesse, int menge, int artnr, int anzahl,
			int versanddauer, String status, String imagepath) {
		super(id, name, beschreibung, preis, menge, artnr, anzahl, versanddauer, status, imagepath);
		this.groesse = groesse;
		this.setPreismitanzahl(anzahl * preis);

	}
	/**
	 * Konstruktur 
	 * @see {@link @Produkt}
	 * @param id {@inheritDoc @Produkt}
	 * @param name {@inheritDoc @Produkt}
	 * @param beschreibung {@inheritDoc @Produkt}
	 * @param preis {@inheritDoc @Produkt}
	 * @param groesse Hosengrösse
	 * @param menge {@inheritDoc @Produkt}
	 * @param artnr {@inheritDoc @Produkt}
	 * @param versanddauer {@inheritDoc @Produkt}
	 * @param status {@inheritDoc @Produkt}
	 */
	public Hose(String name, String beschreibung, double preis, int groesse, int menge, int artnr, String status) {
		super(name, beschreibung, preis, menge, artnr, status);
		this.groesse = groesse;
	}

	
}
