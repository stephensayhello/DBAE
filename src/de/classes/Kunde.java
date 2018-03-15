package de.classes;

import de.databaseOperations.NutzerOperations;

public class Kunde extends Nutzer {

	private Adresse adresse;
	private String vorname;
	private String nachname;

	public Kunde(int kundennr, String passwort, String email, Adresse adresse, String vorname, String nachname) {
		super(kundennr, passwort, email);
		this.adresse = adresse;
		this.vorname = vorname;
		this.nachname = nachname;
	}
	public Kunde( String passwort, String email, Adresse adresse, String vorname, String nachname) {
		super(NutzerOperations.hoechsteID(), passwort, email);
		this.adresse = adresse;
		this.vorname = vorname;
		this.nachname = nachname;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	
}
