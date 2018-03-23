package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Admin;
import de.classes.Adresse;
import de.classes.Hose;
import de.classes.Kunde;
import de.classes.Produkt;
import de.datenbank.DBConnection;

public class HoseOperations {
	private final static String HOSE_NACH_HO_ID = "SELECT * FROM hose WHERE ho_id=?;";
	private final static String HOSE_LOESCHEN = "DELETE FROM hose WHERE ho_id = ?;";

	public static Hose hoseausdbholen(Produkt produkt) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(HOSE_NACH_HO_ID);
			pst.setInt(1, produkt.getProdukt_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int ho_id = rs.getInt(1);
				int groesse = rs.getInt(2);
				Hose hose = new Hose(ho_id, produkt.getName(), produkt.getBeschreibung(), produkt.getPreis(), groesse,
						produkt.getMenge(), produkt.getArtikelnr(),produkt.getAnzahl(),produkt.getVersanddauer(),produkt.getStatus(), produkt.getImagePath());
				con.close();
				return hose;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;

	}
	
	
	public static void entferneHose(Hose hose) {
		Connection con = DBConnection.getConnection();
		 try {
			PreparedStatement pst = con.prepareStatement(HOSE_LOESCHEN);
			pst.setInt(1, hose.getProdukt_id());
			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
