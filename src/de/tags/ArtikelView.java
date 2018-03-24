package de.tags;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import de.classes.Bewertung;
import de.classes.Hose;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.utilities.ReadFromFile;
/**
 * Diese Klasse den Tag ArtikelView. Die Artikelvie liefert einen 
 * &Ueberblick &ueber alle Artikel
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
	 *@Bewertung eine Instanz der Klasse Bewertung 
	 */
	private List<Bewertung> bewertungen;
	private Bewertung bewertung;
	/**
	 * Ein Z&aehler
	 */
	public static int counter = 0;
	/**
	 * Diese Variablen enthalten Gr&oessen f&uer die Ausgabe
	 */
	private static final String[] SHIRT_GROESSEN = { "S", "M", "L", "XL", "XXL" };
	private static final String[] HOSE_GROESSEN = { "28", "30", "32", "34", "38", "40", "42" };
	private static final String[] SCHUHE_GROESSEN = { "38", "39", "40", "41", "42", "43", "44", "45" };
// Getter und Setter
	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

	public List<Bewertung> getBewertungen() {
		return bewertungen;
	}

	public void setBewertungen(List<Bewertung> bewertungen) {
		this.bewertungen = bewertungen;
	}
	
	public void setBewertung(Bewertung bewertung) {
		this.bewertung = bewertung;
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
		
		
		
		
		
		
		String produktpreis = produkt.getPreisineuro();

		artikelview = artikelview.replace("PLATZHALTER0", Integer.toString(produkt.getProdukt_id()));
		artikelview = artikelview.replace("PLATZHALTER1", produkt.getName());
		artikelview = artikelview.replace("PLATZHALTER2", produkt.getBeschreibung());
		artikelview = artikelview.replace("PLATZHALTER3", String.valueOf(produkt.getArtikelnr()));
		artikelview = artikelview.replace("PLATZHALTER4", produktpreis);
	// artikelview = artikelview.replace("PLATZHALTER6", Integer.toString(bewertung.ermitteleDurchschnitt()));
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
