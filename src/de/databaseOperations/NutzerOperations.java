package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import de.classes.Nutzer;
import de.datenbank.DBConnection;
/**
* 
* Diese Klasse verwaltet NutzerOperationen
* @see {@link package-info}
* 
* @author Benjamin Gajewski
*
*/
public class NutzerOperations {
	/**
	 * Statements
	 */
	private final static String NUTZER_ANLEGEN = "INSERT INTO nutzer VALUES (?, ?, ?)";
	private final static String NUTZER_ABFRAGE = "SELECT * FROM nutzer WHERE email = ?;";
	private final static String KUNDEN_ABFRAGE_NACH_KUNDENNNR = "SELECT * FROM kunde WHERE kundennr = ?;";
	private final static String ADMIN_ABFRGAE_NACH_ADMINID = "SELECT * FROM admin WHERE admin_id = ?;";
	private final static String NUTZER_UPDATE_EMAIL = "UPDATE nutzer SET email = ? WHERE nutzer_id =?;";
	private final static String NUTZER_UPDATE_PASSWORT = "UPDATE nutzer SET passwort = ? WHERE nutzer_id =?;";
	private final static String MAX_NUTZER_ID = "SELECT MAX(nutzer_id) FROM nutzer;";
	private final static String NUTZER_LOESCHEN = "DELETE FROM nutzer WHERE nutzer_id = ?";
	private final static String NUTZER_NACH_ID = "SELECT * FROM nutzer WHERE nutzer_id =?";
/**
 * Diese Methode legt einen neuen Nutzer in der DB an.
 * @param nutzer der neue @Nutzer
 */
	public static void anlegen(Nutzer nutzer) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(NUTZER_ANLEGEN);
			pst.setInt(1, nutzer.getNutzer_id());
			pst.setString(2, nutzer.getPasswort());
			pst.setString(3, nutzer.getEmail());

			pst.execute();
			con.close();
			DBConnection.closeConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Diese methode ermittelt die H&oechste ID aus der DB.
	 * @return H&oechste ID
	 */
	public static int hoechsteID() {
		Connection con = DBConnection.getConnection();
		int id = 0;
		try {
			PreparedStatement pst = con.prepareStatement(MAX_NUTZER_ID);

			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		id++;
		DBConnection.closeConnection();
		return id;
	}

	/**
	 * SELECT 
	 * Diese Methode holt einen Nutzer anhand der Mail aus der DB
	 * @param email selbsterkl&aerend
	 * @return Nutzerobjekt
	 */
	public static Nutzer nutzerAusDbHolen(String email) {

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(NUTZER_ABFRAGE);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int nutzer_id = rs.getInt(1);
				String password = rs.getString(2);
				String emailfromdb = rs.getString(3);
			

				Nutzer nutzer = new Nutzer(nutzer_id, password, emailfromdb);
				con.close();
				DBConnection.closeConnection();
				return nutzer;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		DBConnection.closeConnection();
		return null;

	}
	/**
	 * SELECT 
	 * Diese Methode holt einen Nutzer anhand der ID aus der DB
	 * @param ID selbsterkl&aerend
	 * @return Nutzerobjekt
	 */
	public static Nutzer nutzerAusDbHolen(int id) {

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(NUTZER_NACH_ID);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int nutzer_id = rs.getInt(1);
				String password = rs.getString(2);
				String emailfromdb = rs.getString(3);
			

				Nutzer nutzer = new Nutzer(nutzer_id, password, emailfromdb);
				con.close();
				return nutzer;
			}
			DBConnection.closeConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		DBConnection.closeConnection();
		return null;

	}
	
	
	/**
	 * Diese methode &ueberpr&ueft ob der Nutzer ein kunde ist
	 * @param id - DB ID
	 * @return ist / ist nicht Kunde
	 */
	public static boolean nutzeristKunde(int id) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(KUNDEN_ABFRAGE_NACH_KUNDENNNR);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int kundennr = rs.getInt(1);
				con.close();
				return true;
			}
			DBConnection.closeConnection();

		} catch (SQLException e) {
			
			e.printStackTrace();

		}
		DBConnection.closeConnection();
		return false;

	}
	
	
	/**
	 * Diese methode &ueberpr&ueft ob der Nutzer ein Admin ist
	 * @param id - DB ID
	 * @return ist / ist nicht Admin
	 */
	public static boolean nutzeristAdmin(int id) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ADMIN_ABFRGAE_NACH_ADMINID);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int admin_id = rs.getInt(1);
				con.close();
				DBConnection.closeConnection();
				return true;
			} 

		} catch (

		SQLException e) {
		
			e.printStackTrace();

		}
		DBConnection.closeConnection();
		return false;

	}
	/**
	 * Diese Methode updatet die Daten eines Nutzers
	 * @param nutzer der ge&aenderte Nutzer
	 */
	public static void nutzerDataUpdate(Nutzer nutzer) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(NUTZER_UPDATE_EMAIL);
			pst.setString(1, nutzer.getEmail());
			pst.setInt(2, nutzer.getNutzer_id());
			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBConnection.closeConnection();
	}
	/**
	 * Diese Methode l&oescht einen Nutzer aus der DB.
	 * @param nutzer Nutzerobjekt @Nutzer
	 */
	public static void entferneNutzer(Nutzer nutzer) {
		Connection con = DBConnection.getConnection();
		 try {
			PreparedStatement pst = con.prepareStatement(NUTZER_LOESCHEN);
			pst.setInt(1, nutzer.getNutzer_id());
			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 DBConnection.closeConnection();
	}
	/**
	 * Diese Methode l&oescht einen Nutzer aus der DB.
	 * @param id DB-Id
	 */
	public static void entferneNutzermitID(int id) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(NUTZER_LOESCHEN);
			pst.setInt(1, id);
			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBConnection.closeConnection();
	}
	/**
	 * Diese Methode setzt dem Nutzer ein neues Passwort
	 * @param pw vorbestimmtes Passwort
	 * @param id Kunde, dessen Passwort angepasst wird
	 */
	public static void setzeNeuesPasswort(String pw, int id){
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(NUTZER_UPDATE_PASSWORT);
			pst.setString(1, pw);
			pst.setInt(2, id);
			
			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBConnection.closeConnection();
	}

}
