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
 * Dieses Servlet liefert Daten aus der DB f&uumlr die artikeluebersicht.jsp
 * dazu.
 * 
 * @author Stephen Galla Servlet implementation class Artikeluebersicht
 */
@WebServlet("/Artikeluebersicht")
public class Artikeluebersicht extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;

	/**
	 * Die Get Methode holt alle Daten aus der DB heraus und bringt die
	 * entsprechenden Artikel(ein Produktobjekt stellvertretend f&uumlr den
	 * Artikel) in die &Uumlbersicht. Sortiert die Produkte in die entsprechende
	 * Liste, je nach gew&aumlhlten Kategorien in der Checkbox.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("produktlistedb");
		List<Produkt> produkte = ProduktOperations.ladeProdukteAusDatenbank();
		session.setAttribute("produktlistedb", produkte);
		List<Produkt> produkteSortiertnachartnr = new ArrayList<>();

		String[] checkboxkategorien = request.getParameterValues("Produkt");

		List<Produkt> produktnachsortiment = new ArrayList<>();

		int counter = 0;
		for (int i = 0; i < produkte.size(); i++) {
			if (!(counter == produkte.get(i).getArtikelnr())) {
				counter = produkte.get(i).getArtikelnr();
				produkteSortiertnachartnr.add(produkte.get(i));
			}
		}
		if (checkboxkategorien != null) {
			for (String string : checkboxkategorien) {
				if (Integer.parseInt(string) == 1) {
					for (Produkt produkt : produkteSortiertnachartnr) {
						if (produkt instanceof Schuhe) {
							produktnachsortiment.add(produkt);

						}
					}
				}

				else if (Integer.parseInt(string) == 2) {
					for (Produkt produkt : produkteSortiertnachartnr) {
						if (produkt instanceof Hose) {
							produktnachsortiment.add(produkt);
						}
					}
				} else if (Integer.parseInt(string) == 3) {
					for (Produkt produkt : produkteSortiertnachartnr) {
						if (produkt instanceof Shirt) {
							produktnachsortiment.add(produkt);
						}
					}
				}
			}
			produkteSortiertnachartnr = produktnachsortiment;
		}

		request.setAttribute("test", "ttttttttt");
		request.setAttribute("produktesortiertnachartnr", produkteSortiertnachartnr);
		request.getRequestDispatcher("artikeluebersicht.jsp").forward(request, response);

	}

	/**
	 * Die Post-Methode wird aufgerufen, wenn ein Produkt in den Warenkorb
	 * gelegt wird. Es wird gepr�ft ob ein Produkt mit den entsprechenden
	 * Eigenschaften in der Produktliste vorhanden ist. Der Warenkorb mit dem
	 * Produkt in der entsprechenden Gr&oumlsse wird an die Session
	 * angef&uumlgt.
	 * 
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		session = request.getSession();
		List<String> messages = new ArrayList<>();
		List<Produkt> produkte = (List<Produkt>) session.getAttribute("produktlistedb");

		Warenkorb warenkorb = (Warenkorb) session.getAttribute("warenkorb");
		if (warenkorb == null) {
			Kunde kunde = (Kunde) session.getAttribute("kundeeingeloggt");
			warenkorb = new Warenkorb(kunde);

		}

		int artnr = Integer.parseInt(request.getParameter("artnr"));
		int menge = Integer.parseInt(request.getParameter("menge"));

		String groesse = request.getParameter("groesse");

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

				if (produkt.getArtikelnr() == artnr && ((Shirt) produkt).getGroesse().equals(groesse)) {
					modalProdukt = produkt;
				}
			}
		}

		if (modalProdukt == null) {
			messages.add("Zur Zeit leider nicht lieferbar!");
			request.setAttribute("messages", messages);
		} else {
			modalProdukt.setAnzahl(menge);
			boolean vorhanden = false;
			for (Produkt produkt : warenkorb.getInhalt()) {
				if (produkt.getProdukt_id() == modalProdukt.getProdukt_id()) {
					vorhanden = true;
					int neueAnzahl = produkt.getAnzahl() + modalProdukt.getAnzahl();
					if (modalProdukt.getStatus().contains("Lieferbar") && modalProdukt.getMenge() >= neueAnzahl) {
						produkt.setAnzahl(neueAnzahl);
						modalProdukt = produkt;
						break;
					} else {

					}
				}
			}

			modalProdukt.setAnzahl(modalProdukt.getAnzahl());
			modalProdukt.setPreismitanzahlineuro(modalProdukt.getPreis() * modalProdukt.getAnzahl());
			if (modalProdukt.getStatus().equals("Lieferbar") && modalProdukt.getMenge() >= modalProdukt.getAnzahl()) {
				if (!vorhanden) {
					warenkorb.getInhalt().add(modalProdukt);
				}
			} else {
				messages.add("Zur Zeit nicht lieferbar!");

			}

		}
		if (messages.size() != 0) {
			request.setAttribute("messages", messages);
		}
		session.setAttribute("warenkorb", warenkorb);

		session.removeAttribute("warenversanddauer");
		session.setAttribute("warenversanddauer", warenkorb.gethoechsteVersanddauer());

		request.getRequestDispatcher("warenkorb.jsp").forward(request, response);
	}

}
