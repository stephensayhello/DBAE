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
 * Diese Klasse verwaltet ProduktOperationen.
 * 
 * @see {@link package-infos}
 * 
 * @author Benjamin Gajewski
 *
 */
public class HoseOperations {
	private final static String HOSE_NACH_HO_ID = "SELECT * FROM hose WHERE ho_id=?;";
	private final static String HOSE_LOESCHEN = "DELETE FROM hose WHERE ho_id = ?;";
	private final static String LADE_GROESSE_NACH_ART = "select groesse from hose inner join produkt on(produkt.produkt_id = hose.ho_id) where artikelnr = ?";

	/**
	 * Diese Methode holt ein bestimmtes Produkt aus der DB
	 * 
	 * @ProduktOperations
	 * @param produkt
	 *            das Produkt, was als Hose aus der Db geholt werden soll
	 * @return Produktobjekt von Typ Hose
	 */
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
						produkt.getMenge(), produkt.getArtikelnr(), produkt.getAnzahl(), produkt.getVersanddauer(),
						produkt.getStatus(), produkt.getImagePath());
				con.close();
				
				return hose;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		DBConnection.closeConnection();
		return null;

	}

	public static List<Object> ladeHoseGroessen(int artnr) {
		List<Object> groessen = new ArrayList<>();
		Connection con = DBConnection.getConnection();

		try {

			PreparedStatement pst = con.prepareStatement(LADE_GROESSE_NACH_ART);
			pst.setInt(1, artnr);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				groessen.add(rs.getObject(1));
			}
           con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return groessen;
	}

	/**
	 * @see @ProduktOperations Diese Methode &loescht ein Produkt aus der DB.
	 * @param hose
	 */
	public static void entferneHosemitid(int id) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(HOSE_LOESCHEN);
			pst.setInt(1, id);
			pst.execute();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
