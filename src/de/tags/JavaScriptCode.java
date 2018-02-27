package de.tags;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;
/**
 * Diese Klasse liefert Javascript um dem Klick den Wechsel
 * zwischen Nav und Login Tag zu machen.
 * Hinweis: Die Funktion ist nicht fertig gestellt.
 * @author paul4
 *
 */
public class JavaScriptCode extends TagSupport {
	/**
	 *SUID automatisch generiert.
	 **/
	private static final long serialVersionUID = 1L;
	/**
	 * Der JS Code um bei Button Click zu wechseln.
	 */
	final String js = "<script>"
			+ "function onclick() {} "
			+ "</script>";
	/**
	 *@return Ausdruck. 
	 **/
	public int doStartTag() {
		try {
			pageContext.getOut().append(js);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}

}
