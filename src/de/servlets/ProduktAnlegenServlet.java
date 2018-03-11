package de.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("p_name");
		String beschreibung = request.getParameter("p_beschreibung");
		int kategorie = Integer.parseInt(request.getParameter("p_kategorie"));
		double preis = Double.parseDouble(request.getParameter("p_preis"));
		int menge = Integer.parseInt(request.getParameter("p_menge"));
		String groesse = request.getParameter("p_groesse");

		// PrintWriter out = response.getWriter();
		// String ausgabe = "Das Produkt wurde erfolgreich angelegt.";
		// Die Übergabe der Kathegorie funktioniert nicht.

		Produkt produkt = null;
		if (kategorie == 0) {
			request.getRequestDispatcher("produkt_anlegen.jsp").forward(request, response);
		} else if (kategorie == 1) {
			produkt = new Shirt(name, beschreibung, preis, groesse, menge);
		} else if (kategorie == 2) {
			produkt = new Hose(name, beschreibung, preis, Integer.parseInt(groesse), menge);
		} else {
			produkt = new Schuhe(name, beschreibung, preis, Integer.parseInt(groesse), menge);
		}

		ProduktOperations.anlegen(produkt);

		request.getRequestDispatcher("produkt_anlegen.jsp").forward(request, response);

	}

}
