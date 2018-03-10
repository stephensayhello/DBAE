package de.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.classes.Adresse;
import de.classes.Kunde;
import de.classes.Produkte;
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
		
		String name = (String) request.getAttribute("p_name");
		String beschreibung = (String) request.getAttribute("p_bezeichnung");
		
		String s_preis=(String) request.getAttribute("p_preis");
		double preis = Double.parseDouble(s_preis);
		
		int menge = Integer.parseInt( (String) request.getAttribute("p_menge"));
		String art = (String) request.getAttribute("p_art");
		int groesse = Integer.parseInt((String) request.getAttribute("p_groesse"));
		
		int produkt_id = 1;
		
		Produkte produkt = new Produkte(art, name, beschreibung, preis, groesse, menge, produkt_id);
		
		
	}
//
//	public static int hoechsteID() {
//		Connection con = DBConnection.getConnection();
//		int id= 0;
//		try {
//			PreparedStatement pst = con.prepareStatement("SELECT MAX(produkt_id) FROM produkt");
//			
//			  ResultSet rs  = pst.executeQuery();
//			  rs.next();
//			  id= rs.getInt(1);
//			  
//			  
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(id);
//		id++;
//		System.out.println(id);
//		return id;
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
