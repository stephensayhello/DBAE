package de.classes;

import java.util.List;

public class Warenkorb {
	/**
	 * Eine Liste von Produkten im Warenkorb.
	 */
	private List<Produkt> inhalt;
	/**
	 * Der Account, der den Warenkorb bestellt.
	 */
	private Kunde kunde;
	
	public List<Produkt> getInhalt() {
		return inhalt;
	}

	public void setInhalt(List<Produkt> inhalt) {
		this.inhalt = inhalt;
	}

	


}
