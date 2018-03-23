package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Admin;
import de.classes.SupportAnfrage;
import de.datenbank.DBConnection;

public class SupportOperations {

	private final static String ANFRAGE_SPEICHERN = "INSERT INTO  supportanfrage VALUES (?, ?, ?)";
	private final static String HOECHSTEID = "SELECT MAX(sa_id) FROM supportanfrage";
	private final static String ANFRAGE_LOESCHEN = "DELETE FROM supportanfrage WHERE sa_id = ?;";
	
	
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
			rs.next();
			id = rs.getInt(1);
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id++;
		return id;
	}
	
	public static void entferneAnfrage(SupportAnfrage anfrage) {
		Connection con = DBConnection.getConnection();
		 try {
			PreparedStatement pst = con.prepareStatement(ANFRAGE_LOESCHEN);
			pst.setInt(1, anfrage.getSupport_id());
			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
