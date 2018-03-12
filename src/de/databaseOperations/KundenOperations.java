package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Kunde;
import de.classes.Nutzer;
import de.datenbank.DBConnection;

public class KundenOperations {

	public final static String ANLEGEN_KUNDE = "INSERT INTO kunde VALUES (?,?,?,?)";
	public final static String MAIL_KUNDE_VERGLEICH = "SELECT email FROM nutzer WHERE email = ?;";

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

}
