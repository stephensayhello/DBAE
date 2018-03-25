package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import de.classes.Kunde;
import de.classes.Produkt;
import de.classes.Warenkorb;
import de.datenbank.DBConnection;
/**
* 
* Diese Klasse verwaltet @Warenkorb Operations
* @see {@link package-info}
* 
* @author Stephen Galla
*
*/
public class WarenkorbOperations {
	/**
	 * Statements
	 */
	private static final String MAX_WARENKORB_ID = "SELECT MAX(warenkorb_id) FROM warenkorb_kunde_zuordnung;";
	private static final String WARENKORB_LOESCHEN_ZU = "DELETE FROM warenkorb_kunde_zuordnung WHERE warenkorb_id = ?";
	private static final String WARENKORB_LOESCHEN = "DELETE FROM warenkorb WHERE warenkorb_id = ?";
	private static final String WARENKORB_SPEICHERN = "INSERT INTO warenkorb VALUES(?,?,?);";
	private static final String WARENKORB_BESTELLUNG_PROD_ZUO = "INSERT INTO warenkorb_kunde_zuordnung VALUES(?,?);";
	private static final String WARENKORB_LADEN = "SELECT * FROM warenkorb WHERE warenkorb_id = ?;";
	private static final String WARENKORB_KUNDE_ZU_LADEN = "SELECT warenkorb_id FROM warenkorb_kunde_zuordnung WHERE kundennr = ?;";
	
	/**
	 * Diese Methode holt die H&oechste ID aus der DB.
	 * @return hoechste ID
	 */
	public static int hoechsteID() {
		Connection con = DBConnection.getConnection();
		int id = 0;
		try {
			PreparedStatement pst = con.prepareStatement(MAX_WARENKORB_ID);

			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		id++;

		return id;
	}
	/**
	 * DELETE
	 * Diese methode l&oescht einen Warenkorb aus der DB.
	 * @param id Die zu entfernde ID
	 */
	public static void entferneWarenkorb(int id) {
		System.out.println("WARENKORB ID:");
		System.out.println(id);
		
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement pst = con.prepareStatement(WARENKORB_LOESCHEN);
			pst.setInt(1, id);
			pst.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement pst = con.prepareStatement(WARENKORB_LOESCHEN_ZU);
			pst.setInt(1, id);
			pst.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	/**
	 * Diese Methode ordnet eine Liste von Produkten einen bestimmten Warenkorb zu.
	 * @param warenkorb Der Warenkob der mit den Produkten verkn&uepft wird
	 */
	private static void anlegenWarenkorbProduktzuordnung(Warenkorb warenkorb) {

		List<Produkt> inhalt = warenkorb.getInhalt();

		Connection con = DBConnection.getConnection();
		PreparedStatement pst;

		try {

			for (Produkt produkt : inhalt) {
				pst = con.prepareStatement(WARENKORB_SPEICHERN);
				pst.setInt(1, warenkorb.getWarenkorb_id());
				pst.setInt(2, produkt.getProdukt_id());
				pst.setInt(3, produkt.getAnzahl());
				pst.execute();
				con.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * INSERT INTO
	 * Diese Methode legt einen neuen Warenkorb in der DB an.
	 * @param warenkorb das neue objekt der Klasse @Warenkorb
	 */
	public static void anlegenWarenkorb(Warenkorb warenkorb) {
		Connection con = DBConnection.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(WARENKORB_BESTELLUNG_PROD_ZUO);
			pst.setInt(1, warenkorb.getWarenkorb_id());
			pst.setInt(2, warenkorb.getKunde().getNutzer_id());

			pst.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		anlegenWarenkorbProduktzuordnung(warenkorb);
	}
	/**
	 * Diese methode ladet einen Warenkorb aus der DB anhand eines Kunden
	 * @param kunde der bestimmte Kunde
	 * @return der Warenkorb des kunden
	 */
	public static Warenkorb ladeWarenkorbAusDB(Kunde kunde) {

		Connection con = DBConnection.getConnection();
		PreparedStatement pst;
		Warenkorb warenkorb = null;

		try {
			pst = con.prepareStatement(WARENKORB_KUNDE_ZU_LADEN);
			pst.setInt(1, kunde.getNutzer_id());
			ResultSet set = pst.executeQuery();
			
			set.next();
			int warenkorb_id = set.getInt(1);

			List<Produkt> produkte = ladeProdukteZuWarenkorbAusDB(warenkorb_id);
			warenkorb = new Warenkorb(kunde, produkte, warenkorb_id);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return warenkorb;
	}
	
	/**
	 * Diese Methode liefert eine Liste aller Produkte die im Warenkorb sind.
	 * @param warenkorb DB-ID.
	 * @return Liste von Objekten der Klasse @Produkt = Warenkorbinhalt
	 */
	private static List<Produkt> ladeProdukteZuWarenkorbAusDB(int warenkorb) {

		Connection con = DBConnection.getConnection();
		PreparedStatement pst;
		List<Produkt> produkte = new ArrayList<Produkt>();

		try {
			pst = con.prepareStatement(WARENKORB_LADEN);
			pst.setInt(1, warenkorb);
			ResultSet set = pst.executeQuery();

			while (set.next()) {

				int produkt_id = set.getInt(2);
				int anzahl = set.getInt(3);
				Produkt produkt = ProduktOperations.produktausdbholen(produkt_id);
				produkt.setAnzahl(anzahl);
				produkt.setPreismitanzahl(produkt.getPreis() * produkt.getAnzahl());
				produkt.setPreismitanzahlineuro(produkt.getPreismitanzahl());
				produkte.add(produkt);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produkte;
	}

}
