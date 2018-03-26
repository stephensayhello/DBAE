package de.tags;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;

import de.classes.Bewertung;
import de.utilities.ReadFromFile;

/**
 * Der tag liefert einen footer deer die Anforderung Daten bearbeiten.
 * @author Stephen Galla
 *
 */
public class BewertungsBox extends TagSupport {
	/**
	 * Autogeneriert
	 */
	private static final long serialVersionUID = 1L;
	private Bewertung bewertung;

	public int doStartTag() {
		try {
			pageContext.getOut().append(getBox());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private String getBox() throws IOException {
		String box = ReadFromFile.readContentFromFile(pageContext, "bewertung.html");
		box = box.replaceAll("PLATZHALTER2", bewertung.getKunde().getVorname());
		box = box.replaceAll("PLATZHALTER3", bewertung.getKunde().getNachname());
		box = box.replaceAll("PLATZHALTER4", bewertung.getText());
		
		String sterne = "";
		
		for (int i = 1; i <= 5; i++) {
			if(i <= bewertung.getPunkte()) {
				sterne += "<span class='glyphicon glyphicon-star'></span>";
			} else {
				sterne += "<span class='glyphicon glyphicon-star-empty'></span>";
			}
		}
		
		
		
		
		box = box.replaceAll("PLATZHALTER5",sterne);
		
		return box;
	}

	public Bewertung getBewertung() {
		return bewertung;
	}

	public void setBewertung(Bewertung bewertung) {
		this.bewertung = bewertung;
	}

}