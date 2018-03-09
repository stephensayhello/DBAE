package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.classes.Kunde;
import de.classes.Nutzer;
import de.datenbank.DBConnection;

public class KundenOperations {

	public final static String anlegenKunde = "INSERT INTO kunde VALUES (?,?,?,?)";

	public static void anlegen(Kunde kunde) {

		NutzerOperations.anlegen(kunde);
		AdresseOperations.anlegen(kunde.getAdresse());

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(anlegenKunde);
			System.out.println(((Nutzer)kunde).getNutzer_id());
			pst.setInt(1, ((Nutzer)kunde).getNutzer_id());
			pst.setInt(2, kunde.getAdresse().getAdress_id());
			pst.setString(3, kunde.getVorname());
			pst.setString(4, kunde.getNachname());
			
			pst.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
