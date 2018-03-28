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
import de.classes.Kunde;
import de.databaseOperations.KundenOperations;
import de.databaseOperations.NutzerOperations;

/**
 * Dieses Servlet bietet die M&oeglichkeit die Kundendaten zu ver&anendern.
 * Servlet implementation class KundenOperationsServlet
 * 
 * @author Stephen Galla
 */
@WebServlet("/KundenOperationsServlet")
public class KundenOperationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KundenOperationsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gibt alle Kunden aus der Db an die kundenuebersicht.jsp weiter. Setzt ein
	 * Attribut in der request, damit die .jsp nicht ein weiteres Mal geladen
	 * wird.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<>();
		session.setAttribute("messages", messages);

		request.setAttribute("klick", "show");
		List<Kunde> kunden = KundenOperations.holeAlleKunden();
		session.setAttribute("kunden", kunden);
		request.getRequestDispatcher("kundenuebersicht.jsp").forward(request, response);

	}

	/**
	 * Bekommt aus der kundenuebersicht.jsp eine id &uumlbergeben und l&oumlscht
	 * einen Kunden aus der Db.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String rolle = (String) session.getAttribute("rolle");
		List<String> messages = new ArrayList<>();
		request.setAttribute("messages", messages);

		if (rolle.contains("admin")) {
			String auswahl = request.getParameter("auswahl");
			int id = Integer.parseInt(auswahl);
			Kunde kunde = KundenOperations.kundeausdbholen(NutzerOperations.nutzerAusDbHolen(id));
			KundenOperations.entferneKunde(kunde);
			messages.add("Kunde:" + kunde.getNutzer_id() + "wurde entfernt!");

			request.getRequestDispatcher("kundenuebersicht.jsp").forward(request, response);

		} else {
			messages.add("Fehler passiert");
			request.getRequestDispatcher("kundenuebersicht.jsp").forward(request, response);
		}

	}

}
