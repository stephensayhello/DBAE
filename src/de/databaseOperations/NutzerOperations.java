package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Kunde;
import de.classes.Nutzer;
import de.datenbank.DBConnection;

public class NutzerOperations {

	public final static String NUTZERANLEGEN = "INSERT INTO nutzer VALUES (?, ?, ?)";

	public final static String PASSWORTABFRAGE = "SELECT passwort FROM nutzer WHERE email= ?";

	public final static String NUTZERABFRAGE = "SELECT * FROM nutzer WHERE email = ?";

	public final static String KUNDENABFRAGENACHKUNDENNNR = "SELECT * FROM kunde WHERE kundennr = ?";
	
	public final static String ADMINABFRGAENACHADMINID ="SELECT * FROM admin WHERE admin_id = ?";

	public static void anlegen(Nutzer nutzer) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(NUTZERANLEGEN);
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
			PreparedStatement pst = con.prepareStatement("SELECT MAX(nutzer_id) FROM nutzer");

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

	/**
	 * 
	 * Diese Methode �berpr�ft das Passwort des Nutzers beim Login.
	 * 
	 * @param nutzer
	 *            der zu pr�fende Nutzer.
	 * @return das Passwort ist richtig / oder falsch.
	 */

	public static boolean login(Nutzer nutzer) {
		boolean passwortabfrage = false;
		String passwort = "";
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(PASSWORTABFRAGE);
			pst.setString(1, nutzer.getEmail());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				passwort = rs.getString(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (nutzer.getPasswort().contains(passwort)) {
			passwortabfrage = true;
		} else {
			// Tue nichts.
		}
		return passwortabfrage;
	}

	public static Nutzer nutzerAusDbHolen(String email) {

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(NUTZERABFRAGE);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int nutzer_id = rs.getInt(1);
				String password = rs.getString(2);
				String emailfromdb = rs.getString(3);
				System.out.println(nutzer_id);
				System.out.println(password);
				System.out.println(emailfromdb);

				Nutzer nutzer = new Nutzer(nutzer_id, password, emailfromdb);
				return nutzer;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;

	}

	public static boolean nutzeristKunde(Nutzer nutzer) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(KUNDENABFRAGENACHKUNDENNNR);
			pst.setInt(1, nutzer.getNutzer_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int kundennr = rs.getInt(1);

				System.out.println(kundennr);
				System.out.println();

				return true;
			}
		
		} catch (

		SQLException e) {
			System.out.println("Fehler");
			e.printStackTrace();

		}
		return false;

	}
	public static boolean nutzeristAdmin(Nutzer nutzer) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ADMINABFRGAENACHADMINID);
			pst.setInt(1, nutzer.getNutzer_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int admin_id = rs.getInt(1);

				System.out.println(admin_id);
				System.out.println();

				return true;
			}
		
		} catch (

		SQLException e) {
			System.out.println("Fehler");
			e.printStackTrace();

		}
		return false;

	}

}
