package de.tags;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

public class NavTest  extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int doStartTag() {
		try {
			pageContext.getOut().append(getHTML());	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return SKIP_PAGE;
	}
	
	
	private String getHTML() {
		String ausgabe ="<nav class='navbar navbar-collapse navbar-toggleable-md navbar-light bg-faded'>"
				+ "<div class='coolapse navbar-collapse' >"
				+ "<a class='navbar-brand' href='index.jsp'>Startseite </a><div class='collapse navbar-collapse'>"
				+ "<ul class='nav navbar-nav navbar-right'><li><a href='LoginIn.jsp'><span class='glyphicon glyphicon-user'></span>Sign Up</a></li> "
				+ "<a href='LoginIn.jsp'><span class='glyphicon glyphicon-log-in'></span>Login</a></ul>"
				+ "<a class='navbar-brand' href='Artikeluebersicht.jsp'>Produktübersichtr </a>"
				+ "<form class='navabar-form navbar-left navbar-brand action='Suche.jsp'><div class= 'form-group navbar-collapse'>"
				+ "<input type='text' class='form-control' placeholder='Suchen'></div><button type='submit' class='btn btn-default'>Suche</button>"
				+ "</form>"
				+ "</div></nav>";
		
		return ausgabe;
	}
}
