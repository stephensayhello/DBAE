package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.ResultSetMetaData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import de.classes.Admin;
import de.classes.Hose;
import de.classes.Nutzer;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.datenbank.DBConnection;

public class ProduktOperations {

	private final static String ANLEGEN_PRODUKT = "INSERT INTO produkt VALUES (?,?,?,?,?,?,?,?,?)";

	private final static String ANLEGEN_SCHUHE = "INSERT INTO schuhe VALUES (?,?)";
	private final static String ANLEGEN_HOSE = "INSERT INTO hose VALUES (?,?)";
	private final static String ANLEGEN_SHIRT = "INSERT INTO shirt VALUES (?,?)";
	private final static String LADE_PRODUKTE = "SELECT * FROM produkt;";

	private final static String PRODUKT_ZEIGEN_NACH_NAME = "SELECT * FROM produkt WHERE  name = ?;";
	private final static String PRODUKT_ZEIGEN_NACH_ID = "SELECT * FROM produkt WHERE  produkt_id = ?;";

	private final static String PRODUKT_ANZAHL = "SELECT COUNT(*) FROM ";
	private final static String ZEIGE_SCHUHE_NACH_PRODUKTID = "SELECT sch_id FROM schuhe WHERE sch_id = ?;";
	private final static String ZEIGE_SHIRT_NACH_PRODUKTID = "SELECT sh_id FROM shirt WHERE sh_id = ?;";
	private final static String ZEIGE_HOSE_NACH_PRODUKTID = "SELECT ho_id FROM hose WHERE ho_id = ?;";
	private final static String MAX_ARTNR = "SELECT MAX(artikelnr) FROM produkt;";
	private final static String ZEIGE_PRODUKT_NACH_ARTNR = "SELECT * FROM produkt WHERE artikelnr =?;";

	private final static String PRODUKT_UPDATE = "UPDATE produkt SET name = ?, menge = ?, preis = ? WHERE produkt_id=?;";

	private final static String PRODUKT_LOESCHEN = "DELETE FROM produkt WHERE produkt_id = ?";

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
			}

			if (produktistHose(produkt.getProdukt_id())) {
				Hose hose = HoseOperations.hoseausdbholen(produkt);
				return hose;
			}

			if (produktistShirt(produkt.getProdukt_id())) {
				Shirt shirt = ShirtOperations.holeShirtausdb(produkt);
				return shirt;
			}
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;

	}

	public static int hoechsteartikelnr() {
		Connection con = DBConnection.getConnection();
		int id = 0;
		try {
			PreparedStatement pst = con.prepareStatement(MAX_ARTNR);

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

				return produkt;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;

	}

	public static void entferneProdukt(Produkt produkt) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_LOESCHEN);
			pst.setInt(1, produkt.getProdukt_id());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateProdukt(Produkt produkt) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE);
			pst.setString(1, produkt.getName());
			pst.setInt(2, produkt.getMenge());
			pst.setDouble(3, produkt.getPreis());
			pst.setInt(4, produkt.getProdukt_id());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
