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

public class BestellungOperations {
	private final static String MAX_BSTNR = "SELECT MAX(bstnr) FROM bestellung;";
	private final static String ANLEGEN_BESTELLUNG_PROD_ZUO = "INSERT INTO bestellung_produktzuordnung VALUES (?, ?, ?)";
	private final static String ANLEGEN_BESTELLUNG = "INSERT INTO bestellung VALUES (?, ?,?)";

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

			
=======
>>>>>>> c51ec4561bee20c594a3a7cf82d179878dbc46d2

			for (Produkt produkt : bestellliste) {
				pst = con.prepareStatement(ANLEGEN_BESTELLUNG_PROD_ZUO);
				pst.setInt(1,  1);
				pst.setInt(3, produkt.getMenge());
				pst.setInt(2, produkt.getArtikelnr() );
				
				
				pst.execute();
			

			}
		// 	bestellunganlegen(bestellung);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void bestellunganlegen(Bestellung bestellung) {
		Connection con = DBConnection.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(ANLEGEN_BESTELLUNG);
			pst.setInt(1, 5);
			pst.setInt(2, bestellung.getKunde().getNutzer_id());
			pst.setString(3 , bestellung.getDate().toString());
			pst.execute();
			System.out.println("bestellop");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bestellung.setBestellnummer(2);
		System.out.println("Testfall B2");
		
		anlegen(bestellung);

	}
}
