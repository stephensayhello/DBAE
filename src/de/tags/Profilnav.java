package de.tags;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;

import de.utilities.ReadFromFile;

/**
 * Der tag liefert eine Navigationsleiste f&uer die Anforderung Daten bearbeiten.
 * @author Paul blanke
 *
 */
public class Profilnav extends TagSupport {
	/**
	 * Autogeneriert
	 */
	private static final long serialVersionUID = 1L;

	public int doStartTag() {
		try {
			pageContext.getOut().append(getProfilnav());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private String getProfilnav() throws IOException {
		String nav = ReadFromFile.readContentFromFile(pageContext, "profilnav.html");
		
		return nav;
	}

}