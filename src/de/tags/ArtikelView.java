package de.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import de.classes.Hose;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.databaseOperations.BewertungsOperations;
import de.databaseOperations.HoseOperations;
import de.databaseOperations.SchuheOperations;
import de.databaseOperations.ShirtOperations;
import de.utilities.ReadFromFile;
/**
 * Diese Klasse den Tag ArtikelView. Die Artikelview liefert einen 
 * &Uumlberblick &uumlber alle Artikel
 * @author Stephen Galla
 *
 */
public class ArtikelView extends TagSupport {
	/**
	 * Auto Generiert
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @Produkt eine Instanz der Klasse Produkt 
	 */
	private Produkt produkt;
	/**
	 * Ein Z&aumlhler
	 */
	public static int counter = 0;
	/**
	 * Diese Variablen enthalten Gr&oumlssen f&uumler die Ausgabe
	 */
	private static final String[] SHIRT_GROESSEN = { "S", "M", "L", "XL", "XXL" };
	private static final String[] HOSE_GROESSEN = { "28", "30", "32", "34", "38", "40", "42" };
	private static final String[] SCHUHE_GROESSEN = { "38", "39", "40", "41", "42", "43", "44", "45" };
// Getter und Setter
	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}
	/**
	 * Tag Methode 
	 */
	public int doStartTag() {
		try {
			pageContext.getOut().append(getArtikelView());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	/**
	 * Hilfsmethode, die die Ausgabe Erstellt-
	 * @return die Ausgabe in HTML Form
	 * @throws IOException Fehler, wenn das File nicht gelesen wurde.
	 */
	private String getArtikelView() throws IOException {
		String artikelview = ReadFromFile.readContentFromFile(pageContext, "artikel.html");

		
		String sterne = "";
		double durchschnitt = BewertungsOperations.ladeDurschnitt(produkt.getArtikelnr());
		int durchschnittgerundet = (int) Math.round(durchschnitt);
		for (int i = 1; i <= 5; i++) {
			if(i <= durchschnittgerundet) {
				sterne += "<span class='glyphicon glyphicon-star'></span>";
			} else {
				sterne += "<span class='glyphicon glyphicon-star-empty'></span>";
			}
		}
		
		
		String produktpreis = produkt.getPreisineuro();

		artikelview = artikelview.replace("PLATZHALTER0", Integer.toString(produkt.getProdukt_id()));
		artikelview = artikelview.replace("PLATZHALTER1", produkt.getName());
		artikelview = artikelview.replace("PLATZHALTER2", produkt.getBeschreibung());
		artikelview = artikelview.replace("PLATZHALTER3", String.valueOf(produkt.getArtikelnr()));
		artikelview = artikelview.replace("PLATZHALTER4", produktpreis);
		artikelview = artikelview.replace("PLATZHALTER6", sterne);
		
		if(produkt.getImagePath().isEmpty()) {
			artikelview = artikelview.replace("PLATZHALTER5", "img/dummy.jpg");

		} else {
			artikelview = artikelview.replace("PLATZHALTER5", produkt.getImagePath());

		}
		
		String options = "";
		List<Object> groessen = null;
		
		if(produkt instanceof Shirt) {
			groessen = ShirtOperations.ladeShirtGroessen(produkt.getArtikelnr());
		} else if(produkt instanceof Schuhe) {
			groessen = SchuheOperations.ladeSchuheGroessen(produkt.getArtikelnr());
		} else if(produkt instanceof Hose) {
			groessen = HoseOperations.ladeHoseGroessen(produkt.getArtikelnr());
		}
		
		
		for (Object value : groessen) {
			options += "<option value=" + value + ">" + value + "</option>";
		}
		artikelview = artikelview.replace("PLATZHALTERGROESSE", options);
		
		
		return artikelview;
	}

}
