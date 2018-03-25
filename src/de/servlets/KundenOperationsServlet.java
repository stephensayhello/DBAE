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
import de.classes.Nutzer;
import de.databaseOperations.KundenOperations;
import de.databaseOperations.NutzerOperations;
import de.utilities.SaltedHash;
import de.utilities.mail;

/**
 * Dieses Servlet bietet die M&oeglichkeit die Kundendaten zu ver&anendern.
 * Servlet implementation class KundenOperationsServlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Daten holen
		HttpSession session = request.getSession();
		String rolle = (String) session.getAttribute("rolle");
		List<String> messages = new ArrayList<>();
		session.setAttribute("messages", messages);
		// prüfungen vornehmen
		if(rolle!=null){
		if(rolle.contains("admin")) {
			request.setAttribute("klick", "show");
			List<Kunde> kunden =  KundenOperations.holeAlleKunden();
			session.setAttribute("kunden", kunden);
			request.getRequestDispatcher("kundenuebersicht.jsp").forward(request, response);
		}} else {
			messages.add("Sie sind nicht berechtigt auf diese Funktionen zu zugreifen!! Bitte loggen Sie sich als Admin ein");
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// Daten holen
		HttpSession session = request.getSession();
		String rolle = (String) session.getAttribute("rolle");
		List<String> messages = new ArrayList<>();
		session.setAttribute("messages", messages);
		
		// logik
		if(rolle!=null){
		if(rolle.contains("admin")) {
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
			
		} else {
			messages.add("Sie sind nicht berechtigt auf diese Funktionen zu zugreifen!! Bitte loggen Sie sich als Admin ein");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}


}
