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
* @author alle.
*
*/
public class NutzerOperations {

	private final static String NUTZER_ANLEGEN = "INSERT INTO nutzer VALUES (?, ?, ?)";

	
	private final static String NUTZER_ABFRAGE = "SELECT * FROM nutzer WHERE email = ?;";

	private final static String KUNDEN_ABFRAGE_NACH_KUNDENNNR = "SELECT * FROM kunde WHERE kundennr = ?;";

	private final static String ADMIN_ABFRGAE_NACH_ADMINID = "SELECT * FROM admin WHERE admin_id = ?;";

	private final static String NUTZER_UPDATE_EMAIL = "UPDATE nutzer SET email = ? WHERE nutzer_id =?;";
	private final static String NUTZER_UPDATE_PASSWORT = "UPDATE nutzer SET passwort = ? WHERE nutzer_id =?;";

	private final static String MAX_NUTZER_ID = "SELECT MAX(nutzer_id) FROM nutzer;";
	
	private final static String NUTZER_LOESCHEN = "DELETE FROM nutzer WHERE nutzer_id = ?";
	
	private final static String NUTZER_NACH_ID = "SELECT * FROM nutzer WHERE nutzer_id =?";

	public static void anlegen(Nutzer nutzer) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(NUTZER_ANLEGEN);
			pst.setInt(1, nutzer.getNutzer_id());
			pst.setString(2, nutzer.getPasswort());
			pst.setString(3, nutzer.getEmail());

			pst.execute();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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
		
		return id;
	}

	/**
	 * 
	 * Diese Methode überprüft das Passwort des Nutzers beim Login.
	 * 
	 * @param nutzer
	 *            der zu prüfende Nutzer.
	 * @return das Passwort ist richtig / oder falsch.
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
				return nutzer;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;

	}
	
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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;

	}
	
	
	
	
	

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
			

		} catch (SQLException e) {
			
			e.printStackTrace();

		}
		return false;

	}
	
	

	public static boolean nutzeristAdmin(int id) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ADMIN_ABFRGAE_NACH_ADMINID);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int admin_id = rs.getInt(1);
				con.close();
				return true;
			}

		} catch (

		SQLException e) {
		
			e.printStackTrace();

		}
		
		return false;

	}

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
	}
	
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
	}
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
	}
	
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
		
	}

}
