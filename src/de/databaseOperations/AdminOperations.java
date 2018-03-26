package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Admin;
import de.classes.Nutzer;
import de.datenbank.DBConnection;
/**
 * 
 * Diese Klasse verwaltet AdminOperationen
 * @see package-infos.
 * @author Stephen Galla
 *
 */
public class AdminOperations {
	private static final String ADMIN_AUS_DB_HOLEN = "SELECT * FROM admin WHERE admin_id = ?;";
	private static final String  ADMIN_LOESCHEN= " DELETE  FROM admin WHERE admin_id = ?";
	/**
	 * Diese Metthode holt einen Admin aus der DB raus.
	 * @param nutzer Ein Objekt der Klasse @Nutzer
	 * @return ein Admin Objekt @Admin
	 */
	public static Admin holeAdminausDB(Nutzer nutzer) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ADMIN_AUS_DB_HOLEN);
			pst.setInt(1, nutzer.getNutzer_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int admin_id = rs.getInt(1);
				String name = rs.getString(2);
				

				Admin admin = new Admin(nutzer.getNutzer_id(), nutzer.getEmail(), nutzer.getPasswort(), name);
				
				con.close();
				DBConnection.closeConnection();
				return admin;
			}	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		DBConnection.closeConnection();
		return null;

	}
	
	/**
	 * Diese Methode entfernt einen gegebenden Admin aus der DB.
	 * @param admin Der zu L&oeschende Admin
	 */
	public static void entferneAdmin(Admin admin) {
		Connection con = DBConnection.getConnection();
		 try {
			PreparedStatement pst = con.prepareStatement(ADMIN_LOESCHEN);
			pst.setInt(1, admin.getNutzer_id());
			pst.execute();
			con.close();
			DBConnection.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
