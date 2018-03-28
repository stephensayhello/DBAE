package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import de.classes.Hose;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.datenbank.DBConnection;
/**
* 
* Diese Klasse verwaltet ProduktOperationen 1 von 2.
* @see {@link package-info}
* 
* @author Benjamin Gajewski
*
*/
public class ProduktOperations {
	
	/**
	 * Statements
	 */
	private final static String ANLEGEN_PRODUKT = "INSERT INTO produkt VALUES (?,?,?,?,?,?,?,?,?)";
	private final static String ANLEGEN_SCHUHE = "INSERT INTO schuhe VALUES (?,?)";
	private final static String ANLEGEN_HOSE = "INSERT INTO hose VALUES (?,?)";
	private final static String ANLEGEN_SHIRT = "INSERT INTO shirt VALUES (?,?)";
	private final static String LADE_PRODUKTE = "SELECT * FROM produkt ORDER BY produkt_id ASC;";
	private final static String PRODUKT_ZEIGEN_NACH_ID = "SELECT * FROM produkt WHERE  produkt_id = ?;";
	private final static String ZEIGE_SCHUHE_NACH_PRODUKTID = "SELECT sch_id FROM schuhe WHERE sch_id = ?;";
	private final static String ZEIGE_SHIRT_NACH_PRODUKTID = "SELECT sh_id FROM shirt WHERE sh_id = ?;";
	private final static String ZEIGE_HOSE_NACH_PRODUKTID = "SELECT ho_id FROM hose WHERE ho_id = ?;";
	private final static String MAX_ARTNR = "SELECT MAX(artikelnr) FROM produkt;";
	private final static String ZEIGE_PRODUKT_NACH_ARTNR = "SELECT * FROM produkt WHERE artikelnr =?;";
	

	

	/**
	 * Diese Methode holt ein bestimmtes Produkt aus der DB.
	 * @param artnr DB-ID.
	 * @return Instanz der Klasse @Produkt
	 */
	public static Produkt ladeProduktausdb(int artnr) {

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ZEIGE_PRODUKT_NACH_ARTNR);
			pst.setInt(1, artnr);
			ResultSet rs = pst.executeQuery();
			rs.next();

			double preis = rs.getDouble(1);
			int menge = rs.getInt(2);
			String name = rs.getString(3);
			String beschreibung = rs.getString(4);
			int id = rs.getInt(5);
			int artikelnr = rs.getInt(6);
			String status = rs.getString(8);
			String pfad = rs.getString(9);
			Produkt produkt = new Produkt(id, name, beschreibung, preis, menge, artnr, artikelnr,status);
			produkt.setImagePath(pfad);
			
			if (produktistSchuhe(produkt.getProdukt_id())) {
				Schuhe schuhe = SchuheOperations.holeSchuheausdb(produkt);
				
				return schuhe;
				
			} else if (produktistHose(produkt.getProdukt_id())) {
				Hose hose = HoseOperations.hoseausdbholen(produkt);
				
				return hose;
				
			} else if (produktistShirt(produkt.getProdukt_id())) {
				Shirt shirt = ShirtOperations.holeShirtausdb(produkt);
				
				return shirt;
			}
			con.close();
			DBConnection.closeConnection();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		DBConnection.closeConnection();
		return null;

	}
	/**
	 * SELECT
	 * Diese Methode ermittelt die Höchste Artikelnummer aus der Db und erhöht sie um eins.
	 * @return neue DB-iD
	 */
	public static int hoechsteartikelnr() {
		Connection con = DBConnection.getConnection();
		int id = 0;
		try {
			PreparedStatement pst = con.prepareStatement(MAX_ARTNR);

			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		id++;
		DBConnection.closeConnection();
		return id;
	}
	
	/**
	 * Diese Methode speichert eine neues Produkt in der DB.
	 * @param produkt das neue objekt der Klasse @Produkt
	 */
	public static void anlegen(Produkt produkt) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ANLEGEN_PRODUKT);
			pst.setDouble(1, produkt.getPreis());
			pst.setInt(2, produkt.getMenge());
			pst.setString(3, produkt.getName());
			pst.setString(4, produkt.getBeschreibung());
			pst.setInt(5, produkt.getProdukt_id());
			pst.setInt(6, produkt.getArtikelnr());
			pst.setInt(7, produkt.getVersanddauer());
			pst.setString(8, produkt.getStatus());
			pst.setString(9, produkt.getImagePath());
			pst.execute();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (produkt instanceof Shirt) {
			anlegenShirt((Shirt) produkt);
		} else if (produkt instanceof Hose) {
			anlegenHose((Hose) produkt);
		} else if (produkt instanceof Schuhe) {
			anlegenSchuhe((Schuhe) produkt);
		}

	}
	/**
	 * Diese Methode speichert ein Produkt der Klasse Schuhe in der DB.
	 * @param schuhe eine Instanz von @Schuhe
	 */
	private static void anlegenSchuhe(Schuhe schuhe) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ANLEGEN_SCHUHE);
			pst.setInt(1, ((Produkt) schuhe).getProdukt_id());
			pst.setInt(2, schuhe.getGroesse());

			pst.execute();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Diese Methode speichert ein Produkt der Klasse Hose in der DB.
	 * @param hose eine Instanz von @Hose
	 */
	private static void anlegenHose(Hose hose) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ANLEGEN_HOSE);
			pst.setInt(1, ((Produkt) hose).getProdukt_id());
			pst.setInt(2, hose.getGroesse());

			pst.execute();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Diese Methode speichert ein Produkt der Klasse Shirt in der DB.
	 * @param shirt eine Instanz von @Shirt
	 */
	private static void anlegenShirt(Shirt shirt) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ANLEGEN_SHIRT);
			pst.setInt(1, ((Produkt) shirt).getProdukt_id());
			pst.setString(2, shirt.getGroesse());
			pst.execute();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Diese Methode liefert eine neue DB-Id.
	 * @return neue DB-Id
	 */
	public static int hoechsteID() {
		Connection con = DBConnection.getConnection();
		int id = 0;
		try {
			PreparedStatement pst = con.prepareStatement("SELECT MAX(produkt_id) FROM produkt");

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
	 * Diese Methode liefert eine Liste aller Produkte aus der DB.
	 * @return alle produkte aus der DB.
	 */
	public static List<Produkt> ladeProdukteAusDatenbank() {

		Connection con = DBConnection.getConnection();
		List<Produkt> produkte = new ArrayList<Produkt>();

		try {
			PreparedStatement pst = con.prepareStatement(LADE_PRODUKTE);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				double preis = rs.getDouble(1);
				int menge = rs.getInt(2);
				String name = rs.getString(3);
				String beschreibung = rs.getString(4);
				int id = rs.getInt(5);
				int artnr = rs.getInt(6);
				int versanddauer = rs.getInt(7);
				String status = rs.getString(8);
				String pfad = rs.getString(9);
				Produkt produkt = new Produkt(id, name, beschreibung, preis, menge, artnr, versanddauer, status);
				produkt.setImagePath(pfad);
				if (produktistSchuhe(produkt.getProdukt_id())) {
					Schuhe schuhe = SchuheOperations.holeSchuheausdb(produkt);
					produkte.add(schuhe);
				}
				if (produktistHose(produkt.getProdukt_id())) {
					Hose hose = HoseOperations.hoseausdbholen(produkt);
					produkte.add(hose);
				}
				if (produktistShirt(produkt.getProdukt_id())) {
					Shirt shirt = ShirtOperations.holeShirtausdb(produkt);
					produkte.add(shirt);
				}

			}
			con.close();
			DBConnection.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produkte;
	}
	/**
	 * Diese methode liefert alle Produkt einer Produktgruppe aus der DB.
	 * @param artikelnr DB-ID
	 * @return Liste aller @Produkte aus der DB, die zu einer best. Gruppe
	 * geh&oeren
	 */
	public static List<Produkt> ladeProdukteAusDatenbankmitArtnr(int artikelnr) {
		
		Connection con = DBConnection.getConnection();
		List<Produkt> produkte = new ArrayList<Produkt>();
		
		try {
			PreparedStatement pst = con.prepareStatement(ZEIGE_PRODUKT_NACH_ARTNR);
			pst.setInt(1, artikelnr);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				double preis = rs.getDouble(1);
				int menge = rs.getInt(2);
				String name = rs.getString(3);
				String beschreibung = rs.getString(4);
				int id = rs.getInt(5);
				int artnr = rs.getInt(6);
				int versanddauer = rs.getInt(7);
				String status = rs.getString(8);
				String pfad = rs.getString(9);
				Produkt produkt = new Produkt(id, name, beschreibung, preis, menge, artnr, versanddauer, status);
				produkt.setImagePath(pfad);
				if (produktistSchuhe(produkt.getProdukt_id())) {
					Schuhe schuhe = SchuheOperations.holeSchuheausdb(produkt);
					produkte.add(schuhe);
				}
				else if (produktistHose(produkt.getProdukt_id())) {
					Hose hose = HoseOperations.hoseausdbholen(produkt);
					produkte.add(hose);
				}
				else if (produktistShirt(produkt.getProdukt_id())) {
					Shirt shirt = ShirtOperations.holeShirtausdb(produkt);
					produkte.add(shirt);
				}
				
			}
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConnection();
		return produkte;
	}
	/**
	 * Diese Methode pr&uumlft ob ein produkt zu der Klasse Shirts geh&oert.
	 * @param id DB-ID.
	 * @return ist / ist nicht eine Instanz der Klasse @Shirt
	 */
	public static boolean produktistShirt(int id) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ZEIGE_SHIRT_NACH_PRODUKTID);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int sh_id = rs.getInt(1);
				con.close();
				return true;
			}

		} catch (

		SQLException e) {
		
			e.printStackTrace();

		}
		return false;

	}
	/**
	 * Diese Methode pr&ueft ob ein produkt zu der Klasse Hose geh&oert.
	 * @param id DB-ID.
	 * @return ist / ist nicht eine Instanz der Klasse @Hose
	 */
	public static boolean produktistHose(int id) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ZEIGE_HOSE_NACH_PRODUKTID);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int ho_id = rs.getInt(1);
				con.close();
				return true;
			}

		} catch (

		SQLException e) {
		
			e.printStackTrace();

		}
		DBConnection.closeConnection();
		return false;

	}
	/**
	 * Diese Methode pr&ueft ob ein produkt zu der Klasse Schuhe geh&oert.
	 * @param id DB-ID.
	 * @return ist / ist nicht eine Instanz der Klasse @Schuhe
	 */
	public static boolean produktistSchuhe(int id) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ZEIGE_SCHUHE_NACH_PRODUKTID);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int sch_id = rs.getInt(1);
				con.close();
				return true;
			}

		} catch (

		SQLException e) {
		
			e.printStackTrace();

		}
		DBConnection.closeConnection();
		return false;

	}
/**
 * Diese Methode holt ein produkt anhand der ID aus der DB.
 * @param id DB-ID
 * @return Instanz der Klasse @Produkt
 */
	public static Produkt produktausdbholen(int id) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_ZEIGEN_NACH_ID);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				double preis = rs.getDouble(1);
				int menge = rs.getInt(2);
				String name = rs.getString(3);
				String beschreibung = rs.getString(4);
				int produkt_id = rs.getInt(5);

				int artnr = rs.getInt(6);
				int versanddauer = rs.getInt(7);
				String status = rs.getString(8);
				String pfad = rs.getString(9);

				Produkt produkt = new Produkt(produkt_id, name, beschreibung, preis, menge, artnr, versanddauer, status);
				produkt.setImagePath(pfad);
				if (produktistSchuhe(produkt.getProdukt_id())) {
					return SchuheOperations.holeSchuheausdb(produkt);
				}
				if (produktistHose(produkt.getProdukt_id())) {
					return HoseOperations.hoseausdbholen(produkt);
				}
				if (produktistShirt(produkt.getProdukt_id())) {
					return ShirtOperations.holeShirtausdb(produkt);
				}
				con.close();
				
				return produkt;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		DBConnection.closeConnection();
		return null;

	}
}
