package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.classes.Produkt;
import de.classes.Bewertung;
import de.classes.Kunde;
import de.datenbank.DBConnection;
/**
 * @see class Bewertung
 *   Diese Klasse bildet die Datenbankoperationen der Klasse Bewertung ab.
 * @author Paul Blanke
 *
 */
public class BewertungsOperations {
	/**
	 * Statement als String.
	 */
	private final static String BEWERTUNG_NACH_ID = "SELECT * FROM bewertung WHERE  kundennr = ?;";
	private final static  String BEWERTUNGEN = "SELECT * FROM bewertung;";
	
	private final static String HOECHSTE_ID = "SELECT MAX(bewertung_id) FROM bewertung;";
	
	private final static String BEWERTUNG_NEU = "INSERT INTO bewertung VALUES(?, ?, ?, ? ,?);";
	
	private final static String BEWERTUNG_LOESCHEN = "DELETE FROM bewertung WHERE bewertung_id = ?;";
	
	private final static String SUCHE_BEST_BEWERTUNG = "SELECT * FROM bewertung WHERE kundennr = ?, produkt_id= ?;";
	
	private final static String ALLE_BEWERT_PRODUKT = "SELECT * FROM bewertung WHERE produkt_id;";
	
	
	/**
	 * Select Methode.
	 * Holt eine Bewertung anhand eines Kunden aus der DB.
	 * @param kunde der Ausl�ser der Bewertung.
	 * 
	 * @return Bewertungsobjekt
	 */
	public static Bewertung holeBewertung(Kunde kunde) {
		Connection con = DBConnection.getConnection();
		Bewertung bewertung = new Bewertung();
		try {
			PreparedStatement pst = con.prepareStatement(BEWERTUNG_NACH_ID);
			pst.setInt(1, kunde.getNutzer_id());
			ResultSet rs = pst.executeQuery();
			rs.next();
			bewertung.setBewertung_id(rs.getInt(1));
			bewertung.setProdukt_id(rs.getInt(3));
			bewertung.setKundennr(rs.getInt(2));
			bewertung.setPunkte(rs.getInt(4));
			bewertung.setKommentar(rs.getString(5));
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bewertung;
	}
	
	
	
	/**
	 * Zeigt alle Bewertungen.
	 * @return Liste von Bewertungen
	 */
	public static List<Bewertung> holeAlleBewetung() {
		List<Bewertung> bewertungen = new ArrayList<>();
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(BEWERTUNGEN);
			ResultSet rs = pst.executeQuery();
			rs.next();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				Bewertung bewertung = new Bewertung(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
				bewertungen.add(bewertung);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bewertungen;
	}
	
	
	
	/**
	 * Die Methode liefert die hoechste ID aus der DB zur�ck.
	 * @return hoechste ID.
	 */
	public static int hoechsteID() {
		int id= 1;
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(HOECHSTE_ID);
			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id+= 1;
		return id;
	}
	
	
	
	/**
	 * Die Methode schreibte eine �bergegebende Bewertung in die DB rein.
	 * @param bewertung die neue Bewertung
	 */
	public static void neueBewertung(Bewertung bewertung) {
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement pst = con.prepareStatement(BEWERTUNG_NEU);
			pst.setInt(1, bewertung.getBewertung_id());
			pst.setInt(2, bewertung.getKundennr());
			pst.setInt(3, bewertung.getProdukt_id());
			pst.setInt(4, bewertung.getPunkte());
			pst.setString(5, bewertung.getKommentar());
			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void entferneBewertung(Bewertung bewertung) {
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement pst = con.prepareStatement(BEWERTUNG_LOESCHEN);
			pst.setInt(1, bewertung.getBewertung_id());
			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode �berpr�ft ob eine Bewertung schon vorhanden ist-> Vermeidung von Doppelungen.
	 * Logik rs leer = false, rs voll = true;
	 * @param kundennr
	 * @param produkt_id
	 * @return
	 */
	public static boolean prufeaufVorhandeneBestellung(int kundennr, int produkt_id) {
		boolean rueckgabe = false;
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(SUCHE_BEST_BEWERTUNG);
			pst.setInt(1, kundennr);
			pst.setInt(2, produkt_id);
			ResultSet rs = pst.executeQuery();
			if(!rs.next()) {
				rueckgabe = false;
			} else if(rs.next()) {
				rueckgabe = true;
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return rueckgabe;
	}
	
	
	public static List<Bewertung> sucheAlleBwertungBestProdukt(int produkt_id) {
		List<Bewertung> bewertProdukt = new ArrayList<>();
		Connection con = DBConnection.getConnection();
		
			PreparedStatement pst;
			try {
				pst = con.prepareStatement(ALLE_BEWERT_PRODUKT);
				pst.setInt(1, produkt_id);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					Bewertung bewertung = new Bewertung(rs.getInt(1), rs.getInt(2),rs.getInt(3), rs.getInt(4), rs.getString(5));
					bewertProdukt.add(bewertung);
				}
				
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			
		return bewertProdukt;
	}
	
}
