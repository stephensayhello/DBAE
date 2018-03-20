package de.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.databaseOperations.ProduktOperations;

/**
 * Servlet implementation class ProduktAuswahlServlet
 */
@WebServlet("/ProduktAuswahlServlet")
public class ProduktAuswahlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduktAuswahlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Auswahl Parameter.
		String auswahl = request.getParameter("auswahl");
		List<String> messages = new ArrayList<>();
		String kategorie ="";
		// Umwandlung auf DB Namen.
		switch(auswahl) {
		
			case "Schuhe":
				kategorie = "schuhe";
				break;
				
			case "T-Shirts": 
				kategorie = "shirt";
				break;
				
			case "Hosen":
				kategorie ="hose";
				
		
		}
		
		// Ausgabe
		
		String ausgabe = "Sie haben die Kategorie: " + auswahl 
				+ " ausgewählt. In dieser Kategorie haben wir ";
		
	//int anzahl = ProduktOperations.zeigeAnzahlProdukte(kategorie);
		//ausgabe +=  anzahl  + " Artikel für Sie.";
		messages.add(ausgabe);
		request.setAttribute("messages", messages);
		
		
		request.getRequestDispatcher("artikeluebersicht.jsp").forward(request, response);
	}

}
