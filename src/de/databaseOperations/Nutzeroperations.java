package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.classes.Kunde;
import de.datenbank.DBConnection;

public class Nutzeroperations {
	static int counter = 0;
	final String Nutzeranlegen = "INSERT INTO nutzer VALUES ?, ?, ?";

	public Nutzeroperations() {
		counter++;
	}

	public void anlegen(Kunde kunde) {
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement pst = con.prepareStatement(Nutzeranlegen);
			pst.setInt(1, counter);
			kunde.setKundennr(counter);
			pst.setString(2, kunde.getEmail() );
			pst.setString(3, kunde.getPasswort());
			
			pst.executeQuery();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
