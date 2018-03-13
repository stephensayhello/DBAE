package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Admin;
import de.classes.Nutzer;
import de.datenbank.DBConnection;

public class AdminOperations {
	public static final String ADMINAUSDBHOLEN = "SELECT * FROM admin WHERE admin_id = ?;";

	public static Admin holeAdminausDB(Nutzer nutzer) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ADMINAUSDBHOLEN);
			pst.setInt(1, nutzer.getNutzer_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int admin_id = rs.getInt(1);
				String name = rs.getString(2);
				System.out.println(admin_id);

				Admin admin = new Admin(nutzer.getNutzer_id(), nutzer.getEmail(), nutzer.getPasswort(), name);
				return admin;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;

	}

}
