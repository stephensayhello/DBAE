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

import de.classes.Produkte;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}	
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("p_name");
		String beschreibung = request.getParameter("p_bezeichnung");
		
		String s_preis= request.getParameter("p_preis");
		 double preis = Double.parseDouble(s_preis);
		
		int menge =  Integer.parseInt(request.getParameter("p_menge"));
		String art =  request.getParameter("p_art");
		int groesse = Integer.parseInt( request.getParameter("p_groesse"));
		PrintWriter out = response.getWriter();
		String ausgabe = "Das Produkt wurde erfolgreich angelegt.";
		// Die �bergabe der Kathegorie funktioniert nicht.
		Produkte produkt = new Produkte(art, name, beschreibung, 19.90, groesse, menge );
		produkt.setKathegorie("Mimi");
		ProduktOperations.anlegen(produkt);
		
		request.getRequestDispatcher("produkt_anlegen.jsp").include(request, response);
		
		
	}

}