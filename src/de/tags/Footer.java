package de.tags;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;
/**
 * Diese Klasse bringt die Fussleiste/ Impression als Tag dazu.
 * @author Paul Blanke
 *
 */
public class Footer  extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final String footerHmtl ="<style>#footer {"
			+ "postion='fixed'; left=0; bottom: 0; width: 100%; background-color: red; color: white; text-align: center; } </style>"
			+ "<div class='footer' id='footer'><p>SportWeb Gmbh</p></div>";
	
	
	public int doStartTag() {
		try {
			pageContext.getOut().append(footerHmtl);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	

	
	
	
	
}
