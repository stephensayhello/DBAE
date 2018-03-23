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
		HttpSession session = request.getSession();
		String rolle = (String) session.getAttribute("rolle");
		List<String> messages = new ArrayList<>();
		session.setAttribute("messages", messages);
		if(rolle.contains("admin")) {
			String email = request.getParameter("auswahl");
			String passwort = request.getParameter("passwort");
			
			Kunde kunde = null;
			if(!email.contains("")) {
				kunde = KundenOperations.kundeausdbholen(NutzerOperations.nutzerAusDbHolen(email));
			} 
			String auslesen = request.getParameter("auslesen");
			
			if(auslesen.contains("loeschen")) {
				String emailL = request.getParameter("loeschen");
				Nutzer nutzer = NutzerOperations.nutzerAusDbHolen(emailL);
				
				KundenOperations.entferneKunde(kunde, nutzer);
				
				messages.add("Der Kundeaccount: " + kunde.getEmail() +" wurden erfolgreich aus dem System gelöscht.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else if(auslesen.contains("passwort")) {
				Nutzer nutzer = NutzerOperations.nutzerAusDbHolen(email);
				
				try {
					nutzer.setPasswort(SaltedHash.getSaltedHash(passwort));
					NutzerOperations.nutzerDataUpdate(nutzer);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mail.SendMailTLS("","Neues Passwort!  ", passwort);
				messages.add("Das passwort des Accounts: " + kunde.getEmail() + "  wurde geändert und der Nutzer wurde benachrichtigt.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			} else {
				messages.add("Fehler passiert");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
		} else {
			messages.add("Sie sind nicht berechtigt auf diese Funktionen zu zugreifen!! Bitte loggen Sie sich als Admin ein");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String rolle = (String) session.getAttribute("rolle");
		List<String> messages = new ArrayList<>();
		session.setAttribute("messages", messages);
		if(rolle.contains("admin")) {
			request.setAttribute("klick", "show");
			List<Kunde> kunden =  KundenOperations.holeAlleKunden();
			session.setAttribute("kunden", kunden);
			request.getRequestDispatcher("kundenuebersicht.jsp").forward(request, response);
		} else {
			messages.add("Sie sind nicht berechtigt auf diese Funktionen zu zugreifen!! Bitte loggen Sie sich als Admin ein");
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
		
		
	}


}
