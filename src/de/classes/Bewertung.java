package de.classes;
/***
 * Diese Klasse bildet Produktbewertungen ab.
 * @author Paul Blanke
 *
 */
public class Bewertung {
	/**
	 * zählt die Anzahl der Bewertungen.
	 */
	private static int counter = 0;
	/**
	 * DB-ID.
	 */
	private int bw_id;
	/**
	 * Höhe der Bewertung.
	 */
	private int bewertung;
	/**
	 * Anzahl der Gesamtbewertung
	 */
	private int anzahlBewertung;
	/**
	 * Das bewertete Produkt.
	 */
	private int pro_id;
	/**
	 * Der Kunde, der die Bewertung vorgenommen hat.
	 */
	private int kundennr;
	/**
	 * Der Text der Bewertung.
	 */
	private String inhalt;
	
	
	
	// get und Set Methoden 
	public int getBw_id() {
		return bw_id;
	}



	public void setPw_id(int pw_id) {
		this.bw_id = pw_id;
	}



	public int getBewertung() {
		return bewertung;
	}



	public void setBewertung(int bewertung) {
		this.bewertung = bewertung;
	}



	public int getAnzahlBewertung() {
		return anzahlBewertung;
	}



	public void setAnzahlBewertung(int anzahlBewertung) {
		this.anzahlBewertung = anzahlBewertung;
	}



	public int getPro_id() {
		return pro_id;
	}



	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	
	public int getKundennr() {
		return kundennr;
	}



	public void setKundennr(int kundennr) {
		this.kundennr = kundennr;
	}


	public String getInhalt() {
		return inhalt;
	}



	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}



	/**
 	* Konstruktur 
 	* @param bewertung Höhe der Bewertung
 	* @param prod_id die Produkt-ID.
 	* @param kundennr Die Kundennr.
 	*/
	public Bewertung(int bewertung, int prod_id, int kundennr) {
		counter++;
		this.anzahlBewertung = counter;
		this.setPro_id(prod_id);
		if(0 < bewertung  && bewertung <= 5) {
			this.setBewertung(bewertung);
		} else {
			this.setBewertung(5);
		}
		this.setKundennr(kundennr);
		// TODO Auto-generated constructor stub
	}
	


	/**
	 * Konstruktur
	 * @param bewertung Höhe der Bewertung.
	 * @param produkt Gegenstand der Bewertung
	 * @param kunde   Auslöser der Bewertung.
	 */
	public Bewertung(int bewertung, Produkt produkt, Kunde kunde) {
		counter++;
		this.anzahlBewertung = counter;
		this.setPro_id(produkt.getProdukt_id());
		if(0 < bewertung && bewertung <= 5) {
			this.setBewertung(bewertung);
		} else {
			this.setBewertung(5);
		}
		this.kundennr = kunde.getNutzer_id();
		
	}
	
	/**
	 * Konstruktur
	 * @param bewertung Höhe der Bewertung.
	 * @param produkt Gegenstand der Bewertung
	 * @param kunde   Auslöser der Bewertung.
	 * @param inhalt Beschreibung.
	 */
	public Bewertung(int bewertung, Produkt produkt, Kunde kunde, String inhalt) {
		counter++;
		this.anzahlBewertung = counter;
		this.setPro_id(produkt.getProdukt_id());
		if(0 < bewertung && bewertung <= 5) {
			this.setBewertung(bewertung);
		} else {
			this.setBewertung(5);
		}
		this.kundennr = kunde.getNutzer_id();
		
	}
	/**
	 * testemethode für Ausgabe.
	 * @return Ausgabe der Bewertung.
	 */
	
	public String bewerten() {
		String wertung = "";
		if(0< bewertung && bewertung <= 2) {
			wertung = "Das Produkt wurde schlecht bewertet!";
		} else if(bewertung == 3) {
			wertung = "Das Produkt wurde durchschnittlich bewertet.";
		} else if(3 < bewertung  && bewertung <= 5   ) {
			wertung ="Das Produkt ist super";
		}
		return wertung;
	}

}
