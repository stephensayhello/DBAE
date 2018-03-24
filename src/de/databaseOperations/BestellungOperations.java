package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.classes.Adresse;
import de.classes.Bestellung;
import de.classes.Hose;
import de.classes.Kunde;
import de.classes.Nutzer;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.datenbank.DBConnection;
import sun.security.action.GetBooleanAction;

/**
 * 
 * Diese Klasse verwaltet BestellungOperationen
 * 
 * @see package-info. {@link package-info}
 * @author alle.
 *
 */
public class BestellungOperations {
	private final static String MAX_BSTNR = "SELECT MAX(bstnr) FROM bestellung;";
	private final static String ANLEGEN_BESTELLUNG_PROD_ZUO = "INSERT INTO bestellung_produktzuordnung VALUES (?, ?, ?)";
	private final static String ANLEGEN_BESTELLUNG = "INSERT INTO bestellung VALUES (?,?,?)";
	private final static String BESTELLUNG_MIT_BSTNR_AUS_DB_HOLEN = "SELECT * FROM bestellung_produktzuordnung inner join produkt "
			+ "ON(bestellung_produktzuordnung.produkt_id = produkt.produkt_id) WHERE bstnr = ?;";
	private final static String BESTELLUNG_NATURAL_JOIN = "SELECT * FROM produkt NATURAL JOIN bestellung_produktzuordnung ;";
	
	private final static String BSTNR_MIT_KUNDENNR_AUS_DB_HOLEN = "SELECT * FROM bestellung WHERE kundennr = ?;";
	private final static String BESTELLUNG_AUS_DB = "SELECT * FROM bestellung ;";
	private final static String BESTELLUNG_AUF_PRODUKT_PRUEFEN = "SELECT * FROM bestellung_produktzuordnung inner join produkt "
			+ "ON(bestellung_produktzuordnung.produkt_id = produkt.produkt_id) WHERE kundennr = ?, produkt_id = ? ";

	public static int hoechsteID() {
		Connection con = DBConnection.getConnection();
		int id = 0;
		try {
			PreparedStatement pst = con.prepareStatement(MAX_BSTNR);

			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);
			con.close();
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

			for (Produkt produkt : bestellliste) {
				pst = con.prepareStatement(ANLEGEN_BESTELLUNG_PROD_ZUO);
				pst.setInt(1, bestellung.getBestellnummer());
				pst.setInt(2, produkt.getProdukt_id());
				pst.setInt(3, produkt.getAnzahl());

				pst.execute();

			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void bestellunganlegen(Bestellung bestellung) {
		Connection con = DBConnection.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(ANLEGEN_BESTELLUNG);

			pst.setInt(1, bestellung.getBestellnummer());
			pst.setInt(2, bestellung.getKunde().getNutzer_id());
			pst.setString(3, bestellung.getDate().toString());

			pst.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	public static List<Bestellung> bestellungausdbholen(Kunde kunde) {

		List<Bestellung> bestellungen = bestelldatumundBstnrMitKnrAusDbholen(kunde.getNutzer_id());

		try {
			for (Bestellung bestellung : bestellungen) {
				List<Produkt> produktbestellliste = new ArrayList<>();
				Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(BESTELLUNG_MIT_BSTNR_AUS_DB_HOLEN);
				pst.setInt(1, bestellung.getBestellnummer());
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					int produkt_id = rs.getInt(2);
					int preis = rs.getInt(4);
					int menge = rs.getInt(5);
					String name = rs.getString(6);
					String beschreibung = rs.getString(7);
					int artikelnr = rs.getInt(9);
					int versanddauer = rs.getInt(10);
					String status = rs.getString(11);
					String imagepath = rs.getString(12);
					int anzahl = rs.getInt(3);
					Produkt produkt = new Produkt(produkt_id, name, beschreibung, preis, menge, artikelnr, anzahl,
							versanddauer, status, imagepath);
					if (ProduktOperations.produktistHose(produkt_id)) {
						Hose hose = HoseOperations.hoseausdbholen(produkt);
						produktbestellliste.add(hose);
					} else if (ProduktOperations.produktistSchuhe(produkt_id)) {
						Schuhe schuhe = SchuheOperations.holeSchuheausdb(produkt);
						produktbestellliste.add(schuhe);
					} else if (ProduktOperations.produktistShirt(produkt_id)) {
						Shirt shirt = ShirtOperations.holeShirtausdb(produkt);
						produktbestellliste.add(shirt);
					}

					con.close();
				}

				bestellung.setBestellliste(produktbestellliste);
				bestellung.setKunde(kunde);

			}

			return bestellungen;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	return null;	
	}
		public static List<Bestellung> allebestellungenausgeben() {
			
			List<Bestellung> bestellungen = BestellungmitDatumAusDbholen() ;
			
			try {
				for (Bestellung bestellung : bestellungen) {
					List<Produkt> produktbestellliste = new ArrayList<>();
					Connection con = DBConnection.getConnection();
					PreparedStatement pst = con.prepareStatement(BESTELLUNG_NATURAL_JOIN);
					
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						int produkt_id = rs.getInt(1);
						int preis = rs.getInt(2);
						int menge = rs.getInt(3);
						String name = rs.getString(4);
						String beschreibung = rs.getString(5);
						int artikelnr = rs.getInt(6);
						int versanddauer = rs.getInt(7);
						String status = rs.getString(8);
						String imagepath = rs.getString(9);
						int anzahl = rs.getInt(10);
						Produkt produkt = new Produkt(produkt_id, name, beschreibung, preis, menge, artikelnr, anzahl,
								versanddauer, status, imagepath);
						if (ProduktOperations.produktistHose(produkt_id)) {
							Hose hose = HoseOperations.hoseausdbholen(produkt);
							produktbestellliste.add(hose);
						} else if (ProduktOperations.produktistSchuhe(produkt_id)) {
							Schuhe schuhe = SchuheOperations.holeSchuheausdb(produkt);
							produktbestellliste.add(schuhe);
						} else if (ProduktOperations.produktistShirt(produkt_id)) {
							Shirt shirt = ShirtOperations.holeShirtausdb(produkt);
							produktbestellliste.add(shirt);
						}
						
						con.close();
					}
					
					bestellung.setBestellliste(produktbestellliste);
					
					
				}
				
				return bestellungen;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}

		return null;

	}

	public static List<Bestellung> bestelldatumundBstnrMitKnrAusDbholen(int knr) {

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(BSTNR_MIT_KUNDENNR_AUS_DB_HOLEN);
			pst.setInt(1, knr);
			ResultSet rs = pst.executeQuery();
			List<Bestellung> teilbestellungen = new ArrayList<>();
			while (rs.next()) {
				int bstnr = rs.getInt(1);
				String bestelldatum = rs.getString(3);

				Bestellung bestellung = new Bestellung(bstnr, bestelldatum);
				teilbestellungen.add(bestellung);
			}

			con.close();
			return teilbestellungen;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;

	}
	public static List<Bestellung> BestellungmitDatumAusDbholen() {
		
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement pst = con.prepareStatement(BESTELLUNG_AUS_DB);
			
			ResultSet rs = pst.executeQuery();
			List<Bestellung> teilbestellungen = new ArrayList<>();
			while (rs.next()) {
				int bstnr = rs.getInt(1);
				int knr = rs.getInt(2);
				String bestelldatum = rs.getString(3);
				Kunde kunde = KundenOperations.kundeausdbholen(NutzerOperations.nutzerAusDbHolen(knr));
				Bestellung bestellung = new Bestellung(bstnr,bestelldatum,kunde);
				teilbestellungen.add(bestellung);
			}
			
			con.close();
			return teilbestellungen;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return null;
		
	}

	/**
	 * Diese Methode pr�ft ob ein Kunde ein Produkt bestellt hat.
	 * 
	 * @param kunde
	 *            der pr�fende Kunde.
	 * @param produkt_id
	 *            das pr�fende Produkt
	 * @return hat / hat nicht das Produkt.
	 */
	public static boolean pruefeBestellungAufProdukt(Kunde kunde, int produkt_id) {
		Connection con = DBConnection.getConnection();
		boolean rueckgabe = false;
		try {
			PreparedStatement pst = con.prepareStatement(BESTELLUNG_AUF_PRODUKT_PRUEFEN);
			pst.setInt(1, kunde.getNutzer_id());
			pst.setInt(2, produkt_id);
			ResultSet rs = pst.executeQuery();

			if (!rs.next()) {
				rueckgabe = false;
			} else {
				rueckgabe = true;
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rueckgabe;

	}

}
