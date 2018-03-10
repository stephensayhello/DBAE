package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import de.classes.Produkte;
import de.datenbank.DBConnection;

public class ProduktOperations {
	
	public final static String anlegenProdukt = "INSERT INTO produkt VALUES (?,?,?,?,?)";
	
	public static void anlegen(Produkte produkt) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(anlegenProdukt);
			pst.setDouble(1, produkt.getPreis());
			pst.setInt(2, produkt.getMenge());
			pst.setString(3, produkt.getName());
			pst.setString(4, produkt.getBeschreibung());
			//set produkt_id
			pst.setInt(5, produkt.getProdukt_id());
			pst.setString(6, produkt.getArt());

			pst.execute();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
//	public static int hoechsteID() {
//		Connection con = DBConnection.getConnection();
//		int id= 0;
//		try {
//			PreparedStatement pst = con.prepareStatement("SELECT MAX(produkt_id) FROM produkte");
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

	public ProduktOperations() {
		// TODO Auto-generated constructor stub
	}

}
