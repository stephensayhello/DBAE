package de.classes;

import java.util.ArrayList;
import java.util.List;

import de.databaseOperations.Warenkorboperations;

public class Warenkorb {
	private int warenkorb_id;
	/**
	 * Eine Liste von Produkten im Warenkorb.
	 */
	private List<Produkt> produkte;
	/**
	 * Der Account, der den Warenkorb bestellt.
	 */
	private Kunde kunde;
	
	public Warenkorb(Kunde kunde,List<Produkt> produkte){
		this.warenkorb_id = Warenkorboperations.hoechsteID();
		this.kunde = kunde;
		this.produkte = produkte;
	}
	
	public Warenkorb(Kunde kunde){
		this.warenkorb_id = Warenkorboperations.hoechsteID();
		this.kunde = kunde;
		this.produkte = new ArrayList<Produkt>();
	}
	
	
	public List<Produkt> getInhalt() {
		return produkte;
	}

	public void setInhalt(List<Produkt> produkte) {
		this.produkte = produkte;
	}
	
	


}
