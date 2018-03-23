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

import com.sun.mail.imap.protocol.MailboxInfo;

import de.classes.Adresse;
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
		String email = request.getParameter("auswahl");
		String passwort = request.getParameter("passwort");
		HttpSession session = request.getSession();
		Kunde kunde = null;
		if(!email.contains("")) {
			kunde = KundenOperations.kundeausdbholen(NutzerOperations.nutzerAusDbHolen(email));
		} 
		String auslesen = request.getParameter("auslesen");
		List<String> messages = new ArrayList<>();
		session.setAttribute("messages", messages);
		if(auslesen.contains("loeschen")) {
			KundenOperations.entferneKunde(kunde);
			
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
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("klick", "klick");
		List<Kunde> kunden =  KundenOperations.holeAlleKunden();
		HttpSession session = request.getSession();
		session.setAttribute("kunden", kunden);
		request.getRequestDispatcher("kundenuebersicht.jsp").forward(request, response);
		
	}


}
