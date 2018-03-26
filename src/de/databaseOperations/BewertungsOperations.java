package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import de.classes.Bewertung;
import de.classes.Hose;
import de.classes.Kunde;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.datenbank.DBConnection;

public class BewertungsOperations {
	
	private final static String MAX_BEWERTUNGSID = "SELECT MAX(bewertungsid) FROM bewertung;";
	private static final String LADE_BEWERTUNG_ZU_ARTIKEL = "SELECT * FROM bewertung where artikelnr = ?;";
	private static final String LADE_BEWERTUNGEN = "SELECT * FROM bewertung;";
	private static final String BEWERTUNG_ANLEGEN = "INSERT INTO bewertung VALUES(?,?,?,?,?,?);";
	private static final String BEWERTUNGEN_ZU_ARTIKEL_LOESCHEN = "DELETE FROM bewertung WHERE artikelnr = ?;";
	private static final String BEWERTUNGEN_ZU_ARTIKEL_LOESCHEN_MIT_KUNDENNR = "DELETE FROM bewertung WHERE kundennr = ?;";
	private static final String DURCHSCHNITTLICHE_BEWERTUNG_PRO_ARTIKEL = "SELECT AVG(punkte) FROM bewertung WHERE artikelnr = ? GROUP BY artikelnr;";

	
	
	public static int ladeDurschnitt(int artikelnr) {
		Connection con = DBConnection.getConnection();
		int durschnitt = 0;

		try {
			PreparedStatement pst = con.prepareStatement(DURCHSCHNITTLICHE_BEWERTUNG_PRO_ARTIKEL);
			pst.setInt(1, artikelnr);

			ResultSet set = pst.executeQuery();
			try {
			set.next();
			durschnitt = set.getInt(1);
			} catch (PSQLException e) {
				return 5;
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return durschnitt;

	}
	
	
	
	public static void bewertungAnlegen(Bewertung bewertung) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(BEWERTUNG_ANLEGEN);
			pst.setInt(1, bewertung.getBewertungsid());
			pst.setInt(2, bewertung.getArtikelnr());
			pst.setInt(3, bewertung.getKundennr());
			pst.setInt(4, bewertung.getPunkte());
			pst.setString(5, bewertung.getDatum().toString());
			pst.setString(6, bewertung.getText());

			pst.execute();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public static List<Bewertung> ladeBewertungen(int artikelnr) {
		Connection con = DBConnection.getConnection();
		List<Bewertung> bewertungen = new ArrayList<>();

		try {
			PreparedStatement pst = con.prepareStatement(LADE_BEWERTUNG_ZU_ARTIKEL);
			pst.setInt(1, artikelnr);

			ResultSet set = pst.executeQuery();
			Bewertung bewertung = null;
			
			while(set.next()) {
				
				Kunde kunde = KundenOperations.kundeausdbholen(NutzerOperations.nutzerAusDbHolen(set.getInt(3)));
				Produkt produkt = ProduktOperations.ladeProduktausdb(set.getInt(2));
				
				bewertung = new Bewertung(set.getInt(1), produkt, kunde, set.getInt(4), set.getString(5), set.getString(6));
				bewertungen.add(bewertung);
			}
			

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bewertungen;

	}
	
	
	
	
	public static int hoechsteBewertungsID() {
		Connection con = DBConnection.getConnection();
		int id = 0;
		try {
			PreparedStatement pst = con.prepareStatement(MAX_BEWERTUNGSID);

			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		id++;
		DBConnection.closeConnection();
		return id;
	}



	public static List<Bewertung> ladeAlleBewertungen() {
		Connection con = DBConnection.getConnection();
		List<Bewertung> bewertungen = new ArrayList<>();

		try {
			PreparedStatement pst = con.prepareStatement(LADE_BEWERTUNGEN);

			ResultSet set = pst.executeQuery();
			Bewertung bewertung = null;
			
			while(set.next()) {
				
				Kunde kunde = KundenOperations.kundeausdbholen(NutzerOperations.nutzerAusDbHolen(set.getInt(3)));
				Produkt produkt = ProduktOperations.ladeProduktausdb(set.getInt(2));
				
				bewertung = new Bewertung(set.getInt(1), produkt, kunde, set.getInt(4), set.getString(5), set.getString(6));
				bewertungen.add(bewertung);
			}
			

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bewertungen;
	}
	public static void entferneBewertungmitKundennr(int id) {
		Connection con = DBConnection.getConnection();
		 try {
			PreparedStatement pst = con.prepareStatement(BEWERTUNGEN_ZU_ARTIKEL_LOESCHEN_MIT_KUNDENNR);
			pst.setInt(1, id);
			pst.execute();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 DBConnection.closeConnection();
	}
	
}
