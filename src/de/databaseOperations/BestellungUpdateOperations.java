package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.datenbank.DBConnection;
/**
 * Diese Klasse aktualisiert eine bestimmte Bestellung.
 * @author Paul Blanke
 *
 */
public class BestellungUpdateOperations {
	/**
	 * Statement
	 */
	private final static String UPDATE_BEARBEITUNGSSTATUS = "UPDATE bestellung SET status = ? WHERE bstnr =?;";

	
	
	/**
	 * Diese Methode updatet eine Bestellung und ver&andert den Bestellungsstatus
	 * @param bstnr
	 * @param status
	 */
	public static void updateBestellungBearbeitungsstatus(int bstnr, String status) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(UPDATE_BEARBEITUNGSSTATUS);

			pst.setString(1, status);
			pst.setInt(2, bstnr);

			pst.execute();
			con.close();
			DBConnection.closeConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
