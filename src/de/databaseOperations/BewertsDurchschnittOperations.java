package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.datenbank.DBConnection;
/**
 * Diese Klasse stellt eine Logikmetethode bereit, die zufällige id ermittelt.
 *@see package-infos
 * @author paul4
 *
 */
public class BewertsDurchschnittOperations {

	public final static String DURCHSCHNITT_PRO_PRODUKT = "SELECT * FROM bewertung WHERE produkt_id = ?;";
	private final static String DURCHSCHNITT_BEWERTUNG = "SELECT score FROM bewertungs_durchschnitt WHERE artikelnr = ?;";
/**
 * Diese Methode ermittelt den Durchschnitt anhand einer produkt_id
 * @param produkt_id Grundlage der Berechnung.
 * @return durchschnitt pro Produkt
 */
	public static int ermitteleneDurchschnitt(int produkt_id) {
		int counter = 0;
		int sum = 0;
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(DURCHSCHNITT_PRO_PRODUKT);
			pst.setInt(1, produkt_id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				counter++;
				int eins = rs.getInt(1);
				int zwei = rs.getInt(2);
				int drei = rs.getInt(3);
				sum = rs.getInt(4);
				String komm = rs.getString(5);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sum = sum /counter;
		return sum;
	}
	
}
