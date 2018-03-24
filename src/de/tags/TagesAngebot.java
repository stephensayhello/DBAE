package de.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import de.classes.Bewertung;
import de.classes.Hose;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.utilities.ReadFromFile;
/**
 * Diese Klasse liefert einen Tag speziell f&uer das tagesangebot dazu.
 * @author Paul Blanke
 *
 */
public class TagesAngebot  extends TagSupport{
	/**
	 * autogeneriert.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Ein Produkt Objekt. @Produkt
	 */
	Produkt produkt;
	/**
	 * 
	 * die durchschnittliche Punktzahl.
	 */
	int durchschnitt;
	/**
	 * Arrays von Groessen.
	 */
	private static final String[] SHIRT_GROESSEN = { "S", "M", "L", "XL", "XXL" };
	private static final String[] HOSE_GROESSEN = { "28", "30", "32", "34", "38", "40", "42" };
	private static final String[] SCHUHE_GROESSEN = { "38", "39", "40", "41", "42", "43", "44", "45" };

	
	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}
	
	public void setDurchschnitt(int durchschnitt) {
		this.durchschnitt = durchschnitt;
		
	}
	
	public int doStartTag() {
		try {
			pageContext.getOut().append(getTagesAngebotView());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	/**
	 * Diese Methode liefert den HTML-teil.
	 * @return
	 */
	private String getTagesAngebotView() {
		String artikelview = ReadFromFile.readContentFromFile(pageContext, "tagesangebot.html");
		
		String produktpreis = produkt.getPreisineuro();

		artikelview = artikelview.replace("PLATZHALTER0", Integer.toString(produkt.getProdukt_id()));
		artikelview = artikelview.replace("PLATZHALTER1", produkt.getName());
		artikelview = artikelview.replace("PLATZHALTER2", produkt.getBeschreibung());
		artikelview = artikelview.replace("PLATZHALTER3", String.valueOf(produkt.getArtikelnr()));
		artikelview = artikelview.replace("PLATZHALTER4", produktpreis);
		artikelview = artikelview.replace("PLATZHALTER6",Integer.toString(durchschnitt) );
		if(produkt.getImagePath().isEmpty()) {
			artikelview = artikelview.replace("PLATZHALTER5", "img/dummy.jpg");

		} else {
			artikelview = artikelview.replace("PLATZHALTER5", produkt.getImagePath());

		}
		
		String options = "";
		String[] groessen = null;
		
		if(produkt instanceof Shirt) {
			groessen = SHIRT_GROESSEN;
		} else if(produkt instanceof Schuhe) {
			groessen = SCHUHE_GROESSEN;
		} else if(produkt instanceof Hose) {
			groessen = HOSE_GROESSEN;
		}
		
		for (Object value : groessen) {
			options += "<option value=" + value + ">" + value + "</option>";
		}
		artikelview = artikelview.replace("PLATZHALTERGROESSE", options);
		
		
		return artikelview;
	}

}
