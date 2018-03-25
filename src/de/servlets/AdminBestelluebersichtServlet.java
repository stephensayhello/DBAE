package de.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.classes.Bestellung;
import de.databaseOperations.BestellungOperations;

/**
 * @author 
 * Dieses Servlet liefert die Logik zur gleichnamigen JSP-Seite dazu.
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
		// methode ist ungenutzt.
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("klick", "klick");
		List<Bestellung> bestellungen = BestellungOperations.allebestellungenausgeben();
		request.setAttribute("Bestellungen", bestellungen);
		for (Bestellung bestellung : bestellungen) {
			System.out.println(bestellung.getBestellliste());
		}
		request.getRequestDispatcher("bestellungadmin.jsp").forward(request, response);
	}

}
