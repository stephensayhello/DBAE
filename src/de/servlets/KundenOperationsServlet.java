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
import de.databaseOperations.KundenOperations;
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
		String auswahl = request.getParameter("kunde");
		String loeschen = request.getParameter("loeschen");
		String passwort = request.getParameter("passwort");
		List<String> messages = new ArrayList<>();
		
		if(!loeschen.contains("") && passwort.contains("")) {
			Adresse adresse = new Adresse("", "", 12, "");
			Kunde kunde = new Kunde("", "", adresse, "","");
			messages.add("Der Kunde wurde entfernt");
			request.setAttribute("messages", messages);
			KundenOperations.entferneKunde(kunde);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else  if(!auswahl.contains("") && !passwort.contains("") ){
			// Unglücklich gelöst :)
			Adresse adresse = new Adresse(auswahl, auswahl, 0, auswahl);
			Kunde kunden = new Kunde( "", "", adresse, "", "");
			// SetPasswort
			KundenOperations.kundenUpdateDaten(kunden);
			// Mail an Kunde
			mail.SendMailTLS("Neues passwort", "Passwort zurück gesetzt", "");
			messages.add("Das Passwort wurde neu gesetzt");
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} else {
			messages.add("Ein technischer Fehler ist passiert");
			messages.add("Bitte wiederholen Sie Ihre Eingaben.");
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("kundeninfos.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Kunde> kunden =  KundenOperations.holeAlleKunden();
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<>();
		System.out.println("Soso");
		session.setAttribute("kunden", kunden);
		request.getRequestDispatcher("kundeninfos.jsp").forward(request, response);
		
	}

}
