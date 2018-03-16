package de.classes;

public class Produkt_Anzahl_Zuordnung {
	private Produkt produkt;
	private int anzahl;
	
	
	
	
	
	
	
	public Produkt_Anzahl_Zuordnung(Produkt produkt, int anzahl) {
		super();
		this.produkt = produkt;
		this.anzahl = anzahl;
	}
	
	
	
	
	public int getAnzahl() {
		return anzahl;
	}
	
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}
	public Produkt getProdukt() {
		return produkt;
	}
	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}
	
}
