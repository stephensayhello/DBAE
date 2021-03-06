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

/**
 * Servlet implementation class Produkt�bersichtServlet
 * 
 * @author Benjamin Gajewski Dieses Servlet liefert dem Admin eine �bersicht
 *         �ber alle verf�gbaren Produkte.
 */
@WebServlet("/ProduktUebersichtServlet")
public class ProduktUebersichtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProduktUebersichtServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * L�d Produkte dem entsprechend &uumlbergebenen Parameter aus der Db und
	 * &uumlbergibt diese der Session.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String rolle = (String) session.getAttribute("rolle");
		List<String> messages = new ArrayList<>();

		String pruefe = request.getParameter("pruefe");
		String auswahl = request.getParameter("auswahl");

		if (pruefe.contains("id")) {
			

			int produkt_id = 0;
			try {
				produkt_id = Integer.parseInt(auswahl);
			} catch (NumberFormatException nfe) {

			}
			Produkt produkt = ProduktOperations.produktausdbholen(produkt_id);
			session.removeAttribute("produkt");
			session.setAttribute("produkt", produkt);
			session.setAttribute("auswahl", "produktgruppe");
			request.getRequestDispatcher("produkt_bearbeiten.jsp").forward(request, response);

		} else if (pruefe.contains("artikelnr")) {

			int artikelnr = 0;
			try {
				artikelnr = Integer.parseInt(auswahl);
			} catch (NumberFormatException nfe) {

			}

			Produkt produkt = ProduktOperations.ladeProduktausdb(artikelnr);
			session.removeAttribute("produkt");
			session.setAttribute("produkt", produkt);
			session.setAttribute("auswahl", "artikel");
			request.getRequestDispatcher("produktgruppe_bearbeiten.jsp").forward(request, response);

		}

	}

	/**L�dt Produkte aus der Db, um diese auf der produktuebersicht.jsp anzuzeigen.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String rolle = (String) session.getAttribute("rolle");
		List<String> messages = new ArrayList<>();
		request.setAttribute("messages", messages);
		

		request.setAttribute("klick", "klick");
		session.removeAttribute("messages");
		List<Produkt> produkte = ProduktOperations.ladeProdukteAusDatenbank();
		List<Produkt> produkteSortiertnachartnr = new ArrayList<>();

		int counter = 0;
		for (int i = 0; i < produkte.size(); i++) {
			if (!(counter == produkte.get(i).getArtikelnr())) {
				counter = produkte.get(i).getArtikelnr();
				produkteSortiertnachartnr.add(produkte.get(i));
			}
		}
		session.removeAttribute("produkte");

		session.setAttribute("produkte", produkte);
		request.setAttribute("produktesortiert", produkteSortiertnachartnr);

		request.getRequestDispatcher("produktinfos.jsp").forward(request, response);

	}

}
