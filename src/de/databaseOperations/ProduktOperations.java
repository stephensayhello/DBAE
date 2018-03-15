package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.ResultSetMetaData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.classes.Hose;
import de.classes.Nutzer;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.datenbank.DBConnection;

public class ProduktOperations {

	public final static String ANLEGEN_PRODUKT = "INSERT INTO produkt VALUES (?,?,?,?,?)";

	public final static String ANLEGEN_SCHUHE = "INSERT INTO schuhe VALUES (?,?)";
	public final static String ANLEGEN_HOSE = "INSERT INTO hose VALUES (?,?)";
	public final static String ANLEGEN_SHIRT = "INSERT INTO shirt VALUES (?,?)";
	public final static String LADE_PRODUKTE = "SELECT * FROM produkt;";

	public final static String PRODUKT_ZEIGEN_NACH_NAME = "SELECT * FROM produkt WHERE  name = ?;";
	public final static String PRODUKT_ZEIGEN_NACH_ID = "SELECT * FROM produkt WHERE  produkt_id = ?;";

	public final static String PRODUKT_ANZAHL = "SELECT COUNT(*) FROM ";
	public final static String ZEIGE_SCHUHE_NACH_PRODUKTID = "SELECT sch_id FROM schuhe WHERE sch_id = ?;";
	public final static String ZEIGE_SHIRT_NACH_PRODUKTID = "SELECT sh_id FROM shirt WHERE sh_id = ?;";
	public final static String ZEIGE_HOSE_NACH_PRODUKTID = "SELECT ho_id FROM hose WHERE ho_id = ?;";

	public static void anlegen(Produkt produkt) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ANLEGEN_PRODUKT);
			pst.setDouble(1, produkt.getPreis());
			pst.setInt(2, produkt.getMenge());
			pst.setString(3, produkt.getName());
			pst.setString(4, produkt.getBeschreibung());
			pst.setInt(5, produkt.getProdukt_id());

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

	public static int hoechsteID() {
		Connection con = DBConnection.getConnection();
		int id = 0;
		try {
			PreparedStatement pst = con.prepareStatement("SELECT MAX(produkt_id) FROM produkt");

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

	/**
	 * 
	 * Diese Methode liefert zu einem Produkt Daten aus der DB dazu.
	 * 
	 * @param produkt
	 * @return
	 */
	public static Produkt zeigeProdukt(Produkt produkt) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_ZEIGEN_NACH_NAME);
			pst.setString(1, produkt.getName());

			ResultSet rs = pst.executeQuery();
			rs.next();

			if (rs.next()) {
				produkt.setPreis(rs.getDouble(1));
				produkt.setMenge(rs.getInt(2));
				produkt.setBeschreibung(rs.getString(1));

			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produkt;
	}

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
				Produkt produkt = new Produkt(id, name, beschreibung, preis, menge);

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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produkte;
	}

	public static boolean produktistShirt(int id) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ZEIGE_SHIRT_NACH_PRODUKTID);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int sh_id = rs.getInt(1);

				System.out.println(sh_id);
				System.out.println();

				return true;
			}

		} catch (

		SQLException e) {
			System.out.println("Fehler");
			e.printStackTrace();

		}
		return false;

	}

	public static boolean produktistHose(int id) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ZEIGE_HOSE_NACH_PRODUKTID);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int ho_id = rs.getInt(1);

				System.out.println(ho_id);
				System.out.println();

				return true;
			}

		} catch (

		SQLException e) {
			System.out.println("Fehler");
			e.printStackTrace();

		}
		return false;

	}

	public static boolean produktistSchuhe(int id) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ZEIGE_SCHUHE_NACH_PRODUKTID);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int sch_id = rs.getInt(1);

				System.out.println(sch_id);
				System.out.println();

				return true;
			}

		} catch (

		SQLException e) {
			System.out.println("Fehler");
			e.printStackTrace();

		}
		return false;

	}

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
				String beschreibung=rs.getString(4);
				int produkt_id = rs.getInt(5);
				
            Produkt produkt = new Produkt(produkt_id, name, beschreibung, preis, menge);
				return produkt;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;

	}

}
