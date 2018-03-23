package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.classes.Admin;
import de.classes.Adresse;
import de.classes.Kunde;
import de.classes.Nutzer;
import de.datenbank.DBConnection;

public class KundenOperations {

	private final static String ANLEGEN_KUNDE = "INSERT INTO kunde VALUES (?,?,?,?)";
	private final static String MAIL_KUNDE_VERGLEICH = "SELECT email FROM nutzer WHERE email = ?;";
	private final static String KUNDEN_ABFRAGE = "SELECT * FROM kunde WHERE kundennr = ?;";
	private final static String KUNDE_UPDATE = "UPDATE kunde SET vorname= ?, nachname= ? WHERE kundennr = ?";
	private final static String KUNDE_LISTE ="SELECT * FROM kunde ORDER BY kundennr ASC;";
	
	private final static String KUNDE_LOESCHEN = "DELETE FROM kunde WHERE kundennr = ?;";
	
	
    
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
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;
		
	}

	public static void kundenUpdateDaten(Kunde kunde) {
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement pst = con.prepareStatement(KUNDE_UPDATE);
			pst.setString(1, kunde.getVorname());
			pst.setString(2, kunde.getNachname());
			pst.setInt(3,kunde.getNutzer_id());
			pst.execute();
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NutzerOperations.nutzerDataUpdate(kunde);
		Adresse adresse = kunde.getAdresse();
		adresse.setAdress_id(kunde.getNutzer_id());
		AdresseOperations.adresseDataUpdate(adresse);
	}
	
	
	public static void entferneKunde(Kunde kunde) {
		Connection con = DBConnection.getConnection();
		 try {
			PreparedStatement pst = con.prepareStatement(KUNDE_LOESCHEN);
			pst.setInt(1, kunde.getNutzer_id());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Hole alle Kunden aus DB
	public static List<Kunde> holeAlleKunden() {
		List<Kunde> kunden  = new ArrayList<>();
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement pst = con.prepareStatement(KUNDE_LISTE);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				Nutzer nutzer = NutzerOperations.nutzerAusDbHolen(id);
				int adress_id = rs.getInt(2);
				String mail = nutzer.getEmail();
				String vorname = rs.getString(3);
				String nachname = rs.getString(4);
				Adresse adresse = AdresseOperations.adresseAusDbHolen(adress_id);
				String passwort = nutzer.getPasswort();
				Kunde kunde = new Kunde(id,passwort, mail, adresse, vorname , nachname);
				kunden.add(kunde);
				
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return kunden;
	}
}
