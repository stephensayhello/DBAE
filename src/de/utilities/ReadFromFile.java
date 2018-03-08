package de.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.jsp.PageContext;

public class ReadFromFile {

	public static String readContentFromFile(PageContext pageContext, String file) {
		String path = pageContext.getServletContext().getRealPath("/html/");
		String content = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(path + file));
			String str;
			while ((str = in.readLine()) != null) {
				content += str;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
}
