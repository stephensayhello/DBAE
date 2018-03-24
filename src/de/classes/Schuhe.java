package de.classes;
/**
 * Diese Klasse bildet eine Variante des Produkt aus.
 * @author Benjamin Gajewski
 * @see @Produkt
 */
public class Schuhe extends Produkt {
	/**
	 * Die Gr&oesse zB. 44
	 */
	private int groesse;
	
	
// Getter und Setter	
	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	/**
	 * Konstruktor.
	 * @param id {@inheritDoc @Produkt}
	 * @param name {@inheritDoc @Produkt}
	 * @param beschreibung {@inheritDoc @Produkt}
	 * @param preis {@inheritDoc @Produkt}
	 * @param groesse Schuhgr&oesse
	 * @param menge {@inheritDoc @Produkt }
	 * @param artnr {@inheritDoc @Produkt}
	 * @param anzahl {@inheritDoc @Produkt}
	 * @param versanddauer {@inheritDoc @Produkt}
	 * @param status {@inheritDoc @Produkt}
	 * @param imagepath {@inheritDoc @Produkt}
	 */
	public Schuhe(int id, String name, String beschreibung, double preis, int groesse, int menge, int artnr, int anzahl, int versanddauer, String status, String imagepath) {
		super(id, name, beschreibung, preis, menge,  artnr, anzahl, versanddauer,status, imagepath);
		this.groesse = groesse;
		this.setPreismitanzahl(anzahl*preis);
		
	}

	/**
	 * Konstruktor.
	 * @param id {@inheritDoc @Produkt}
	 * @param name {@inheritDoc @Produkt}
	 * @param beschreibung {@inheritDoc @Produkt}
	 * @param preis {@inheritDoc @Produkt}
	 * @param groesse Schuhgr&oesse
	 * @param menge {@inheritDoc @Produkt }
	 * @param artnr {@inheritDoc @Produkt}
	 * @param anzahl {@inheritDoc @Produkt}
	 * @param versanddauer {@inheritDoc @Produkt}
	 * @param status {@inheritDoc @Produkt}
	 */
	public Schuhe(int id, String name, String beschreibung, double preis, int groesse, int menge, int artnr, int versanddauer, String status) {
		super(id, name, beschreibung, preis, menge,  artnr, versanddauer, status);
		this.groesse = groesse;
	}

	/**
	 * Konstruktor.
	 * @param id {@inheritDoc @Produkt}
	 * @param name {@inheritDoc @Produkt}
	 * @param beschreibung {@inheritDoc @Produkt}
	 * @param preis {@inheritDoc @Produkt}
	 * @param groesse Schuhgr&oesse
	 * @param menge {@inheritDoc @Produkt }
	 * @param artnr {@inheritDoc @Produkt}
	 * @param anzahl {@inheritDoc @Produkt}
	 * @param status {@inheritDoc @Produkt}
	 */
	public Schuhe(String name, String beschreibung, double preis, int groesse, int menge, int artnr, String status) {
		super(name, beschreibung, preis, menge, artnr, status);
		this.groesse = groesse;
	}

	
}
