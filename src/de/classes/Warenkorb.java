package de.classes;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.databaseOperations.WarenkorbOperations;
/**
 * Diese Klasse bildet den Warenkorb ab.
 * @author Benjamin Gajewski
 *
 */
public class Warenkorb {
	/**
	 * DB-Id
	 */
	private int warenkorb_id;
	/**
	 * Eine Liste von Produkten im Warenkorb.
	 */
	private List<Produkt> produkte;
	/**
	 * Der Account, der den Warenkorb bestellt.
	 */
	private Kunde kunde;
	
	 private String gesamtpreisineuro;
	 // Getter und Setter
	 
	 public Kunde getKunde() {
			return kunde;
		}

		public void setKunde(Kunde kunde) {
			this.kunde = kunde;
		}
		
		
		public List<Produkt> getInhalt() {
			return produkte;
		}

		public void setInhalt(List<Produkt> produkte) {
			this.produkte = produkte;
		}
		
		public void setGesamtpreis(int gesamtpreis) {
		}

		public int getWarenkorb_id() {
			return warenkorb_id;
		}

		public void setWarenkorb_id(int warenkorb_id) {
			this.warenkorb_id = warenkorb_id;
		}
		public void setGesamtpreisineuro(String gesamtpreisineuro) {
			
			this.gesamtpreisineuro = gesamtpreisineuro;
		}
	/**
	* Konstruktor 
	* DB-Id wird aus der 
	* @param kunde Kundenobjekt @Kunde
	* @param produkte Produkt im Warenkorb @Produkt
	*/
	public Warenkorb(Kunde kunde,List<Produkt> produkte){
	    this.setWarenkorb_id(WarenkorbOperations.hoechsteID());
		this.setKunde(kunde);
		this.produkte = produkte;
	}
	/**
	* Konstruktor 
	* DB-Id wird aus der 
	* @param kunde Kundenobjekt @Kunde
	*/
	public Warenkorb(Kunde kunde){
		this.setWarenkorb_id(WarenkorbOperations.hoechsteID());
		this.setKunde(kunde);
		this.produkte = new ArrayList<Produkt>();
	}
	/**
	 * Konstruktor
	 * @param kunde Kundenobjekt @Kunde
	 * @param produkte liste Produkte im warenkorb
	 * @param id Die ID.
	 */
	public Warenkorb(Kunde kunde,List<Produkt> produkte, int id){
		this.setWarenkorb_id(id);
		this.setKunde(kunde);
		this.produkte = produkte;
	}
	
// Methoden
	
	/**
	 * Ersatzmethode f&uer get
	 * berechnet anhand der Liste den gesamtpreis aller Produkte im Warenkorb.
	 * @return Der Gesamtpreis des Warenkorbs.
	 */
	public double getGesamtpreis() {
	double gesamtpreis = 0;
		for (Produkt produkt : produkte) {
			 gesamtpreis = gesamtpreis + (produkt.getAnzahl() * produkt.getPreis());
		}
		return gesamtpreis;
	}
	/**
	 *  Diese Methode ermittelt die  l&aengste Versanddauer anhand aller Produkte. 
	 * @return die L&aengste Versanddauer
	 */
	public int gethoechsteVersanddauer(){
		int versanddauer = 0;
		if(produkte.size()!=0){
		 versanddauer = produkte.get(0).getVersanddauer();
		for (Produkt produkt : produkte) {
			if(produkt.getVersanddauer()>versanddauer){
				versanddauer = produkt.getVersanddauer();
			}
		}}
		
		return versanddauer;
		
	}

	
	
	/**
	 * Diese Methode berechnet den Gesamtrpeis aller Produkte in Euro als String.
	 * @return Gesamtpreis f&uer Html Ausgabe.
	 */
	public String getGesamtpreisineuro() {
		double gesamtpreis = 0;
		for (Produkt produkt : produkte) {
			 gesamtpreis = gesamtpreis + (produkt.getAnzahl() * produkt.getPreis());
		}
		return NumberFormat.getCurrencyInstance(Locale.GERMANY).format(gesamtpreis);
	}

	


}
