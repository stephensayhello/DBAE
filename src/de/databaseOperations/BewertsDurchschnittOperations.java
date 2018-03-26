package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.datenbank.DBConnection;
/**
 * Diese Klasse stellt eine Logikmetethode bereit, die zufällige id ermittelt.
 *@see package-infos
 * @author Paul Blanke
 *
 */
public class BewertsDurchschnittOperations {
	/**
	 * Statements
	 */
	private final static String DURCHSCHNITT_PRO_PRODUKT = "SELECT * FROM bewertung WHERE produkt_id = ?;";
	
	/**
	 * Diese Methode ermittelt den Durchschnitt anhand einer produkt_id
	 * @param produkt_id Grundlage der Berechnung.
	 * @return durchschnittliche Bewertung pro Produkt
	 */
	public static int ermitteleneDurchschnitt(int produkt_id) {
		int counter = 0;
		int sum = 0;
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(DURCHSCHNITT_PRO_PRODUKT);
			pst.setInt(1, produkt_id);
			ResultSet rs = pst.executeQuery();
			if(!rs.next()) {
				counter = 1;
				sum = 5;
			}
			
			while(rs.next()) {
				counter++;
				int eins = rs.getInt(1);
				int zwei = rs.getInt(2);
				int drei = rs.getInt(3);
				sum += rs.getInt(4);
				String komm = rs.getString(5);
			}
			if(!rs.next()) {
				sum = sum / counter;
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBConnection.closeConnection();
		return sum;
	}
	
}
