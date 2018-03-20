package de.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.classes.Hose;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.databaseOperations.ProduktOperations;

import de.datenbank.DBConnection;
import de.databaseOperations.*;

/**
 * Servlet implementation class ProduktAnlegenServlet
 */
@WebServlet("/ProduktAnlegenServlet")
public class ProduktAnlegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProduktAnlegenServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("im servlet angekommen");
		String name = request.getParameter("p_name");
		String beschreibung = request.getParameter("p_beschreibung");
		int kategorie = Integer.parseInt(request.getParameter("p_kategorie"));

		double preis = Double.parseDouble(request.getParameter("p_preis"));

		String[] groessearray = request.getParameterValues("checkGroesse");
		String[] mengearray = request.getParameterValues("inputMenge");
		String[] versanddauerarray = request.getParameterValues("p_versanddauer");
System.out.println("a");
		int artikelnr = ProduktOperations.hoechsteartikelnr();
		Produkt produkt = null;
System.out.println(artikelnr);
		for (int i = 0; i < groessearray.length; i++) {

			if (!mengearray[i].isEmpty() && !versanddauerarray[i].isEmpty()) {
				String groesse = groessearray[i];
				int menge = Integer.parseInt(mengearray[i]);
				int versanddauer = Integer.parseInt(versanddauerarray[i]);

				if (kategorie == 1) {
					produkt = new Shirt(ProduktOperations.hoechsteID(), name, beschreibung, preis, groesse, menge,
							artikelnr, versanddauer,"Lieferbar");
					System.out.println("shirt");
					ProduktOperations.anlegen(produkt);
				} else if (kategorie == 2) {
					produkt = new Hose(ProduktOperations.hoechsteID(), name, beschreibung, preis, Integer.parseInt(groesse),
							menge, artikelnr, versanddauer,"Lieferbar");
					ProduktOperations.anlegen(produkt);
				} else if (kategorie == 3) {
					produkt = new Schuhe(ProduktOperations.hoechsteID(), name, beschreibung, preis,
							Integer.parseInt(groesse), menge, artikelnr, versanddauer,"Lieferbar");
					ProduktOperations.anlegen(produkt);

				}
			}
System.out.println("bla");
		
		}

		request.getRequestDispatcher("produkt_anlegen.jsp").forward(request, response);

	}

}
