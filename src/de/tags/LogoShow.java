package de.tags;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * Diese Klasse bindet über einen 
 * @see tag.tld Tag das Logo auf jeder Seite ein.
 * @author Paul Blanke
 *
 */
public class LogoShow extends TagSupport {
	/**
	 *SVUID. 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 *	Der Logo Pfad. 
	 */
	final String logo ="<img src='img/Logo.jpg'  alt='Logo'>";
	/**
	 * 
	 * @return zeigt die gesamte Seite.
	 */
	public int doStartTag() {
		try {
			pageContext.getOut().append(logo);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	return SKIP_BODY;
	}
}
