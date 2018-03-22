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

import de.classes.Produkt;
import de.databaseOperations.ProduktUpdateOperations;
import de.logik.Regex;

/**
 * Servlet implementation class ProduktgruppenBearbeitenServlet
 */
@WebServlet("/ProduktgruppenBearbeitenServlet")
public class ProduktgruppenBearbeitenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProduktgruppenBearbeitenServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		session.removeAttribute("messages");
		double preis1 = 0;
		Produkt produkt = (Produkt) session.getAttribute("produkt");
		session.removeAttribute("produkt");
		String name = request.getParameter("name");
		String preis = request.getParameter("preis");
		String beschreibung = request.getParameter("beschreibung");
		if (produkt != null) {
			if (Regex.pruefeNurDouble(preis)) {
				preis1 = Double.parseDouble(preis);
			}
			ProduktUpdateOperations.updateProduktgruppemitArtikelnr(produkt.getArtikelnr(), name, preis1, beschreibung);

			messages.add("Alles in Butter!");

		} else {
			messages.add("fehler");
		}
		session.setAttribute("messages", messages);
		request.getRequestDispatcher("produktinfos.jsp").forward(request, response);

	}

}
