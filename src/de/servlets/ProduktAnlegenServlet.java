package de.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import de.classes.Hose;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.databaseOperations.ProduktOperations;

import de.utilities.FileStorageInDropbox;

/**
 * Servlet implementation class ProduktAnlegenServlet
 * 
 * @author Benjamin Gajewski
 */
@WebServlet("/ProduktAnlegenServlet")
@MultipartConfig
public class ProduktAnlegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProduktAnlegenServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Läd ein Bild in die Dropbox. Legt einen neuen Artikel an und speichert
	 * diesen entsprechend der in der produkt_anlegen.jsp ausgew&aumlhlten
	 * Kriterien in die Db.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// https://stackoverflow.com/questions/2422468/how-to-upload-files-to-server-using-jsp-servlet
		Part filePart = request.getPart("uploadFile");
		String name = (filePart.getHeader("Content-Disposition")).replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1");
	
		File img = new File(name);

		// https://www.mkyong.com/java/how-to-convert-inputstream-to-file-in-java/
		InputStream stream = filePart.getInputStream();

		OutputStream outputStream = new FileOutputStream(img);

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = stream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		outputStream.close();

		FileStorageInDropbox storage = new FileStorageInDropbox();
		String path = storage.uploadImage(img);

		String p_name = request.getParameter("p_name");
		String beschreibung = request.getParameter("p_beschreibung");
		int kategorie = Integer.parseInt(request.getParameter("p_kategorie"));

		double preis = Double.parseDouble(request.getParameter("p_preis"));

		String[] groessearray = new String[10];

		groessearray = request.getParameterValues("checkGroesse");
		List<String> mengearray = new ArrayList<String>(Arrays.asList(request.getParameterValues("inputMenge")));
		List<String> versanddauerarray = new ArrayList<String>(
				Arrays.asList(request.getParameterValues("p_versanddauer")));

		mengearray.removeAll(Arrays.asList("", null));
		versanddauerarray.removeAll(Arrays.asList("", null));

		int artikelnr = ProduktOperations.hoechsteartikelnr();
		Produkt produkt = null;
		for (int i = 0; i < groessearray.length; i++) {

			if (mengearray.get(i) != null && versanddauerarray.get(i) != null) {
				String groesse = groessearray[i];
				int menge = Integer.parseInt(mengearray.get(i));
				int versanddauer = Integer.parseInt(versanddauerarray.get(i));

				if (kategorie == 1) {
					produkt = new Shirt(ProduktOperations.hoechsteID(), p_name, beschreibung, preis, groesse, menge,
							artikelnr, versanddauer, "Lieferbar");
					produkt.setImagePath(path);
					ProduktOperations.anlegen(produkt);
				} else if (kategorie == 2) {
					produkt = new Hose(ProduktOperations.hoechsteID(), p_name, beschreibung, preis,
							Integer.parseInt(groesse), menge, artikelnr, versanddauer, "Lieferbar");
					produkt.setImagePath(path);
					ProduktOperations.anlegen(produkt);
				} else if (kategorie == 3) {
					produkt = new Schuhe(ProduktOperations.hoechsteID(), p_name, beschreibung, preis,
							Integer.parseInt(groesse), menge, artikelnr, versanddauer, "Lieferbar");
					produkt.setImagePath(path);
					ProduktOperations.anlegen(produkt);

				}
			}
		}
		List<String> messages = new ArrayList<>();
		request.setAttribute("messages", messages);
		messages.add("Das produkt wurde erfolgreich eingepflegt.");
		request.getRequestDispatcher("produkt_anlegen.jsp").forward(request, response);

	}

}
