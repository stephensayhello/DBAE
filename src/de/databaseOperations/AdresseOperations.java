package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Admin;
import de.classes.Adresse;
import de.datenbank.DBConnection;
/**
* 
* Diese Klasse verwaltet AdressOperationen
* @see 
* @package-info
* @author Paul Blanke
*
*/
public class AdresseOperations {

	private final static String ADRESSE_ANLEGEN = "INSERT INTO adresse VALUES (?, ?, ?, ?, ?);";
	private final static String ADRESSE_AUS_DBHOLEN = "SELECT * FROM adresse WHERE adress_id = ?;";
	private final static String ADRESSE_UPDATE = "UPDATE adresse SET straße= ?, hausnr = ?, postleitzahl =?, ort = ? WHERE adress_id = ?";
	private final static String ADRESSE_LOESCHEN = "DELETE FROM adresse WHERE adress_id = ?;";
	/**
	 * Diese Methode schreibt einen neue Adresse in die DB rein.
	 * @param adresse die neue @Adresse
	 */
	public static void anlegen(Adresse adresse) {

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ADRESSE_ANLEGEN);
			pst.setInt(1, adresse.getAdress_id());
			pst.setString(2, adresse.getStrasse());
			pst.setInt(3, adresse.getPlz());
			pst.setString(4, adresse.getOrt());
			pst.setString(5, adresse.getHausnummer());

			pst.execute();
			con.close();
			DBConnection.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Diese methode ermittelt die h&oechste ID aus der Db und erh&oeht sie um einen.
	 * @return neue ID für DB Speciherung.
	 */
	public static int hoechsteID() {
		Connection con = DBConnection.getConnection();
		int id = 0;
		try {
			PreparedStatement pst = con.prepareStatement("SELECT MAX(adress_id) FROM adresse");

			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);
			con.close();
			DBConnection.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		id++;
		return id;
	}
	/**
	 * Liest eine Adresse aus der DB.
	 * @param id Die ID anhand der die Adresse ermittelt wird
	 * @return rausgesuchte Adresse
	 */
	public static Adresse adresseAusDbHolen(int id) {

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ADRESSE_AUS_DBHOLEN);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				
				String strasse = rs.getString(2);
				int plz = rs.getInt(3);
				String ort = rs.getString(4);
				String hausnr = rs.getString(5);
			
				Adresse adresse = new Adresse(id, strasse, hausnr, plz, ort);
				con.close();
				DBConnection.closeConnection();
				return adresse;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		DBConnection.closeConnection();
		return null;

	}
	/**
	 * Diese Methode updatet die Daten einer Adresse. 
	 * @param adresse die Änderungen an der Adresse
	 */
	public static void adresseDataUpdate(Adresse adresse) {
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement pst = con.prepareStatement(ADRESSE_UPDATE);
			pst.setString(1, adresse.getStrasse());
			pst.setString(2, adresse.getHausnummer());
			pst.setInt(3, adresse.getPlz());
			pst.setString(4, adresse.getOrt());
			pst.setInt(5, adresse.getAdress_id());
			
			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Diese Methode l&oeschte eine Adresse aus der DB.
	 * @param adresse zu löschende Adresse
	 */
	public static void entferneAdresse(Adresse adresse) {
		Connection con = DBConnection.getConnection();
		 try {
			PreparedStatement pst = con.prepareStatement(ADRESSE_LOESCHEN);
			pst.setInt(1, adresse.getAdress_id());
			pst.execute();
			con.close();
			DBConnection.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
