package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.classes.Produkt;
import de.classes.Schuhe;
import de.datenbank.DBConnection;

/**
 * 
 * Diese Klasse verwaltet ProduktOperationen
 * 
 * @see {@link package-info}
 * 
 * @author Stephen Galla
 *
 */
public class SchuheOperations {
	/**
	 * Statements
	 */
	private final static String HOLE_SCHUHE_NACH_SCHID = "SELECT * FROM schuhe WHERE sch_id = ?;";
	private final static String SCHUHE_LOESCHEN = "DELETE FROM schuhe WHERE sch_id = ?;";
	private final static String LADE_GROESSE_NACH_ART = "SELECT groesse from schuhe inner join produkt on(produkt.produkt_id = schuhe.sch_id) where artikelnr = ?;";
	/**
	 * Select Diese Methode holt ein Produkt von Typ Schuhe aus der DB.
	 * @param produkt das rauszuholende Produkt
	 * @return Instanz der Klasse @Schuhe
	 */
	public static Schuhe holeSchuheausdb(Produkt produkt) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(HOLE_SCHUHE_NACH_SCHID);
			pst.setInt(1, produkt.getProdukt_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int sch_id = rs.getInt(1);
				int groesse = rs.getInt(2);
				Schuhe schuhe = new Schuhe(sch_id, produkt.getName(), produkt.getBeschreibung(), produkt.getPreis(),
						groesse, produkt.getMenge(), produkt.getArtikelnr(), produkt.getAnzahl(),
						produkt.getVersanddauer(), produkt.getStatus(), produkt.getImagePath());
				return schuhe;

			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		return null;
	}
	/**
	 * Diese Methode laedet die Groessen anhand der Artikelnummer
	 * @param artnr Artikelnummer
	 * @return liste von Groessen
	 */
	public static List<Object> ladeSchuheGroessen(int artnr) {
		List<Object> groessen = new ArrayList<>();
		Connection con = DBConnection.getConnection();

		try {

			PreparedStatement pst = con.prepareStatement(LADE_GROESSE_NACH_ART);
			pst.setInt(1, artnr);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				groessen.add(rs.getObject(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return groessen;
	}
	
	
	/**
	 * Diese methode l&oescht ein Paar Schuh anhand der ID aus der DB.
	 * @param id
	 */
	public static void entferneSchuhemitId(int id) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(SCHUHE_LOESCHEN);
			pst.setInt(1, id);
			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
