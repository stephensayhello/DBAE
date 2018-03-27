package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import de.classes.Bestellung;
import de.classes.Kunde;
import de.datenbank.DBConnection;

/**
 * Diese Klasse aktualisiert eine bestimmte Bestellung.
 * 
 * @author Paul Blanke
 *
 */
public class BestellungUpdateOperations {
	/**
	 * Statement
	 */
	private final static String UPDATE_BEARBEITUNGSSTATUS = "UPDATE bestellung SET status = ? WHERE bstnr =?;";
	private final static String BESTELLUNG_LOESCHEN = "DELETE FROM bestellung WHERE kundennr=?;";
	private final static String BESTELLUNG_PRODUKTZUORDNUNG_LOESCHEN = "DELETE FROM bestellung_produktzuordnung WHERE bstnr=?;";
	

	/**
	 * Diese Methode updatet eine Bestellung und ver&andert den
	 * Bestellungsstatus
	 * 
	 * @param bstnr
	 * @param status
	 */
	public static void updateBestellungBearbeitungsstatus(int bstnr, String status) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(UPDATE_BEARBEITUNGSSTATUS);

			pst.setString(1, status);
			pst.setInt(2, bstnr);

			pst.execute();
			con.close();
			DBConnection.closeConnection();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void entferneBestellungmitKundennr(int id) {
		
		List<Bestellung> bestellungen = BestellungOperations.bestelldatumundBstnrMitKnrAusDbholen(id);
		
		for (Bestellung bestellung : bestellungen) {
			entferneBestellungProduktzuo(bestellung.getBestellnummer());
		}
		
		
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(BESTELLUNG_LOESCHEN);
			pst.setInt(1, id);
			pst.execute();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	
	}
	
	public static void entferneBestellungProduktzuo (int bstnr) {

		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(BESTELLUNG_PRODUKTZUORDNUNG_LOESCHEN);
			pst.setInt(1, bstnr);
			pst.execute();
			con.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		
	}

}
