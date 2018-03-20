package de.servlets;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("psw");
		session = request.getSession();
		Nutzer nutzer = NutzerOperations.nutzerAusDbHolen(email);
		System.out.println(nutzer);
		List<String> messages = new ArrayList<>();
		request.setAttribute("messages", messages);
<<<<<<< HEAD
		//das lï¿½sen wir noch anders
		if(session.getAttribute("kundeeingeloggt")!=null){
=======
		// das lösen wir noch anders
		if (session.getAttribute("kundeeingeloggt") != null) {
>>>>>>> 05e95e19d8a8eb2ba504b153f95b31784e6c35c1
			messages.add("Sie sind bereits eingeloggt!");
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		if (nutzer != null) {
			if (!SaltedHash.isPwdEqual(password, nutzer.getPasswort())) {
				// hier fehlt noch was
				String fehler = "passwort falsch";
				messages.add(fehler);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			if (NutzerOperations.nutzeristKunde(nutzer.getNutzer_id())) {

				Kunde kunde = KundenOperations.kundeausdbholen(nutzer);

				Warenkorb warenkorb = (Warenkorb) WarenkorbOperations.ladeWarenkorbAusDB(kunde);

				if (warenkorb != null) {
					session.setAttribute("warenkorb", warenkorb);
					session.setAttribute("warenkorbinhalt", warenkorb.getInhalt());
					session.setAttribute("warenkorbgesamtpreis",
							NumberFormat.getCurrencyInstance(Locale.GERMANY).format(warenkorb.getGesamtpreis()));
					WarenkorbOperations.entferneWarenkorb(warenkorb.getWarenkorb_id());
				}

				session.setAttribute("kundeeingeloggt", kunde);
				session.setAttribute("rolle", "kunde");
				session.setAttribute("kundenadresse", kunde.getAdresse());
				messages.add("Erfolgreicher login");
				boolean pruefeLogin = false;
				request.setAttribute("pruefeLogin", pruefeLogin);
				request.getRequestDispatcher("artikeluebersicht.jsp").forward(request, response);
			}
			if (NutzerOperations.nutzeristAdmin(nutzer.getNutzer_id())) {
				Admin admin = AdminOperations.holeAdminausDB(nutzer);
				System.out.println("Admin erkannt:)");
				session.setAttribute("rolle", "admin");
				session.setAttribute("admineingeloggt", admin);
				messages.add("Erfolgreicher login");
				request.getRequestDispatcher("produktinfos.jsp").forward(request, response);

			}

		} else {

			session.setAttribute("rolle", "kunde");
			String falschernutzername = "Name falsch";
			messages.add(falschernutzername);
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}

	}

}
