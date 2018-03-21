package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.classes.Produkt;
import de.datenbank.DBConnection;

public class ProduktUpdateOperations {

	private final static String PRODUKTGRUPPE_UPDATE = "UPDATE produkt SET name = ?, menge = ?, preis = ? WHERE produkt_id =?;";
	private final static String PRODUKT_UPDATE = "UPDATE produkt SET name = ?, menge = ?, preis = ? WHERE artikelnr =?;";
	private final static String PRODUKT_LOESCHEN = "DELETE FROM produkt WHERE produkt_id = ?";
	private final static String ARTIKEL_LOESCHEN = "DELETE FROM produkt WHERE artikel_id = ?";

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
	
	public static void entferneArtikel(Produkt produkt) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_LOESCHEN);
			pst.setInt(1, produkt.getArtikelnr());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
