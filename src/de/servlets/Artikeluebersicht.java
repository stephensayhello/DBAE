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
import de.classes.Produktgruppe;
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
		List<Produkt> produkteSortiertnachartnr = new ArrayList<>();
		int counter = 0;
		for (int i = 0; i < produkte.size(); i++) {
			if (counter == produkte.get(i).getArtikelnr()){
				
			}else {
				counter = produkte.get(i).getArtikelnr();
				produkteSortiertnachartnr.add(produkte.get(i));
			}
		}
		
		
		
		
		
		
		
		request.setAttribute("test", "sdjfifjisdfjnsifsnfisdfnishn");
		request.setAttribute("produkte", produkteSortiertnachartnr);
		request.getRequestDispatcher("artikeluebersicht.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		session = request.getSession();
		
		//List<Produkt> produkte = (List<Produkt>) session.getAttribute("produkte");
		
		Warenkorb warenkorb = (Warenkorb) session.getAttribute("warenkorb");
		
		if(warenkorb == null) {
			Kunde kunde = (Kunde) session.getAttribute("kundeEingeloggt");
			warenkorb = new Warenkorb(kunde);
		}
		
		int produkt_id = Integer.parseInt(request.getParameter("produkt_id"));
		int menge = Integer.parseInt(request.getParameter("menge"));
		String groesse = request.getParameter("groesse");

		Produkt produkt = ProduktOperations.produktausdbholen(produkt_id);
		
		for (int i = 1; i <= menge; i++) {
			warenkorb.getInhalt().add(produkt);
		}
		
		System.out.println("angekommen");
	}

}
