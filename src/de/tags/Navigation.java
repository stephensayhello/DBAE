package de.tags;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;
/**
 * Diese Klasse bildet die Basis für die Navigationsleiste.
 * @author Paul Blanke.
 *
 */
public class Navigation extends TagSupport{
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
		String nav = "<nav class='navbar navbar-inverse bg-faded navbar-collapse navbar-toggleable-md navbar-light'  >"
		// Links		
		+ "<div class='container-fluid'><div class='navbar-header'><a class='nav-brand' href='index.jsp'>Startseite</a>"
		+ "</div><ul class='nav navbar'><li class='active'><a href='index.jsp'>Home</a></li> "
		+ "<li><a href='Artikeluebersicht.jsp'>Produktübersicht</a></li></ul>"
		// Suche
		+ "<form class='navabar-form navbar-left' action='Suche.jsp'><div class= 'form-group'>"
		+ "<input type='text' class='form-control' placeholder='Suchen'></div><button type='submit' class='btn btn-default'>Submit</button>"
		+ "</form"
		// LogIN
		+ "<ul class='nav navbar-nav navbar-right'><li><a href='LoginIn.jsp'><span class='glyphicon glyphicon-user'></span>Sign Up</a></li> "
		+ "<li><a href='LoginIn.jsp'><span class='glyphicon glyphicon-log-in'></span>Login</a></li></ul>"
		
		+ "</div></nav>";	
		return nav;
		
	}
	
	

}
