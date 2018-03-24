package de.classes;

import java.util.ArrayList;
import java.util.List;
/**
 * Diese Klasse bildet die Produktgruppe ab.
 * 
 * @author Benjamin Gajewski
 *
 */
public class Produktgruppe {
	/**
	 * DB-ID.
	 */
	private int artnr;
	/**
	 * Liste aller Produkte in der Gruppe.
	 */
	public List<Produkt> produkte = new ArrayList<>();
 
	// Getter und Setter
	public int getArtnr() {
		return artnr;
	}
	public void setArtnr(int artnr) {
		this.artnr = artnr;
	}
        
    /**
     * Konstruktor
     * @param produkte liste aller Produkte
     * @param artnr DB-ID.
     */
    public Produktgruppe(List<Produkt> produkte, int artnr) {
		super();
		this.produkte = produkte;
		this.artnr = artnr;
	}
	
	
    
    
}

	