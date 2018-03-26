package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import de.classes.Hose;
import de.classes.Produkt;
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
	/**
	 * Diese Methode holt ein bestimmtes Produkt aus der DB
	 * @ProduktOperations
	 * @param produkt das Produkt, was als Hose aus der Db geholt werden soll
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
						produkt.getMenge(), produkt.getArtikelnr(),produkt.getAnzahl(),produkt.getVersanddauer(),produkt.getStatus(), produkt.getImagePath());
				con.close();
				DBConnection.closeConnection();
				return hose;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		DBConnection.closeConnection();
		return null;

	}
	
	
	/**
	 * @see @ProduktOperations
	 * Diese Methode &loescht ein Produkt aus der DB.
	 * @param hose
	 */
	public static void entferneHosemitid(int id) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(HOSE_LOESCHEN);
			pst.setInt(1, id);
			pst.execute();
			con.close();
			DBConnection.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
