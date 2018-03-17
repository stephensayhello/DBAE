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

import com.sun.xml.internal.ws.api.message.Message;
import com.sun.xml.internal.ws.api.message.Messages;

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
		HttpSession session = request.getSession();
		List<Produkt> produkte = ProduktOperations.ladeProdukteAusDatenbank();
		session.setAttribute("produktlistedb", produkte);
		List<Produkt> produkteSortiertnachartnr = new ArrayList<>();
		int counter = 0;
		for (int i = 0; i < produkte.size(); i++) {
			if (counter == produkte.get(i).getArtikelnr()) {

			} else {
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
		List<String> messages = new ArrayList<>();
		List<Produkt> produkte = (List<Produkt>) session.getAttribute("produktlistedb");

		List<Produkt> produktef�rwarenkorb = new ArrayList<>();

		Warenkorb warenkorb = (Warenkorb) session.getAttribute("warenkorb");
		boolean ingroesseverf�gbar = false;

		if (warenkorb == null) {
			Kunde kunde = (Kunde) session.getAttribute("kundeEingeloggt");
			warenkorb = new Warenkorb(kunde);

		}

		int artnr = Integer.parseInt(request.getParameter("artnr"));
		int menge = Integer.parseInt(request.getParameter("menge"));

		String groesse = request.getParameter("groesse");

		for (Produkt produkt2 : produkte) {
			if (produkt2.getArtikelnr() == artnr) {

				if (produkt2 instanceof Shirt) {
					Shirt shirt = (Shirt) produkt2;
					if (shirt.getGroesse().equals(groesse)) {
						ingroesseverf�gbar=true;
						shirt.setAnzahl(menge);
						if (shirt.getStatus().contains("Lieferbar")) {
							warenkorb.getInhalt().add(shirt);
							
						} else {
							messages.add("Produkt in dieser Menge nicht verf�gbar!");
						}
					}
				}

				if (produkt2 instanceof Hose) {
					Hose hose = (Hose) produkt2;
					if (hose.getGroesse() == Integer.parseInt(groesse)) {
						ingroesseverf�gbar=true;
						hose.setAnzahl(menge);
						System.out.println(hose.getPreismitanzahlineuro());
						if (hose.getStatus().contains("Lieferbar")) {
							System.out.println("lieferbar");
							warenkorb.getInhalt().add(hose);
							
						} else {
							messages.add("Produkt in dieser Menge nicht verf�gbar!");
						}
					}
				}
			}

			if (produkt2 instanceof Schuhe) {
				Schuhe schuhe = (Schuhe) produkt2;
				if (schuhe.getGroesse() == Integer.parseInt(groesse)) {
					ingroesseverf�gbar=true;
					schuhe.setAnzahl(menge);
					if (schuhe.getStatus().contains("Lieferbar")) {
						warenkorb.getInhalt().add(schuhe);
						
					} else {
						messages.add("Produkt in dieser Menge nicht verf�gbar!");
					}
				}
			}
		}

		if (!ingroesseverf�gbar) {
			messages.add("Produkt ist in dieser Gr��e nicht lieferbar!");
			request.setAttribute("messages", messages);
		}

		session.setAttribute("warenkorb", warenkorb);
		session.setAttribute("warenkorbinhalt", warenkorb.getInhalt());
		session.removeAttribute("warenkorbgesamtpreis");
		session.setAttribute("warenkorbgesamtpreis", NumberFormat.getCurrencyInstance(Locale.GERMANY).format(warenkorb.getGesamtpreis()));
		System.out.println("angekommen");
		System.out.println(NumberFormat.getCurrencyInstance(Locale.GERMANY).format(warenkorb.getGesamtpreis()));
		System.out.println(warenkorb.getInhalt().size());
		request.getRequestDispatcher("warenkorb.jsp").forward(request, response);
	}

}
