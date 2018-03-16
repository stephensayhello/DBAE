package de.classes;

import java.util.ArrayList;
import java.util.List;

public class Produktgruppe {
	public List<Produkt> produkte = new ArrayList<>();
    private int artnr;
	
     
        
    
    
    public Produktgruppe(List<Produkt> produkte, int artnr) {
		super();
		this.produkte = produkte;
		this.artnr = artnr;
	}
	public int getArtnr() {
		return artnr;
	}
	public void setArtnr(int artnr) {
		this.artnr = artnr;
	}
    
	
    
    
}

	