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

import de.classes.Bestellung;
import de.classes.Kunde;
import de.databaseOperations.BestellungOperations;
import de.utilities.SaltedHash;

/**
 * Servlet implementation class ProfilServlet
 * Dieses Servlet �berpr�ft den Login bevor der Kunde seine Daten einsehen kann.
 * @author Stephen Galla
 */
@WebServlet("/ProfilServlet")
public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Methode leer
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("fruehereBestellungen");
		String password = request.getParameter("psw");
		Kunde kunde = (Kunde) session.getAttribute("kundeeingeloggt");
		List<String> messages = new ArrayList<>();
		List <Bestellung> bestellungen = BestellungOperations.bestellungausdbholen(kunde);
		
		session.setAttribute("fruehereBestellungen", bestellungen);
		request.setAttribute("messages", messages);
		
		if (kunde == null) {
			String bitteinloggen = "Bitte loggen Sie sich ein, um ihr Profil sehen zu k�nnen.";
			messages.add(bitteinloggen);
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}
		if (SaltedHash.isPwdEqual(password, kunde.getPasswort())) {

			session.setAttribute("kundeeingeloggt", kunde);
			request.getRequestDispatcher("profilinfos.jsp").forward(request, response);
		} else {
			String fehlermeldung = "Das eingegebene Passwort ist  falsch!";
			messages.add(fehlermeldung);
			request.getRequestDispatcher("profil.jsp").forward(request, response);
		}
	}
}
