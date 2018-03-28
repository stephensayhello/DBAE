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
 * @author Benjamin Gajewski Dieses Servlet bietet dem Admin die Möglichkeit
 *         einzelne Produkte zu &aumlndern. Servlet implementation class
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

	/**Entfernt ein in der produkt_bearbeiten.jsp ausgesuchtes Produkt aus der Db.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<>();

		request.setAttribute("messages", messages);

		Produkt produkt = (Produkt) session.getAttribute("produkt");
		ProduktUpdateOperations.entferneProdukt(produkt);
		session.removeAttribute("produkt");
		messages.add("Das Produkt wurde aus dem Sortiment entfernt !");

		request.getRequestDispatcher("produktinfos.jsp").forward(request, response);

	}

	/**&Aumlndert ein Produkt entsprechend den aus der .jsp &uumlbergebenen Parametern in der Db.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<>();
		session.removeAttribute("produkte");

		if (session.getAttribute("produkt") == null) {
			messages.add("Sie haben kein Produkt ausgewählt. Wählen Sie bitte ein Produkt aus!");
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

	}

}
