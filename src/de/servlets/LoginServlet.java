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
import de.classes.Admin;
import de.classes.Kunde;
import de.classes.Nutzer;
import de.classes.Warenkorb;
import de.databaseOperations.AdminOperations;
import de.databaseOperations.KundenOperations;
import de.databaseOperations.NutzerOperations;
import de.databaseOperations.WarenkorbOperations;
import de.utilities.SaltedHash;

/**
 * @author Paul Blanke
 * Dieses Servlet &uumlberpr&uumlft den Login des Kunden und gibt 
 * bei einer falschen Eingabe eine Fehlermeldung heraus.
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

	/**
	 * Vergleicht eingegebenes Passwort mit dem hash aus der Db.
	 * Speichert den Nutzer entsprechend seiner Rolle in der Session ein.
	 * 
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String email = request.getParameter("email");
		String password = request.getParameter("psw");
		session = request.getSession();
		Nutzer nutzer = NutzerOperations.nutzerAusDbHolen(email);
		
		List<String> messages = new ArrayList<>();
		request.setAttribute("messages", messages);
	

		if (session.getAttribute("kundeeingeloggt") != null) {

			messages.add("Sie sind bereits eingeloggt!");
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		if (nutzer != null) {
			if (!SaltedHash.isPwdEqual(password, nutzer.getPasswort())) {
				
				String fehler = "Nutzername oder Passwort nicht vorhanden!";
				messages.add(fehler);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else if (NutzerOperations.nutzeristKunde(nutzer.getNutzer_id())) {

				Kunde kunde = KundenOperations.kundeausdbholen(nutzer);

				Warenkorb warenkorb = (Warenkorb) WarenkorbOperations.ladeWarenkorbAusDB(kunde);

				if (warenkorb != null) {
					session.setAttribute("warenkorb", warenkorb);
					
					WarenkorbOperations.entferneWarenkorb(warenkorb.getWarenkorb_id());
				}

				session.setAttribute("kundeeingeloggt", kunde);
				session.setAttribute("rolle", "kunde");
				session.setAttribute("kundenadresse", kunde.getAdresse());
				messages.add("Erfolgreicher login");
				
				request.getRequestDispatcher("artikeluebersicht.jsp").forward(request, response);
			}
			else if (NutzerOperations.nutzeristAdmin(nutzer.getNutzer_id())) {
				Admin admin = AdminOperations.holeAdminausDB(nutzer);
				System.out.println("Admin erkannt:)");
				session.setAttribute("rolle", "admin");
				session.setAttribute("admineingeloggt", admin);
				messages.add("Erfolgreicher login");
				request.getRequestDispatcher("produktinfos.jsp").forward(request, response);

			}

		} else {

			session.setAttribute("rolle", "kunde");
			String falschernutzername = "Nutzername oder Passwort nicht vorhanden!";
			messages.add(falschernutzername);
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}

	}

}
