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

import de.classes.Hose;
import de.classes.Kunde;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.classes.Warenkorb;
import de.databaseOperations.ProduktOperations;

/**
 * Servlet implementation class Artikeluebersicht
 */
@WebServlet("/Artikeluebersicht")
public class Artikeluebersicht extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Artikeluebersicht() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Produkt> produkte = ProduktOperations.ladeProdukteAusDatenbank();
		request.setAttribute("test", "sdjfifjisdfjnsifsnfisdfnishn");
		request.setAttribute("produkte", produkte);
		request.getRequestDispatcher("artikeluebersicht.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Kunde kunde = (Kunde) session.getAttribute("kundeeingeloggt");
		List<Produkt> produkte = (List<Produkt>) session.getAttribute("produkte");

		int produkt_id = Integer.parseInt(request.getParameter("produkt_id"));
		int menge = Integer.parseInt(request.getParameter("menge"));
		String groesse = request.getParameter("groesse");

		Produkt produkt = ProduktOperations.produktausdbholen(produkt_id);
		if (produkte.size() == 0) {
			produkte = new ArrayList<Produkt>();
		}
		for (int i = 1; i <= menge; i++) {
			if (ProduktOperations.produktistHose(produkt_id)) {
				Hose hose = new Hose(produkt.getProdukt_id(), produkt.getName(), produkt.getBeschreibung(),
						produkt.getPreis(), Integer.parseInt(groesse), produkt.getMenge());
				produkte.add(hose);
			}
			if (ProduktOperations.produktistSchuhe(produkt_id)) {
				Schuhe schuhe = new Schuhe(produkt.getProdukt_id(), produkt.getName(), produkt.getBeschreibung(),
						produkt.getPreis(), Integer.parseInt(groesse), produkt.getMenge());
				produkte.add(schuhe);
			}
			if (ProduktOperations.produktistShirt(produkt_id)) {
				Shirt shirt = new Shirt(produkt.getProdukt_id(), produkt.getName(), produkt.getBeschreibung(),
						produkt.getPreis(), groesse, produkt.getMenge());
				produkte.add(shirt);
			}
		}
		for (Produkt item : produkte) {
			System.out.println(item);
		}

		System.out.println("angekommen");
		doGet(request, response);
	}

}
