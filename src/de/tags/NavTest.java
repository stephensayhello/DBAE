package de.tags;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

public class NavTest extends TagSupport {

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

		String ausgabe = 
				 "<nav class=\"navbar navbar-default\">"+
						"  <div class=\"container-fluid\">"+
						"    <div class=\"navbar-header\">"+
						"      <a class=\"navbar-brand\" href=\"#\">SportWeb</a>"+
						"    </div>"+
						"    <ul class=\"nav navbar-nav\">"+
						"      <li class=\"active\"><a href=\"#\">Home</a></li>"+
						"      <li><a href=\"Artikeluebersicht.jsp\">Artikelübersicht</a></li>"+
						"      <li><a href=\"#\">Tagesangebot</a></li>"+
						"    </ul>"+
						"    <ul class=\"nav navbar-nav navbar-right\">"+
						"      <li><a href=\"\"><span class=\"glyphicon glyphicon-user\"></span> Sign Up</a></li>"+
						"      <li><a href=\"LoginIn.jsp\"><span class=\"glyphicon glyphicon-log-in\"></span> Login</a></li>"+
						"    </ul>"+
						"  </div>"+
						"</nav>";
							
;

		return ausgabe;
	}
}
