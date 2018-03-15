package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.datenbank.DBConnection;

public class Warenkorboperations {
private static final String MAX_WARENKORB_ID = "SELECT MAX(warenkorb_id) FROM warenkorb_kunde_zuordnung;";
	public static int hoechsteID() {
		Connection con = DBConnection.getConnection();
		int id = 0;
		try {
			PreparedStatement pst = con.prepareStatement(MAX_WARENKORB_ID);

			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);

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
