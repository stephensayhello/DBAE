package de.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.jsp.PageContext;
/**
 * Diese Klasse liest den Inhalt aus einer Datei heraus.
 * Wird gverwendet um den HTML-Code aus den Tags auszulagern.
 * @author Stephen Galla
 *
 */
public class ReadFromFile {
	/**
	 * Diese methode liest den Inhalt aus einer Datei heraus.
	 * Es wird BufferdReader verwendet.
	 * @param pageContext TagAttribute
	 * @param file Pfad der Datei
	 * @return Inhalt der Datei
	 */
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
