package de.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.classes.Bestellung;
import de.classes.Kunde;
import de.classes.Produkt;
import de.classes.Warenkorb;
import de.databaseOperations.BestellungOperations;
import de.utilities.Mail;

/**
 * Dieses Servlet liefert die Logik f�r den Warenkorb.
 * 
 * @author Benjamin Gajewski Servlet implementation class WarenkorbServlet
 */
@WebServlet("/WarenkorbServlet")
public class WarenkorbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WarenkorbServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Hier wird der Warenkorb geleert.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		session.removeAttribute("warenkorb");

		session.removeAttribute("warenversanddauer");

		request.getRequestDispatcher("warenkorb.jsp").forward(request, response);
	}

	/**Bestellfunktion
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Kunde kunde = (Kunde) session.getAttribute("kundeeingeloggt");
		if ((Warenkorb) session.getAttribute("warenkorb") != null && kunde != null) {
			Warenkorb warenkorb = (Warenkorb) session.getAttribute("warenkorb");
			List<Produkt> warenkorbinhalt = warenkorb.getInhalt();

			Bestellung bestellung = new Bestellung(warenkorbinhalt, kunde);
			BestellungOperations.bestellunganlegen(bestellung);

			Mail.SendMailTLS(kunde.getEmail(), "Ihre Bestellung", "Vielen Dank f�r ihre Bestellung!");

			session.removeAttribute("warenkorb");

			session.removeAttribute("warenversanddauer");
		} 

		request.getRequestDispatcher("warenkorb.jsp").forward(request, response);

	}

}
