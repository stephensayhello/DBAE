package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Adresse;
import de.classes.Kunde;
import de.classes.Nutzer;
import de.datenbank.DBConnection;

public class KundenOperations {

	public final static String ANLEGEN_KUNDE = "INSERT INTO kunde VALUES (?,?,?,?)";
	public final static String MAIL_KUNDE_VERGLEICH = "SELECT email FROM nutzer WHERE email = ?;";
	public final static String KUNDEN_ABFRAGE = "SELECT * FROM kunde WHERE kundennr = ?;";
    
	public static void anlegen(Kunde kunde) {

		NutzerOperations.anlegen(kunde);
		AdresseOperations.anlegen(kunde.getAdresse());

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ANLEGEN_KUNDE);
			System.out.println(((Nutzer) kunde).getNutzer_id());
			pst.setInt(1, ((Nutzer) kunde).getNutzer_id());
			pst.setInt(2, kunde.getAdresse().getAdress_id());
			pst.setString(3, kunde.getVorname());
			pst.setString(4, kunde.getNachname());

			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static boolean mailIstVorhanden(String mail) {
		Connection con = DBConnection.getConnection();
		PreparedStatement prprdQuery;
		try {

			prprdQuery = con.prepareStatement(MAIL_KUNDE_VERGLEICH);
			prprdQuery.setString(1, mail);

			ResultSet rs = prprdQuery.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;

	}
	
	public static Kunde kundeausdbholen (Nutzer nutzer){

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(KUNDEN_ABFRAGE);
			pst.setInt(1, nutzer.getNutzer_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int adress_id = rs.getInt(2);
				String vorname = rs.getString(3);
				String nachname= rs.getString(4);
			    Adresse adresse = AdresseOperations.adresseAusDbHolen(adress_id);
				Kunde kunde = new Kunde(nutzer.getNutzer_id(), nutzer.getPasswort(), nutzer.getEmail(), adresse, vorname, nachname);
				return kunde;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;
		
		
	}

}
