package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import de.classes.Bestellung;
import de.classes.Kunde;
import de.classes.Nutzer;
import de.classes.Produkt;
import de.datenbank.DBConnection;
import sun.security.action.GetBooleanAction;

public class BestellungOperations {
	private final static String MAX_BSTNR = "SELECT MAX(bstnr) FROM bestellung;";
	private final static String ANLEGEN_BESTELLUNG_PROD_ZUO = "INSERT INTO bestellung_produktzuordnung VALUES (?, ?, ?)";
	private final static String ANLEGEN_BESTELLUNG = "INSERT INTO bestellung VALUES (?,?,?)";

	public static int hoechsteID() {
		Connection con = DBConnection.getConnection();
		int id = 0;
		try {
			PreparedStatement pst = con.prepareStatement(MAX_BSTNR);

			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		id++;

		return id;
	}

	public static void anlegen(Bestellung bestellung) {
		
		List<Produkt> bestellliste = bestellung.getBestellliste();

		Connection con = DBConnection.getConnection();
		PreparedStatement pst;

		try {
<<<<<<< HEAD
			


			for (Produkt produkt : bestellliste) {
				pst = con.prepareStatement(ANLEGEN_BESTELLUNG_PROD_ZUO);
				pst.setInt(1,  bestellung.getBestellnummer());
				pst.setInt(2, bestellung.getKunde().getNutzer_id());
				pst.setInt(3,  produkt.getAnzahl());
=======

			for (Produkt produkt : bestellliste) {
				pst = con.prepareStatement(ANLEGEN_BESTELLUNG_PROD_ZUO);
				pst.setInt(1, bestellung.getBestellnummer());
				pst.setInt(3, produkt.getAnzahl());
				pst.setInt(2, produkt.getProdukt_id());
>>>>>>> 877afe9ba57ac4d1afc014ed765da6b9b0fbdf07
				
				pst.execute();
			

			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void bestellunganlegen(Bestellung bestellung) {
		Connection con = DBConnection.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(ANLEGEN_BESTELLUNG);
<<<<<<< HEAD
=======
			
>>>>>>> 877afe9ba57ac4d1afc014ed765da6b9b0fbdf07
			pst.setInt(1, bestellung.getBestellnummer());
			pst.setInt(2, bestellung.getKunde().getNutzer_id());
			pst.setString(3 , bestellung.getDate().toString());
			
			pst.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		anlegen(bestellung);
	}
}
