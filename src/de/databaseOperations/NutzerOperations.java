package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Kunde;
import de.classes.Nutzer;
import de.datenbank.DBConnection;

public class NutzerOperations {

	public final static String Nutzeranlegen = "INSERT INTO nutzer VALUES (?, ?, ?)";
	

	public static void anlegen(Nutzer nutzer) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(Nutzeranlegen);
			pst.setInt(1, nutzer.getNutzer_id());
			pst.setString(2, nutzer.getEmail());
			pst.setString(3, nutzer.getPasswort());

			pst.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int hoechsteID() {
		Connection con = DBConnection.getConnection();
		int id= 0;
		try {
			PreparedStatement pst = con.prepareStatement("SELECT MAX(nutzer_id) FROM nutzer");
			
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
	
}
