package de.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
import de.utilities.CreatePDF;

/**
 * Servlet implementation class WarenkorbServlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("warenkorbinhalt");
		session.removeAttribute("warenkorb");
		session.removeAttribute("warenkorbgesamtpreis");
		session.removeAttribute("warenversanddauer");

		request.getRequestDispatcher("warenkorb.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Kunde kunde = (Kunde) session.getAttribute("kundeeingeloggt");
		if ((Warenkorb) session.getAttribute("warenkorb") != null && kunde != null) {
			Warenkorb warenkorb = (Warenkorb) session.getAttribute("warenkorb");
			List<Produkt> warenkorbinhalt = (List<Produkt>) session.getAttribute("warenkorbinhalt");
				
			Bestellung bestellung = new Bestellung(warenkorbinhalt, kunde);
			CreatePDF.create(bestellung);
			BestellungOperations.bestellunganlegen(bestellung);
			session.removeAttribute("warenkorbinhalt");
			session.removeAttribute("warenkorb");
			session.removeAttribute("warenkorbgesamtpreis");
			session.removeAttribute("warenversanddauer");
		} else if ((Warenkorb) session.getAttribute("warenkorb") == null) {
			System.out.println("Der Warenkorb ist leer oder loggen sie sich ein");
		}

		request.getRequestDispatcher("warenkorb.jsp").forward(request, response);

	}

}
