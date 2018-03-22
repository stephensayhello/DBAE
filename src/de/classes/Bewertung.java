package de.classes;

import de.databaseOperations.BewertungsOperations;

/***
 * Diese Klasse bildet Produktbewertungen ab.
 * @author Paul Blanke
 *
 */
public class Bewertung {
	/**
	 * zählt die Anzahl der Bewertungen.
	 */

	/**
	 * DB-ID.
	 */
	private int bewertung_id;
	/**
	 * Höhe der Bewertung.
	 */
	private int punkte;
	
	/**
	 * Das bewertete Produkt.
	 */
	private int produkt_id;
	/**
	 * Der Kunde, der die Bewertung vorgenommen hat.
	 */
	private int kundennr;
	/**
	 * Der Text der Bewertung.
	 */
	private String kommentar;
	
	
	
	// get und Set Methoden 
	public int getBewertung_id() {
		return bewertung_id;
	}

	public void setBewertung_id(int bewertung_id) {
		this.bewertung_id = bewertung_id;
	}

	public int getPunkte() {
		return punkte;
	}
	
	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}
	
	
	
	
	public int getProdukt_id() {
		return produkt_id;
	}

	public void setProdukt_id(int produkt_id) {
		this.produkt_id = produkt_id;
	}

	public int getKundennr() {
		return kundennr;
	}



	public void setKundennr(int kundennr) {
		this.kundennr = kundennr;
	}


	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public Bewertung() {
		
	}
	/**
 	* Konstruktur 
 	* @param punkte Höhe der Bewertung
 	* @param produkt_id die Produkt-ID.
 	* @param kundennr Die Kundennr.
 	*/
	public Bewertung(int punkte, int produkt_id, int kundennr) {
	
		this.bewertung_id = BewertungsOperations.hoechsteID();
		this.produkt_id = produkt_id;
		this.kundennr = kundennr;
		
		if(0 < punkte  && punkte <= 5) {
			this.punkte = punkte;
		} else {
			this.punkte = 5;
		}
		this.kommentar = "";
	}
	


	/**
	 * Konstruktur
	 * @param punkte Höhe der Bewertung.
	 * @param produkt Gegenstand der Bewertung
	 * @param kunde   Auslöser der Bewertung.
	 */
	public Bewertung(int punkte, Produkt produkt, Kunde kunde) {
		this.bewertung_id = BewertungsOperations.hoechsteID();
		this.produkt_id = produkt.getProdukt_id();
		this.kundennr = kunde.getNutzer_id();
		
		if(0 < punkte  && punkte <= 5) {
			this.punkte = punkte;
		} else {
			this.punkte = 5;
		}
		this.kommentar = "";
		
	}
	
	/**
	 * Konstruktur
	 * @param punkte Höhe der Bewertung.
	 * @param produkt Gegenstand der Bewertung
	 * @param kunde   Auslöser der Bewertung.
	 * @param kommentar Beschreibung.
	 */
	public Bewertung(int punkte, Produkt produkt, Kunde kunde, String kommentar) {
		this.bewertung_id = BewertungsOperations.hoechsteID();
		this.produkt_id = produkt.getProdukt_id();
		this.kundennr = kunde.getNutzer_id();
		
		if(0 < punkte  && punkte <= 5) {
			this.punkte = punkte;
		} else {
			this.punkte = 5;
		}
		this.kommentar = kommentar;
		
	}
	
	
	
	/**
 	* Konstruktur 
 	* @param punkte Höhe der Bewertung
 	* @param produkt_id die Produkt-ID.
 	* @param kundennr Die Kundennr.
 	* @param kommentar Der Inhalt.
 	*/
	public Bewertung(int punkte, int produkt_id, int kundennr, String kommentar) {
		
		this.bewertung_id = BewertungsOperations.hoechsteID();
		this.produkt_id = produkt_id;
		this.kundennr = kundennr;
		
		if(0 < punkte  && punkte <= 5) {
			this.punkte = punkte;
		} else {
			this.punkte = 5;
		}
		this.kommentar = kommentar;
	}
	/**
	 * testemethode für Ausgabe.
	 * @return Ausgabe der Bewertung.
	 */
	
	public String bewerten() {
		String wertung = "";
		if(0< this.punkte && this.punkte <= 2) {
			wertung = "Das Produkt wurde schlecht bewertet!";
		} else if(this.punkte == 3) {
			wertung = "Das Produkt wurde durchschnittlich bewertet.";
		} else if(3 < this.punkte  && this.punkte <= 5   ) {
			wertung ="Das Produkt ist super";
		}
		return wertung;
	}

}
