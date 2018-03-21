package de.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.classes.Produkt;
import de.databaseOperations.ProduktOperations;

/**
 * Servlet implementation class Produkt‹bersichtServlet
 */
@WebServlet("/ProduktUebersichtServlet")
public class ProduktUebersichtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduktUebersichtServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Crashing");
		String pruefe = request.getParameter("pruefe");
		String auswahl =  request.getParameter("auswahl");
		HttpSession session = request.getSession();
		
		if(pruefe.contains("produktgruppe")) {
			System.out.println("Produktgruppe");
			
			int produkt_id = 0;
			try {
				produkt_id = Integer.parseInt(auswahl);
			} catch (NumberFormatException nfe) {
				
			}
			Produkt produkt = ProduktOperations.produktausdbholen(produkt_id);
			session.setAttribute("produkt", produkt);
			session.setAttribute("auswahl", "produktgruppe" );
				request.getRequestDispatcher("produkt_bearbeiten.jsp").forward(request, response);
				
			
			
			
			
		} else if(pruefe.contains("artikelnr")) {
			
			
			int artikelnr = 0;
			try {
				artikelnr = Integer.parseInt(auswahl);
			} catch(NumberFormatException nfe) {
				
			}
			
			Produkt produkt = ProduktOperations.ladeProduktausdb(artikelnr);
			
			session.setAttribute("produkt", produkt);
			session.setAttribute("auswahl", "artikel");
			request.getRequestDispatcher("produkt_bearbeiten.jsp").forward(request, response);
	 
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("srevlet angekommen, produktuebersichtservlet");
		List<Produkt> produkte = ProduktOperations.ladeProdukteAusDatenbank();
		System.out.println("Testen");
		HttpSession session = request.getSession();
		session.setAttribute("produkte", produkte);
		
		request.getRequestDispatcher("produktinfos.jsp").forward(request, response);
		
	}

}
