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
 * Servlet implementation class BewertungServlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// To Post
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Daten beschaffen
		String punkteRe = request.getParameter("punkte");
		String artikelNr = request.getParameter("artikelnr");
		int artikelnr = 0;
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<>();
		session.setAttribute("messages", messages);
		Kunde kunde = (Kunde) session.getAttribute("kundeeingeloggt");
		
		// Prüfungen
		int punkte = 1;
		String kommentar = request.getParameter("bewertung");
		if(!punkteRe.contains("") && !artikelNr.contains("")) {
			try {
				punkte = Integer.parseInt(punkteRe);
				artikelnr = Integer.parseInt(artikelNr);
			} catch(NumberFormatException nfe) {
				System.out.println(nfe.toString());
			}
			
		int produkt_id = ProduktUpdateOperations.sucheProduktIDanhandArtikelNr(artikelnr);	
		
		if(BestellungOperations.pruefeBestellungAufProdukt(kunde, produkt_id)) {
			
			if(kunde == null) {
				messages.add("Nur eingeloggte Kunden können bewerten !");
				request.getRequestDispatcher("artikeluebersicht.jsp").forward(request, response);
				
			} else if(!BewertungsOperations.prufeaufVorhandeneBestellung(kunde.getNutzer_id(), produkt_id)) {
				messages.add("Bewertungen dürfen pro Produkt nur einmal gemacht werden!");
				request.getRequestDispatcher("artikeluebersicht.jsp").forward(request, response);
			} else {
				Bewertung bewertung = new Bewertung(punkte, kunde.getNutzer_id(), produkt_id, kommentar );
				BewertungsOperations.neueBewertung(bewertung);
				messages.add("Ihre Bewertung wurde eingepflegt!");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
			
		} else if(!BestellungOperations.pruefeBestellungAufProdukt(kunde, produkt_id)) {
			messages.add("Sie müssen das Produkt erwerben, um es zu bewerten!");
			request.getRequestDispatcher("artikeluebersicht.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
		
		
		}
	}
}
