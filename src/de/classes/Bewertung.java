package de.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bewertung {

	private int bewertungsid;
	private int artikelnr;
	private int kundennr;
	private int punkte;
	private String text;
	private String datum;
	private Kunde kunde;
	private Produkt produkt;

	public Bewertung(int bewertungsid, int artikelnr, int kundennr, int punkte, String datum, String text) {
		super();
		this.bewertungsid = bewertungsid;
		this.artikelnr = artikelnr;
		this.kundennr = kundennr;
		this.punkte = punkte;
		this.text = text;
		this.datum = datum;
	}
	
	
	public Bewertung(int bewertungsid, int artikelnr, int kundennr, int punkte, String text) {
		super();
		this.bewertungsid = bewertungsid;
		this.artikelnr = artikelnr;
		this.kundennr = kundennr;
		this.punkte = punkte;
		this.text = text;
		this.datum = new Date().toString();
	}
	
	public Bewertung(int bewertungsid, Produkt produkt, Kunde kunde, int punkte, String datum, String text) {
		super();
		this.bewertungsid = bewertungsid;
		this.setProdukt(produkt);
		this.kunde = kunde;
		this.punkte = punkte;
		this.text = text;
		this.datum = new Date().toString();
	}

	public int getBewertungsid() {
		return bewertungsid;
	}

	public void setBewertungsid(int bewertungsid) {
		this.bewertungsid = bewertungsid;
	}

	public int getArtikelnr() {
		return artikelnr;
	}

	public void setArtikelnr(int artikelnr) {
		this.artikelnr = artikelnr;
	}

	public int getKundennr() {
		return kundennr;
	}

	public void setKundennr(int kundennr) {
		this.kundennr = kundennr;
	}

	public int getPunkte() {
		return punkte;
	}

	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}


	public Kunde getKunde() {
		return kunde;
	}


	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}


	public Produkt getProdukt() {
		return produkt;
	}


	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

}
