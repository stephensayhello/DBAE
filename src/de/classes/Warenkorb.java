package de.classes;

import java.util.List;

public class Warenkorb {
	/**
	 * Eine Liste von Produkten im Warenkorb.
	 */
	private List<Produkte> inhalt;
	/**
	 * Der Account, der den Warenkorb bestellt.
	 */
	private Kunde kunde;
	
	public List<Produkte> getInhalt() {
		return inhalt;
	}

	public void setInhalt(List<Produkte> inhalt) {
		this.inhalt = inhalt;
	}

	


}
