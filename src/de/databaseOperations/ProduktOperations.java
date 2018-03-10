package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import de.classes.Produkte;
import de.datenbank.DBConnection;

public class ProduktOperations {
	
	public final static String anlegenProdukt = "INSERT INTO produkt VALUES (?,?,?,?,?,?)";
	
	public final static String Produktzeigen = "SELECT * FROM produkt WHERE  name = ?";
	
	
	public static void anlegen(Produkte produkt) {
		Connection con = DBConnection.getConnection();
		int id = hoechsteID();

		try {
			PreparedStatement pst = con.prepareStatement(anlegenProdukt);
			pst.setDouble(1, produkt.getPreis());
			pst.setInt(2, produkt.getMenge());
			pst.setString(3, produkt.getName());
			pst.setString(4, produkt.getBeschreibung());
			pst.setInt(5, id);
			pst.setString(6, produkt.getKategorie());

			pst.execute();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public static int hoechsteID() {
		Connection con = DBConnection.getConnection();
		int id= 0;
		try {
			PreparedStatement pst = con.prepareStatement("SELECT MAX(produkt_id) FROM produkt");
			
			  ResultSet rs  = pst.executeQuery();
			  rs.next();
			  id= rs.getInt(1);
			  
			  

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(id);
		id++;
		System.out.println(id);
		return id;
	}
	/**
	 * 
	 * Diese Methode liefert zu einem Produkt Daten aus der DB dazu.
	 * @param produkt
	 * @return
	 */
	public Produkte zeigeProdukt(Produkte produkt) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(Produktzeigen);
			pst.setString(1, produkt.getName());

			 ResultSet rs = pst.executeQuery();
			 rs.next();
			 
			 if(rs.next()) {
				 produkt.setPreis(rs.getDouble(1));
				 produkt.setGroesse(rs.getInt(1));
				 produkt.setMenge(rs.getInt(2));
				 produkt.setBeschreibung(rs.getString(1));
				 
			 }
			 
			 con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produkt;
	}
	
	
}
