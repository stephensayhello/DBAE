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
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.databaseOperations.ProduktOperations;

/**
 * Servlet implementation class TagesAngebotServlet
 */
@WebServlet("/TagesAngebotServlet")
public class TagesAngebotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TagesAngebotServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		request.setAttribute("produkte", produkteSortiertnachartnr);
		request.getRequestDispatcher("tagesangebot.jsp").forward(request, response);

	}

		
		
		// TODO Auto-generated method stub
		
	}


