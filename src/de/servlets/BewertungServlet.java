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

import de.classes.Bewertung;
import de.classes.Kunde;
import de.databaseOperations.BestellungOperations;
import de.databaseOperations.BewertungsOperations;
import de.databaseOperations.ProduktUpdateOperations;

/**
 * @author Paul Blanke Diese Servlet ermöglicht die Bewertung der Produkte auf
 *         den entsprechenden Seiten. Servlet implementation class
 *         BewertungServlet
 */
@WebServlet("/BewertungServlet")
public class BewertungServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BewertungServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// To Post
		// Wird nicht genutzt.
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<>();
		request.setAttribute("messages", messages);
		if (session.getAttribute("rolle")==null) {
			messages.add("Wenn Sie ein Produkt bewerten wollen, loggen Sie sich bitte ein.");
            System.out.println("bewertunsservlet");
            
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			int punkte = Integer.parseInt(request.getParameter("punkte"));
			int artikelNr = Integer.parseInt(request.getParameter("artnr"));
			String kommentar = request.getParameter("bewertung");
			Kunde kunde = (Kunde) session.getAttribute("kundeeingeloggt");

		

			Bewertung bewertung = new Bewertung(BewertungsOperations.hoechsteBewertungsID(), artikelNr,
					kunde.getNutzer_id(), punkte, kommentar);

			BewertungsOperations.bewertungAnlegen(bewertung);

			request.getRequestDispatcher("artikeluebersicht.jsp").forward(request, response);
		}

	}
}
