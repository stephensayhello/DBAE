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
	
	public Warenkorb(Kunde kunde,List<Produkt> produkte){
	    this.setWarenkorb_id(WarenkorbOperations.hoechsteID());
		this.setKunde(kunde);
		this.produkte = produkte;
	}
	
	public Warenkorb(Kunde kunde){
		this.setWarenkorb_id(WarenkorbOperations.hoechsteID());
		this.setKunde(kunde);
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
	}

	public int getWarenkorb_id() {
		return warenkorb_id;
	}

	public void setWarenkorb_id(int warenkorb_id) {
		this.warenkorb_id = warenkorb_id;
	}
	
	public int gethoechsteVersanddauer(){
		int versanddauer = 0;
		if(produkte.size()!=0){
		 versanddauer = produkte.get(0).getVersanddauer();
		for (Produkt produkt : produkte) {
			if(produkt.getVersanddauer()>versanddauer){
				versanddauer = produkt.getVersanddauer();
			}
		}}
		
		return versanddauer;
		
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public void setGesamtpreis(double gesamtpreis) {
	}


}
