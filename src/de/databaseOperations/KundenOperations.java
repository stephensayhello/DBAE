package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.classes.Kunde;
import de.datenbank.DBConnection;

public class KundenOperations {
	
	final String anlegenKunde = "INSERT INTO kunde VALUES ?, ?, ?";
	

	public KundenOperations() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void anlegen(Kunde kunde) {
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement pst = con.prepareStatement(anlegenKunde);
			pst.setInt(1, kunde.getKundennr());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	

}
