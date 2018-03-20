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
			if (!(counter == produkte.get(i).getArtikelnr())) {
				counter = produkte.get(i).getArtikelnr();
				produkteSortiertnachartnr.add(produkte.get(i));
			}
		}

		request.setAttribute("test", "ttttttttt");
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

		Warenkorb warenkorb = (Warenkorb) session.getAttribute("warenkorb");
		boolean ingroesseverfuegbar = false;

		if (warenkorb == null) {
			Kunde kunde = (Kunde) session.getAttribute("kundeeingeloggt");
			warenkorb = new Warenkorb(kunde);

		}

		int artnr = Integer.parseInt(request.getParameter("artnr"));
		int menge = Integer.parseInt(request.getParameter("menge"));

		String groesse = request.getParameter("groesse");

		// Produkt-ID f�r Artikel im Modal
		Produkt modalProdukt = null;
		for (Produkt produkt : produkte) {
			if (produkt instanceof Schuhe) {
				if (produkt.getArtikelnr() == artnr && ((Schuhe) produkt).getGroesse() == Integer.parseInt(groesse)) {
					modalProdukt = produkt;
				}
			} else if (produkt instanceof Hose) {
				if (produkt.getArtikelnr() == artnr && ((Hose) produkt).getGroesse() == Integer.parseInt(groesse)) {
					modalProdukt = produkt;
				}
			} else if (produkt instanceof Shirt) {
				if (produkt.getArtikelnr() == artnr && ((Shirt) produkt).getGroesse() == groesse) {
					modalProdukt = produkt;
				}
			}
		}

		if (modalProdukt == null) {
			ingroesseverfuegbar = false;
		} else {
			modalProdukt.setAnzahl(menge);
			boolean vorhanden = false;
			for (Produkt produkt : warenkorb.getInhalt()) {
				if (produkt.getProdukt_id() == modalProdukt.getProdukt_id()) {
					vorhanden = true;
					int neueAnzahl = produkt.getAnzahl() + modalProdukt.getAnzahl();
					if (modalProdukt.getStatus().contains("Lieferbar")
							&& modalProdukt.getMenge() >= neueAnzahl) {
						produkt.setAnzahl(neueAnzahl);
						modalProdukt = produkt;
						break;
					} else {
						messages.add("Produkt in dieser Menge nicht verf�gbar!");
						request.setAttribute("messages", messages);
					}
				}
			}

			modalProdukt.setAnzahl(modalProdukt.getAnzahl());
			modalProdukt.setPreismitanzahlineuro(modalProdukt.getPreis() * modalProdukt.getAnzahl());

			if (modalProdukt.getStatus().contains("Lieferbar") && modalProdukt.getMenge() >= modalProdukt.getAnzahl()) {
				if (!vorhanden) {
					warenkorb.getInhalt().add(modalProdukt);
				}
			} else {
				messages.add("Produkt in dieser Menge nicht verf�gbar!");
				request.setAttribute("messages", messages);
			}

		}

		session.setAttribute("warenkorb", warenkorb);
		session.setAttribute("warenkorbinhalt", warenkorb.getInhalt());
		session.removeAttribute("warenkorbgesamtpreis");
		session.removeAttribute("warenversanddauer");
		session.setAttribute("warenversanddauer", warenkorb.gethoechsteVersanddauer());
		session.setAttribute("warenkorbgesamtpreis",
				NumberFormat.getCurrencyInstance(Locale.GERMANY).format(warenkorb.getGesamtpreis()));
		System.out.println("angekommen");
		System.out.println(NumberFormat.getCurrencyInstance(Locale.GERMANY).format(warenkorb.getGesamtpreis()));
		System.out.println(warenkorb.getInhalt().size());
		request.getRequestDispatcher("warenkorb.jsp").forward(request, response);
	}

}
