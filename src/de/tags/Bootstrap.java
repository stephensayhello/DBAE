package de.tags;

import java.io.IOException;
import javax.servlet.jsp.tagext.TagSupport;

import de.utilities.ReadFromFile;


public class Bootstrap extends TagSupport {
	private static final long serialVersionUID = 1L;

	public int doStartTag() {
		try {
			pageContext.getOut().append(getNavBar());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private String getNavBar() throws IOException {
		String nav = ReadFromFile.readContentFromFile(pageContext, "bootstrap.html");
		
		return nav;
	}

}
