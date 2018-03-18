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

		int menge = 0;
		String name = request.getParameter("p_name");
		String beschreibung = request.getParameter("p_beschreibung");
		int kategorie = Integer.parseInt(request.getParameter("p_kategorie"));
		int versanddauer = Integer.parseInt(request.getParameter("p_versanddauer"));
		double preis = Double.parseDouble(request.getParameter("p_preis"));

		String[] groessearray = request.getParameterValues("checkGroesse");
		String[] mengearray = request.getParameterValues("inputMenge");
		new ArrayList<String>(Arrays.asList(groessearray));
		new ArrayList<String>(Arrays.asList(mengearray));
		System.out.println(groessearray[0]);
		System.out.println(mengearray[0]);
		System.out.println(mengearray.length);
		System.out.println(groessearray.length);
        int artikelnr = ProduktOperations.hoechsteartikelnr(); 
		Produkt produkt = null;

		for (int i = 0; i < groessearray.length; i++) {
			if (!mengearray[i].isEmpty()) {
				String groesse = groessearray[i];
				menge = Integer.parseInt(mengearray[i]);
				System.out.println("test");
				if (kategorie == 0) {
					request.getRequestDispatcher("produkt_anlegen.jsp").forward(request, response);
				} else if (kategorie == 1) {
					produkt = new Shirt(ProduktOperations.hoechsteID(), name, beschreibung, preis, groesse, menge,
						artikelnr, versanddauer	);
					ProduktOperations.anlegen(produkt);

				} else if (kategorie == 2) {
System.out.println("hose");
					produkt = new Hose(ProduktOperations.hoechsteID(), name, beschreibung, preis,
							Integer.parseInt(groesse), menge, artikelnr,versanddauer);
					ProduktOperations.anlegen(produkt);
				} else {
					produkt = new Schuhe(ProduktOperations.hoechsteID(), name, beschreibung, preis,
							Integer.parseInt(groesse), menge, artikelnr, versanddauer);
					ProduktOperations.anlegen(produkt);

				}
			}
		}

		request.getRequestDispatcher("produkt_anlegen.jsp").forward(request, response);

	}

}
