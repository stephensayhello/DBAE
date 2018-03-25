package de.tags;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;

import de.utilities.ReadFromFile;

/**
 * Der tag liefert einen footer deer die Anforderung Daten bearbeiten.
 * @author Stephen Galla
 *
 */
public class Footer extends TagSupport {
	/**
	 * Autogeneriert
	 */
	private static final long serialVersionUID = 1L;

	public int doStartTag() {
		try {
			pageContext.getOut().append(getFooter());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private String getFooter() throws IOException {
		String footer = ReadFromFile.readContentFromFile(pageContext, "footer.html");
		
		return footer;
	}

}