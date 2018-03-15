package de.classes;

import java.util.ArrayList;
import java.util.List;

public class Bestellung {
	private int best_id;
	
	private Kunde kunde;
	
	private List<Produkt> liste;
	
	private double gesamtsumme;

	public int getBest_id() {
		return best_id;
	}

	public void setBest_id(int best_id) {
		this.best_id = best_id;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public List<Produkt> getListe() {
		return liste;
	}

	public void setListe(List<Produkt> liste) {
		this.liste = liste;
	}

	public double getGesamtsumme() {
		return gesamtsumme;
	}

	public void setGesamtsumme(double gesamtsumme) {
		this.gesamtsumme = gesamtsumme;
	}
	
	
	public Bestellung(Kunde kunde, Warenkorb warenkorb) {
		this.kunde = kunde;
		this.liste = new ArrayList<>();
		befüllen(warenkorb);
		this.best_id = kunde.getNutzer_id();
		
	}
	
	private void befüllen(Warenkorb korb) {
		List<Produkt> waren = korb.getInhalt();
	
		for(Produkt produkt: waren) {
			this.liste.add(produkt);
			this.gesamtsumme += produkt.getPreis();
			
		}
	}
	
	
	
}
