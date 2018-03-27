package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	private final static String LADE_GROESSE_NACH_ART = "select groesse from shirt inner join produkt on(produkt.produkt_id = shirt.sh_id) where artikelnr = ?";
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
	
	

	public static List<Object> ladeShirtGroessen(int artnr) {
		List<Object> groessen = new ArrayList<>();
		Connection con = DBConnection.getConnection();

		try {

			PreparedStatement pst = con.prepareStatement(LADE_GROESSE_NACH_ART);
			pst.setInt(1, artnr);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				groessen.add(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return groessen;
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
