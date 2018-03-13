package de.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import de.utilities.ReadFromFile;

public class Modal extends TagSupport {
	private static final long serialVersionUID = 1L;
	private List<String> messages;

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public int doStartTag() {
		try {
			pageContext.getOut().append(getModal());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private String getModal() throws IOException {
		
		if(messages == null) {
			return "";
		}
		
		String nav = ReadFromFile.readContentFromFile(pageContext, "modal.html");
		String[] modalParts = nav.split("PLATZHALTER");
		
		String message = "";
		for (String item : messages) {
			message += item + "<br>";
		}

		return modalParts[0] + message + modalParts[1];
	}

}
