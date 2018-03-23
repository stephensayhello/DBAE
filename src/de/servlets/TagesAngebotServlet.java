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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("klick", "show");
		int id = ProduktUpdateOperations.zufallsID();
		Produkt produkt = ProduktOperations.produktausdbholen(id);
		session.setAttribute("produkt", produkt);
		List<Bewertung> bewertungen = BewertungsOperations.sucheAlleBwertungBestProdukt(id);
		if(bewertungen == null) {
			// Behandele diesen fall später
		}
		session.setAttribute("bewertungen", bewertungen);
		int durchschnitt = BewertsDurchschnittOperations.ermitteleneDurchschnitt(id);
		session.setAttribute("durchschnitt", durchschnitt);
		
		
		
		request.getRequestDispatcher("tagesangebot.jsp").forward(request, response);
	}

}
