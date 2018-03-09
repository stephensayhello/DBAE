package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.classes.Adresse;
import de.datenbank.DBConnection;

public class AdresseOperations {

	public final static String Adresse_anlegen = "INSERT INTO adresse VALUES (?, ?, ?, ?, ?);";

	public static void anlegen(Adresse adresse) {

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(Adresse_anlegen);
			pst.setInt(1, adresse.getAdress_id());
			pst.setString(2, adresse.getStrasse());
			pst.setInt(3, adresse.getHausnummer());
			pst.setInt(4, adresse.getPlz());
			pst.setString(5, adresse.getOrt());
			
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
