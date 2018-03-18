package de.classes;

import java.util.ArrayList;
import java.util.List;

import de.databaseOperations.WarenkorbOperations;

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
	
	private double gesamtpreis;
	public Warenkorb(Kunde kunde,List<Produkt> produkte){
		this.warenkorb_id = WarenkorbOperations.hoechsteID();
		this.kunde = kunde;
		this.produkte = produkte;
	}
	
	public Warenkorb(Kunde kunde){
		this.warenkorb_id = WarenkorbOperations.hoechsteID();
		this.kunde = kunde;
		this.produkte = new ArrayList<Produkt>();
	}
	
	
	public List<Produkt> getInhalt() {
		return produkte;
	}

	public void setInhalt(List<Produkt> produkte) {
		this.produkte = produkte;
	}

	public double getGesamtpreis() {
	double gesamtpreis = 0;
		for (Produkt produkt : produkte) {
			 gesamtpreis = gesamtpreis + (produkt.getAnzahl() * produkt.getPreis());
		}
		return gesamtpreis;
	}

	public void setGesamtpreis(int gesamtpreis) {
		
		
		this.gesamtpreis = gesamtpreis;
	}
	
	


}
