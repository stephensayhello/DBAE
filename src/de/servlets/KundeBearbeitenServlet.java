package de.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.classes.Kunde;
import de.databaseOperations.KundenOperations;
import de.databaseOperations.NutzerOperations;
import de.utilities.Mail;
import de.utilities.SaltedHash;

import de.utilities.RandomStringGenerator;

/**
 * @author Stephen galla Dieses Servlet liefert die Logik f&uumlr die Funktionen
 *         des Admins,um einen Kunden zu l&oumlschen oder ihm ein neues
 *         zuf&aumllliges Passwort zu setzten.<br>
 *         Servlet implementation class KundeBearbeitenServlet
 */
@WebServlet("/KundeBearbeitenServlet")
public class KundeBearbeitenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KundeBearbeitenServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Bekommt aus der kundenuebersicht.jsp die kundennummer &uumlbergeben und
	 * sendet an die Kundenmail ein zuf&aumlllig generiertes Passwort.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> messages = new ArrayList<>();
		String auswahl = request.getParameter("auswahlkunde");
		int kundenauswahl = Integer.parseInt(auswahl);
		Kunde kunde = KundenOperations.kundeausdbholen(NutzerOperations.nutzerAusDbHolen(kundenauswahl));
		String newRandomPw = RandomStringGenerator.generateString();
		String newPwhashed;
		try {
			newPwhashed = SaltedHash.getSaltedHash(newRandomPw);
			NutzerOperations.setzeNeuesPasswort(newPwhashed, kunde.getNutzer_id());
			System.out.println(kunde);
			Mail.SendMailTLS(kunde.getEmail(), "Neues Passwort", newRandomPw);
			messages.add("Das passwort des Accounts: " + kunde.getEmail()
					+ "  wurde geaendert und der Nutzer wurde benachrichtigt.");
			request.setAttribute("messages", messages);
		} catch (Exception e) {

			e.printStackTrace();
		}

		request.getRequestDispatcher("kundenuebersicht.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Diese Methode wird nicht genutzt :)

	}

}
