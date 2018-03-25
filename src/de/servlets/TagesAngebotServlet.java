package de.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.classes.Bewertung;
import de.classes.Produkt;
import de.databaseOperations.BewertsDurchschnittOperations;
import de.databaseOperations.BewertungsOperations;
import de.databaseOperations.ProduktOperations;
import de.databaseOperations.ProduktUpdateOperations;

/**
 * Servlet implementation class TagesAngebotServlet
 * Dieses Servlet leitet Daten aus der DB an die oberfläche durch.
 * Daten: einzeles Produkt und Bewertung
 * @author Paul Blanke
 */
@WebServlet("/TagesAngebotServlet")
public class TagesAngebotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TagesAngebotServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// wird nicht verwendet.
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Daten holen und Attribute setzen
		HttpSession session = request.getSession();
		session.setAttribute("klick", " ");
		int id = 1;
		Produkt produkt = ProduktOperations.produktausdbholen(id);
		session.setAttribute("produkt", produkt);
		List<Bewertung> bewertungen = BewertungsOperations.sucheAlleBwertungBestProdukt(id);
		session.setAttribute("bewertungen", bewertungen);
		int durchschnitt = BewertsDurchschnittOperations.ermitteleneDurchschnitt(id);
		session.setAttribute("durchschnitt", durchschnitt);
		request.getRequestDispatcher("tagesangebot.jsp").forward(request, response);
		
		
	}

}
