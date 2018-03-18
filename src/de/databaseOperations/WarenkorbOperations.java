package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Admin;
import de.classes.Warenkorb;
import de.datenbank.DBConnection;

public class WarenkorbOperations {
	private static final String MAX_WARENKORB_ID = "SELECT MAX(warenkorb_id) FROM warenkorb_kunde_zuordnung;";
	
	private static final String WARENKORB_LOESCHEN = "DELETE FROM warenkorb_kunde_zuordnung WHERE warenkorb_id = ?;";

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
		
		id++;
		
		return id;
	}
	public static void entferneAdmin(Warenkorb korb) {
		Connection con = DBConnection.getConnection();
		 try {
			PreparedStatement pst = con.prepareStatement(WARENKORB_LOESCHEN);
			pst.setInt(1, korb.getWarenkorb_id());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
