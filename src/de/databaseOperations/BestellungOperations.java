package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import de.classes.Bestellung;
import de.classes.Hose;
import de.classes.Kunde;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.datenbank.DBConnection;

/**
 * 
 * Diese Klasse verwaltet BestellungOperationen
 * 
 * @see package-info. {@link package-info}
 * @author Paul blanke
 *
 */
public class BestellungOperations {
	private final static String MAX_BSTNR = "SELECT MAX(bstnr) FROM bestellung;";
	private final static String ANLEGEN_BESTELLUNG_PROD_ZUO = "INSERT INTO bestellung_produktzuordnung VALUES (?, ?, ?)";
	private final static String ANLEGEN_BESTELLUNG = "INSERT INTO bestellung VALUES (?,?,?,'offen')";
	private final static String BESTELLUNG_MIT_BSTNR_AUS_DB_HOLEN = "SELECT * FROM bestellung_produktzuordnung inner join produkt "
			+ "ON(bestellung_produktzuordnung.produkt_id = produkt.produkt_id) WHERE bstnr = ?;";
	private final static String BSTNR_MIT_KUNDENNR_AUS_DB_HOLEN = "SELECT * FROM bestellung WHERE kundennr = ? ORDER BY bstnr ASC;";
	/**
	 * Diese Methode liefert die höchste ID aus der DB und erhöht sie um eins.
	 * @return neue ID zu speichern in der DB
	 */
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
	/**
	 * Diese Methode speichert eine neue Bestellung in der DB.
	 * @param bestellung die zuspeichernde @Bestellung
	 */
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
	/**
	 * Diese Methode speichert eine neue Bestellung in der DB.
	 * @param bestellung die zuspeichernde @Bestellung
	 */
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
		anlegen(bestellung);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
/**
 * Diese Methode holt eine Liste von Bestellung für den einen bestimmten
 * Kunden aus der DB.
 * @param kunde der kunde, zu dem die Bestellung gehören.
 * @return eine Liste aller Bestellungen
 */
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

	/**
	 * Diese Methode holt eine Liste von Bestellung für den einen bestimmten
	 * Kunden aus der DB.
	 * @param knr der kunde, zu dem die Bestellung gehören.
	 * @return eine Liste aller Bestellungen
	 */
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
				String bearbeitungsstatus = rs.getString(4);

				Bestellung bestellung = new Bestellung(bstnr, bestelldatum, bearbeitungsstatus);
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

	

}
