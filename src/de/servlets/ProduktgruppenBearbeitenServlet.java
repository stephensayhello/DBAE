package de.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.classes.Produkt;
import de.databaseOperations.ProduktOperations;
import de.databaseOperations.ProduktUpdateOperations;
import de.logik.Regex;

/**
 * Servlet implementation class ProduktgruppenBearbeitenServlet
 * @author Benjamin Gajewski
 * Dieses Servlet bietet die Möglichkeit mehrere Artikel einer Produktgruppe gleichzeitig zu ändern.
 * 
 */
@WebServlet("/ProduktgruppenBearbeitenServlet")
public class ProduktgruppenBearbeitenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProduktgruppenBearbeitenServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Daten holen
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		List<Produkt> produkte = new ArrayList<>();
		Produkt produkt = (Produkt) session.getAttribute("produkt");
		session.removeAttribute("produkt");
		String rolle = (String) session.getAttribute("rolle");
		session.removeAttribute("messages");
		session.setAttribute("messages", messages);
		System.out.println("prodgrup");
		System.out.println(produkte);
		System.out.println(produkt);
		
		// logik
		if (rolle != null) {
			if (rolle.contains("admin")) {
				produkte = ProduktOperations.ladeProdukteAusDatenbankmitArtnr(produkt.getArtikelnr());
				for (Produkt produkt2 : produkte) {
					System.out.println(produkt);
					ProduktUpdateOperations.entferneProdukt(produkt2);
				}

				messages.add("Artikel gelöscht!");
				request.getRequestDispatcher("produktinfos.jsp").forward(request, response);

			}

		} else {
			messages.add("Bitte einloggen!");
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Daten holen
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		String rolle = (String) session.getAttribute("rolle");
		session.removeAttribute("messages");
		// logik
		if (rolle != null) {
			if (rolle.contains("admin")) {

				double preis1 = 0;
				Produkt produkt = (Produkt) session.getAttribute("produkt");
				session.removeAttribute("produkt");
				String name = request.getParameter("name");
				String preis = request.getParameter("preis");
				String beschreibung = request.getParameter("beschreibung");
				if (produkt != null) {
					if (Regex.pruefeNurDouble(preis)) {
						preis1 = Double.parseDouble(preis);
					}
					ProduktUpdateOperations.updateProduktgruppemitArtikelnr(produkt.getArtikelnr(), name, preis1,
							beschreibung);

					messages.add("Alles in Butter!");

				} else {
					messages.add("fehler");

				}
				session.removeAttribute("produkte");
				session.setAttribute("messages", messages);
				request.getRequestDispatcher("produktinfos.jsp").forward(request, response);
			}
		} else {
			messages.add(" Sie haben nicht die Berechtigung für den Zugriff auf diese Funktionen.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
