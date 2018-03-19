package de.classes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import de.databaseOperations.BestellungOperations;

public class Bestellung {
	private int bestellnummer;
	
	private List <Produkt> bestellliste;
	
	private Date date;
	
	private Kunde kunde;
	
	public Bestellung(int bestellnummer, List<Produkt> bestellliste,  Kunde kunde) {
		
		this.bestellnummer = bestellnummer;
		this.bestellliste = bestellliste;
		this.date = new Date(2018, 12, 20);
		this.kunde = kunde;
	}
	public Bestellung(List<Produkt> bestellliste,  Kunde kunde) {
		
		this.bestellnummer = BestellungOperations.hoechsteID() ;
		this.bestellliste = bestellliste;
		this.date = new Date(2018, 12, 20);
		this.kunde = kunde;
	}

	public int getBestellnummer() {
		return bestellnummer;
	}

	public void setBestellnummer(int bestellnummer) {
		this.bestellnummer = bestellnummer;
	}

	public List<Produkt> getBestellliste() {
		return bestellliste;
	}

	public void setBestellliste(List<Produkt> bestellliste) {
		this.bestellliste = bestellliste;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public void createDate() {
		
	}
	
	
	
	
}
