package de.classes;

public class Adresse {
	
	private String strasse;
	private String hausnummer;
	private String plz;
	private String ort;
	
	

	public String getStrasse() {
		return strasse;
	}


	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}


	public String getHausnummer() {
		return hausnummer;
	}


	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}


	public String getPlz() {
		return plz;
	}


	public void setPlz(String plz) {
		this.plz = plz;
	}


	public String getOrt() {
		return ort;
	}


	public void setOrt(String ort) {
		this.ort = ort;
	}


	public Adresse(String strasse, String hausnummer, String Plz, String ort) {
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.plz = Plz;
		this.ort = ort;
		// TODO Auto-generated constructor stub
	}
	
	

}
