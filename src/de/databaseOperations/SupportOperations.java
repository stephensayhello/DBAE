package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.SupportAnfrage;
import de.datenbank.DBConnection;

public class SupportOperations {

	public final static String ANFRAGE_SPEICHERN = "INSERT INTO  supportanfrage VALUES (?, ?, ?)";
	public final static String HOECHSTEID = "SELECT MAX(sa_id) FROM supportanfrage";
	
	
	public static void speichereSupportAnfrage(SupportAnfrage anfrage) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(ANFRAGE_SPEICHERN);
			pst.setInt(1, anfrage.getSupport_id());
			String nachricht = anfrage.getAnfrage() + " // " + anfrage.getText() + " // Grund: " + anfrage.getGrund();
			pst.setString(2, nachricht);
			pst.setInt(3, 1);
			
			pst.execute();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static int hoechsteID() {
		Connection con = DBConnection.getConnection();
		int id = 1;
		try {
			PreparedStatement pst = con.prepareStatement(HOECHSTEID);
			ResultSet rs = pst.executeQuery();
			if(!rs.next()) {
				id = rs.getInt(0);
			}
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id++;
		return id;
	}
	
	
	
	
	
	
	
}
