package de.classes;
/**
 * Die Klasse für den Kunden.
 * @author Paul Blanke
 *
 */
public class Kunde extends Nutzer{
	/**
	 *@param kundenr: Die Kundennr. 
	 */
	private int kundennr;
	/**
	 *@param Zahlung: Ein Objekt der Klasse zahlung. 
	 */
	private Zahlung zahlung;
	/**
	 *@param status: Der kundenstatus also bestandskunde oder Neukunde.
	 */
	private String status;
	
// get und set Methoden.	
	
	public int getKundennr() {
		return kundennr;
	}
	public void setKundennr(int kundennr) {
		this.kundennr = kundennr;
	}
	public Zahlung getZahlung() {
		return zahlung;
	}
	public void setZahlung(Zahlung zahlung) {
		this.zahlung = zahlung;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
// konstruktor
	public Kunde() {
		
	}
	
	public Kunde(int knr, String name, String adresse, String email, Zahlung zahlung) {
		
		this.setKundennr(knr);
		this.setName(name);
		this.setAdresse(adresse);
		this.setEmail(email);
		this.setZahlung(zahlung);
		this.setStatus("Neukunde");
		
	}
}
