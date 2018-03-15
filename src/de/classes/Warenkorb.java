package de.classes;

import java.util.List;

public class Warenkorb {
	/**
	 * Eine Liste von Produkten im Warenkorb.
	 */
	private List<Produkt> produkte;
	/**
	 * Der Account, der den Warenkorb bestellt.
	 */
	private Kunde kunde;
	
	public Warenkorb(Kunde kunde,List<Produkt> produkte){
		this.kunde = kunde;
		this.produkte = produkte;
	}
	
	public List<Produkt> getInhalt() {
		return produkte;
	}

	public void setInhalt(List<Produkt> produkte) {
		this.produkte = produkte;
	}

	


}
