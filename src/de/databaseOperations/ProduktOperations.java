package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.ResultSetMetaData;

import java.sql.SQLException;

import de.classes.Hose;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.datenbank.DBConnection;

public class ProduktOperations {

	public final static String ANLEGEN_PRODUKT = "INSERT INTO produkt VALUES (?,?,?,?,?)";

	public final static String ANLEGEN_SCHUHE = "INSERT INTO schuhe VALUES (?,?)";
	public final static String ANLEGEN_HOSE = "INSERT INTO hose VALUES (?,?)";
	public final static String ANLEGEN_SHIRT = "INSERT INTO shirt VALUES (?,?)";

	public final static String PRODUKT_ZEIGEN = "SELECT * FROM produkt WHERE  name = ?";

	public static void anlegen(Produkt produkt) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ANLEGEN_PRODUKT);
			pst.setDouble(1, produkt.getPreis());
			pst.setInt(2, produkt.getMenge());
			pst.setString(3, produkt.getName());
			pst.setString(4, produkt.getBeschreibung());
			pst.setInt(5, produkt.getID());

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
			pst.setInt(1, ((Produkt) schuhe).getID());
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
			pst.setInt(1, ((Produkt) hose).getID());
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
			pst.setInt(1, ((Produkt) shirt).getID());
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
	public Produkt zeigeProdukt(Produkt produkt) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_ZEIGEN);
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

}
