package de.tags;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;
/**
 * Diese Klasse bildet die Basis für die Navigationsleiste.
 * @author Paul Blanke.
 *
 */
public class Navigation  extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int doStartTag() {
			try {
				pageContext.getOut().append(getNavBar());	
			} catch (IOException e) {
				e.printStackTrace();
			}
		return SKIP_BODY;
	}
	
	
	private String getNavBar() {
		String nav = "<ul style='list-style-type: none; margin: 0; padding: 0; overflow: hidden; background-color: #333;'>"
				+ "<li style='float: left;'><a style='display: block; color: white; text-align: center; padding: 14px 16px; text-decoration: none; border-right: 1px solid #ADACAB; width: 50px;' href='index.jsp'>Startseite</a></li>"
				+ "<li style='float: left;'><a style='display: block; color: white; text-align: center; padding: 14px 16px; text-decoration: none; border-right: 1px solid #ADACAB; width: 50px;' href='Artikeluebersicht.jsp'>Artikeluebersicht</a></li>"
				+ "<li style='float: left;'><a style='display: block; color: white; text-align: center; padding: 14px 16px; text-decoration: none; border-right: 1px solid #ADACAB; width: 50px;' href='Suche.jsp'>Suche</a></li>"
				+ "<li style='float: right;'><a style='display: block; color: white; background-color: #005883; text-align: center; padding: 14px 16px; text-decoration: none; width: 50px;' href='LoginIn.jsp'>LogIn</a></li>"
			+ "</ul>";
		
		
		return nav;
		
	}
	

}
