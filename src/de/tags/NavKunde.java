package de.tags;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;

import de.utilities.ReadFromFile;


public class NavKunde extends TagSupport {
	private static final long serialVersionUID = 1L;

	private String rolle;
	

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
	

	private String getNavBar(String parameter) throws IOException {
		
		String nav = ReadFromFile.readContentFromFile(pageContext, parameter);
		
		return nav;
	}

}
