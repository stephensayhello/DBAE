package de.tags;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;

import de.utilities.ReadFromFile;

/**
 * Dieser Tag liefert eine Navigationsleiste f&uer den Kunden.
 * @author Benjamin Gajewski
 *
 */
public class NavKunde extends TagSupport {
	private static final long serialVersionUID = 1L;
	/**
	 * Ein Attribute, was unterscheidet, welche Navigationsleiste gezeigt wird.  
	 * Tag Parameter     
	 */
	private String rolle;
	
// Setter 
	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	public int doStartTag() {
		try {
			if(rolle.contains("kunde")){
				pageContext.getOut().append(getNavBar("navKunde.html"));
			} else if (rolle.contains("admin")){
				pageContext.getOut().append(getNavBar("navigationadmin.html"));
			} else {
				pageContext.getOut().append(getNavBar("navigation.html"));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
/**
 * 
 * @param parameter Das File, welches geladen werden soll.
 * @return Inhalt in HTML Form
 * @throws IOException
 */
	private String getNavBar(String parameter) throws IOException {
		
		String nav = ReadFromFile.readContentFromFile(pageContext, parameter);
		
		return nav;
	}

}
