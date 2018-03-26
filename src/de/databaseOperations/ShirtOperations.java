package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import de.classes.Produkt;
import de.classes.Shirt;
import de.datenbank.DBConnection;
/**
* 
* Diese Klasse verwaltet ProduktOperationen
* @see @package-info
* 
* @author Stephen Galla
*
*/
public class ShirtOperations {
	/**
	 * Statements
	 */
	private final static String HOLE_SHIRT_NACH_SHID = "SELECT * FROM shirt WHERE sh_id = ?;";
	private final static String SHIRT_LOESCHEN = "DELETE FROM shirt WHERE sch_id= ?;";
	/**
	 * SELECT
	 * Diese Methode holtb ein Produkt aus der DB
	 * @param produkt
	 * @return ein Objekt der Klasse Shirts.
	 */
	public static Shirt holeShirtausdb(Produkt produkt) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(HOLE_SHIRT_NACH_SHID);
			pst.setInt(1, produkt.getProdukt_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int sh_id = rs.getInt(1);
				String groesse = rs.getString(2);
				Shirt shirt = new Shirt(sh_id, produkt.getName(), produkt.getBeschreibung(), produkt.getPreis(),
						groesse, produkt.getMenge(), produkt.getArtikelnr(),produkt.getAnzahl(), produkt.getVersanddauer(),produkt.getStatus(), produkt.getImagePath());

				return shirt;

			}
			con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	
		return null;
	}
	
	/**
	 * Diese Methode l&oescht ein Shirt anhand der ID aus der DB
	 * @param id DB-id
	 */
	public static void entferneShirtmitId(int id) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(SHIRT_LOESCHEN);
			pst.setInt(1, id);
			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
