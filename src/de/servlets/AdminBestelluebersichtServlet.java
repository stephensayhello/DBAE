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

import de.classes.Bestellung;
import de.classes.Kunde;
import de.databaseOperations.BestellungOperations;
import de.databaseOperations.BestellungUpdateOperations;
import de.databaseOperations.KundenOperations;

/**
 * Servlet implementation class AdminBestelluebersichtServlet
 */
@WebServlet("/AdminBestelluebersichtServlet")
public class AdminBestelluebersichtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminBestelluebersichtServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("klick", "klick");
		List<Kunde> kunden = KundenOperations.holeAlleKunden();
		
		List<Bestellung> bestellungen = new ArrayList<>();
		List<Bestellung> bestellungenvonallenkunden = new ArrayList<>();
		
		for (Kunde kunde : kunden) {
			bestellungen = BestellungOperations.bestellungausdbholen(kunde);
			for (Bestellung bestellung : bestellungen) {
				bestellungenvonallenkunden.add(bestellung);
			}
		}
		
		session.setAttribute("Bestellungen", bestellungenvonallenkunden);
		for (Bestellung bestellung : bestellungen) {
			System.out.println("DIE BESTELLUNGEN");
			System.out.println(bestellung.getBearbeitungsstatus());
			System.out.println(bestellung.getBestellliste());
		}
		request.getRequestDispatcher("bestellungadmin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String bearbeitungsstatus = request.getParameter("status");
		String auswahl = request.getParameter("auswahl");
		int bstnr = Integer.parseInt(auswahl);
		
		BestellungUpdateOperations.updateBestellungBearbeitungsstatus(bstnr, bearbeitungsstatus);
		
		request.getRequestDispatcher("bestellungadmin.jsp").forward(request, response);
		
		
		
		
		
		
	}

}
