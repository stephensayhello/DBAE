package de.classes;

import java.util.ArrayList;
import java.util.List;

import de.databaseOperations.SupportOperations;

public class SupportAnfrage {

	private int support_id;
	
	
	private List<SupportAnfrage> anfragen = new ArrayList<>();
	
	private String anfrage;
	private String text;
	private String grund;
	
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
	
	// konstruktor
	
	public SupportAnfrage(String anfrage, String text, String grund) {
		this.setSupport_id(SupportOperations.hoechsteID());
		this.anfrage = anfrage;
		this.text = text;
		this.grund = grund;
		addAnfrage(this);
	}
	
	
	private void addAnfrage(SupportAnfrage support) {
		this.anfragen.add(support);
	}
	
	private void removeAnfrage(SupportAnfrage anfrage) {
		this.anfragen.remove(anfrage);
	}
	
	
	
	
	
	
	

}
