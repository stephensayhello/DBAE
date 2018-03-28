package de.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.classes.Bewertung;
import de.databaseOperations.BewertungsOperations;

/**
 * Servlet implementation class BewertungsuebersichtServlet
 * 
 * @author Paul Blanke
 */
@WebServlet("/BewertungsuebersichtServlet")
public class BewertungsuebersichtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BewertungsuebersichtServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) Diese Methode wird nicht genutzt.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * Wird aufgerufen, wenn die Bewertungen zu einem entsprechenden Artikel
	 * eingesehen werden sollen.
	 * Gibt den Artikelnamen sowie alle Bewertungen zu dem Artikel zur&uumlck.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int artikelnr = Integer.parseInt(request.getParameter("artnr"));
		List<Bewertung> bewertungen = BewertungsOperations.ladeBewertungen(artikelnr);
		if (!bewertungen.isEmpty()) {
			request.setAttribute("artikelname", bewertungen.get(0).getProdukt().getName());
		}

		request.setAttribute("bewertungen", bewertungen);
		request.getRequestDispatcher("bewertungsuebersicht.jsp").forward(request, response);

	}

}
