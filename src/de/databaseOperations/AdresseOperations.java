package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Adresse;
import de.classes.Nutzer;
import de.datenbank.DBConnection;

public class AdresseOperations {

	public final static String ADRESSE_ANLEGEN = "INSERT INTO adresse VALUES (?, ?, ?, ?, ?);";
	public final static String ADRESSE_AUS_DBHOLEN = "SELECT * FROM adresse WHERE adress_id = ?;";
	public final static String ADRESSE_UPDATE = "UPDATE adresse SET strasse= ?, hausnummer = ?, postleitzahl =?, ort = ? WHERE adress_id = ?";

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

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static int hoechsteID() {
		Connection con = DBConnection.getConnection();
		int id = 0;
		try {
			PreparedStatement pst = con.prepareStatement("SELECT MAX(adress_id) FROM adresse");

			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		id++;
		return id;
	}
	
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
				return adresse;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;

	}

	public static void adresseDataUpdate(Adresse adresse) {
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement pst = con.prepareStatement(ADRESSE_UPDATE);
			pst.setString(1, adresse.getStrasse());
			pst.setString(2, adresse.getHausnummer());
			pst.setInt(3, adresse.getPlz());
			pst.setString(4, adresse.getOrt());
			pst.setInt(2, adresse.getAdress_id());
			
			pst.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
