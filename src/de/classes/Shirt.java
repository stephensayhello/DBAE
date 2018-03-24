package de.classes;
/**
* Diese Klasse bildet eine Variante des Produkt aus.
* @author Benjamin Gajewski
* @see @Produkt
*/
public class Shirt extends Produkt {
	/**
	 * Shirtgr&oesse
	 */
	private String groesse;

	// Get und Set
	public String getGroesse() {
		return groesse;
	}

	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}

	
	
	
	/**
	 * Konstruktor.
	 * @param id {@inheritDoc @Produkt}
	 * @param name {@inheritDoc @Produkt}
	 * @param beschreibung {@inheritDoc @Produkt}
	 * @param preis {@inheritDoc @Produkt}
	 * @param groesse Shirtgr&oesse
	 * @param menge {@inheritDoc @Produkt }
	 * @param artnr {@inheritDoc @Produkt}
	 * @param anzahl {@inheritDoc @Produkt}
	 * @param versanddauer {@inheritDoc @Produkt}
	 * @param status {@inheritDoc @Produkt}
	 * @param imagepath {@inheritDoc @Produkt}
	 */
	public Shirt(int id, String name, String beschreibung, double preis, String groesse, int menge, int artnr,
			int anzahl, int versanddauer, String status, String imagepath) {
		super(id, name, beschreibung, preis, menge, artnr, anzahl, versanddauer, status, imagepath);
		this.groesse = groesse;
		this.setPreismitanzahl(anzahl * preis);

		this.setPreismitanzahlineuro(anzahl * preis);
	}

	/**
	 * Konstruktor.
	 * @param id {@inheritDoc @Produkt}
	 * @param name {@inheritDoc @Produkt}
	 * @param beschreibung {@inheritDoc @Produkt}
	 * @param preis {@inheritDoc @Produkt}
	 * @param groesse Shirtgr&oesse
	 * @param menge {@inheritDoc @Produkt }
	 * @param artnr {@inheritDoc @Produkt}
	 * @param versanddauer {@inheritDoc @Produkt}
	 * @param status {@inheritDoc @Produkt}
	 * @param imagepath {@inheritDoc @Produkt}
	 */
	public Shirt(int id, String name, String beschreibung, double preis, String groesse, int menge, int artnr,
			int versanddauer, String status) {
		super(id, name, beschreibung, preis, menge, artnr, versanddauer, status);
		this.groesse = groesse;
	}

	
}
