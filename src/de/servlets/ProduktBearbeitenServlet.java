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
 * @author Benjamin Gajewski Dieses Servlet bietet die M�glichkeit and, dass der
 *         Admin die Produkte selber �ndern kann. Servlet implementation class
 *         ProduktBearbeitenServlet
 */
@WebServlet("/ProduktBearbeitenServlet")
public class ProduktBearbeitenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProduktBearbeitenServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Daten holen
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<>();
		String rolle = (String) session.getAttribute("rolle");
		request.setAttribute("messages", messages);

		// logik
		if (rolle != null) {
			if (rolle.contains("admin")) {

				Produkt produkt = (Produkt) session.getAttribute("produkt");
				ProduktUpdateOperations.entferneProdukt(produkt);
				session.removeAttribute("produkt");
				messages.add("Das Produkt wurde aus den Sortiment entfernt !");

				request.getRequestDispatcher("produktinfos.jsp").forward(request, response);
			}
		} else {
			messages.add(" Sie haben nicht die Berechtigung f�r den Zugriff auf diese Funktionen.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Daten holen
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<>();
		String rolle = (String) session.getAttribute("rolle");
		// logik
		if (rolle.contains("admin")) {

			session.removeAttribute("produkte");

			if (session.getAttribute("produkt") == null) {
				messages.add("Sie haben kein Produkt ausgew�hlt. W�hlen Sie bitte ein Produkt aus!");
				request.setAttribute("messages", messages);
				request.getRequestDispatcher("produktinfos.jsp").forward(request, response);
			} else {

				Produkt produkt = (Produkt) session.getAttribute("produkt");
				session.removeAttribute("produkt");

				String versanddauer = request.getParameter("versanddauer");
				String mengeS = request.getParameter("menge");
				String status = request.getParameter("status");

				boolean mengenfeldgeprueft = Regex.pruefeNurZahlen(mengeS);
				boolean versanddauerfeldgeprueft = Regex.pruefeNurZahlen(versanddauer);

				if (mengeS.equals("") || versanddauer.equals("")) {
					int versanddauergeprueft = -1;
					int mengegeprueft = -1;
					ProduktUpdateOperations.updateProdukt(produkt.getProdukt_id(), mengegeprueft, versanddauergeprueft,
							status);
					messages.add("Die Daten wurden erfolgreich aktualisiert.");
				} else if (mengenfeldgeprueft && versanddauerfeldgeprueft) {

					int versanddauergeprueft = Integer.parseInt(versanddauer);
					int mengegeprueft = Integer.parseInt(mengeS);
					ProduktUpdateOperations.updateProdukt(produkt.getProdukt_id(), mengegeprueft, versanddauergeprueft,
							status);
					messages.add("Die Daten wurden erfolgreich aktualisiert.");

				} else {
					messages.add("Keine Zahlen eingegeben!");
				}
				request.setAttribute("messages", messages);
				request.getRequestDispatcher("produktinfos.jsp").forward(request, response);

			}
		} else {
			messages.add(" Sie haben nicht die Berechtigung f�r den Zugriff auf diese Funktionen.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
