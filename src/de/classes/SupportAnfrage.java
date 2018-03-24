package de.classes;

import java.util.ArrayList;
import java.util.List;

import de.databaseOperations.SupportOperations;
/**
 * Diese Klasse bildet die Anfrage an den Admin ab.
 * @author Paul Blanke
 *
 */
public class SupportAnfrage {
	/**
	 * DB-ID.
	 */
	private int support_id;
	
	/**
	 * Eine Liste aller Anfragen
	 */
	private List<SupportAnfrage> anfragen = new ArrayList<>();
	/**
	 * Der Betreff.
	 */
	private String anfrage;
	/**
	 * Beschreibung der Anfrage.
	 */
	private String text;
	/**
	 * Grund zB Umtausch.
	 */
	private String grund;
	// getter und Setter
	public int getSupport_id() {
		return support_id;
	}
	public void setSupport_id(int support_id) {
		this.support_id = support_id;
	}
	public List<SupportAnfrage> getAnfragen() {
		return anfragen;
	}
	public void setAnfragen(List<SupportAnfrage> anfragen) {
		this.anfragen = anfragen;
	}
	public String getAnfrage() {
		return anfrage;
	}
	public void setAnfrage(String anfrage) {
		this.anfrage = anfrage;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getGrund() {
		return grund;
	}
	public void setGrund(String grund) {
		this.grund = grund;
	}
	
	/**
	 * Konstruktor
	 * @param anfrage Betreff 
	 * @param text
	 * @param grund
	 */
	public SupportAnfrage(String anfrage, String text, String grund) {
		this.setSupport_id(SupportOperations.hoechsteID());
		this.anfrage = anfrage;
		this.text = text;
		this.grund = grund;
		addAnfrage(this);
	}
	
	/**
	 * Diese Methoden f&uegt eine Supportanfrage in die Liste ein.
	 * @param support
	 */
	private void addAnfrage(SupportAnfrage support) {
		this.anfragen.add(support);
	}
	/**
	 * Diese Methoden l&oescht eine Supportanfrage aus der Liste.
	 * @param support
	 */
	private void removeAnfrage(SupportAnfrage anfrage) {
		this.anfragen.remove(anfrage);
	}
	
	
	
	
	
	
	

}
