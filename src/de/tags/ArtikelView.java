package de.tags;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;

import de.classes.Produkt;
import de.utilities.ReadFromFile;

public class ArtikelView extends TagSupport {
	private static final long serialVersionUID = 1L;
	private Produkt produkt;

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

	public int doStartTag() {
		try {
			pageContext.getOut().append(getNavBar());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private String getNavBar() throws IOException {
		String nav = ReadFromFile.readContentFromFile(pageContext, "artikel.html");
		nav = nav.replace("PLATZHALTER1", produkt.getName());
		nav = nav.replace("PLATZHALTER2", produkt.getBeschreibung());
		nav = nav.replace("PLATZHALTER3", String.valueOf(produkt.getProdukt_id()));
		nav = nav.replace("PLATZHALTER4", String.valueOf(produkt.getPreis()));
		

		return nav;
	}

}
