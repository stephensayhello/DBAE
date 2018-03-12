package de.classes;

import de.databaseOperations.AdresseOperations;

public class Adresse {

	private String strasse;
	private String hausnummer;
	private int plz;
	private String ort;
	private int adress_id;
	
	public Adresse(String strasse, String hausnr, int Plz, String ort) {
		this.adress_id = AdresseOperations.hoechsteID();
		this.strasse = strasse;
		this.hausnummer = hausnr;
		this.plz = Plz;
		this.ort = ort;
	}
	
	public Adresse(int adress_id, String strasse, String hausnummer, int Plz, String ort) {
		this.adress_id = adress_id;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.plz = Plz;
		this.ort = ort;
	}
	

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnummer() {
		return hausnummer;
	}


	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	

	public int getAdress_id() {
		return adress_id;
	}

	public void setAdress_id(int adress_id) {
		this.adress_id = adress_id;
	}
	

}
