package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			pst.setInt(3, adresse.getPlz());
			pst.setString(4, adresse.getOrt());
			pst.setString(5, adresse.getHausnummer());

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static int hoechsteID() {
		Connection con = DBConnection.getConnection();
		int id = 0;
		try {
			PreparedStatement pst = con.prepareStatement("SELECT MAX(adress_id) FROM adresse");

			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		id++;
		return id;
	}

}
