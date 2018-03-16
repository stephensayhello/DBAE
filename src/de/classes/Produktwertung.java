package de.classes;

public class Produktwertung {
	private static int counter = 0;
	
	private int pw_id;
	
	private int bewertung;
	
	private int anzahlBewertung;
	
	private int pro_id;
	
	
	
	public int getPw_id() {
		return pw_id;
	}



	public void setPw_id(int pw_id) {
		this.pw_id = pw_id;
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



	public Produktwertung(int bewertung, int prod_id) {
		counter++;
		this.anzahlBewertung = counter;
		this.setPro_id(prod_id);
		if(0 < bewertung  && bewertung <= 5) {
			this.setBewertung(bewertung);
		} else {
			this.setBewertung(5);
		}
		
		// TODO Auto-generated constructor stub
	}
	
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
