package de.tags;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;

import de.classes.Produkt;
import de.utilities.ReadFromFile;

public class ArtikelView extends TagSupport {
	private static final long serialVersionUID = 1L;
	private Produkt produkt;

	

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

	public int doStartTag() {
		try {
			pageContext.getOut().append(getArtikelView());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private String getArtikelView() throws IOException {
		String artikelview = ReadFromFile.readContentFromFile(pageContext, "artikel.html");
		artikelview = artikelview.replace("PLATZHALTER1", produkt.getName());
		artikelview = artikelview.replace("PLATZHALTER2", produkt.getBeschreibung());
		artikelview = artikelview.replace("PLATZHALTER3", String.valueOf(produkt.getProdukt_id()));
		artikelview = artikelview.replace("PLATZHALTER4", String.valueOf(produkt.getPreis()));
		

		return artikelview;
	}

}
