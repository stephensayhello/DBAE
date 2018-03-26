package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import de.classes.Adresse;
import de.classes.Kunde;
import de.classes.Nutzer;
import de.datenbank.DBConnection;
/**
* 
* Diese Klasse verwaltet KundenOperationen
* @see {@link package-info}
* 
* @author Benjamin Gajewski
*
*/
public class KundenOperations {
	/**
	 * Statements
	 */
	private final static String ANLEGEN_KUNDE = "INSERT INTO kunde VALUES (?,?,?,?)";
	private final static String MAIL_KUNDE_VERGLEICH = "SELECT email FROM nutzer WHERE email = ?;";
	private final static String KUNDEN_ABFRAGE = "SELECT * FROM kunde WHERE kundennr = ?;";
	private final static String KUNDE_UPDATE = "UPDATE kunde SET vorname= ?, nachname= ? WHERE kundennr = ?";
	private final static String KUNDE_LISTE ="SELECT * FROM kunde ORDER BY kundennr ASC;";
	private final static String KUNDE_LOESCHEN = "DELETE FROM kunde WHERE kundennr = ?;";
	
	
    /**
     * INSERT INTO
     * Diese Methode legt einen neuen Kunde in der DB an.
     * @param kunde die ge&aenderten Kundendaten
     */
	public static void anlegen(Kunde kunde) {

		NutzerOperations.anlegen(kunde);
		AdresseOperations.anlegen(kunde.getAdresse());

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(ANLEGEN_KUNDE);
			System.out.println(((Nutzer) kunde).getNutzer_id());
			pst.setInt(1, ((Nutzer) kunde).getNutzer_id());
			pst.setInt(2, kunde.getAdresse().getAdress_id());
			pst.setString(3, kunde.getVorname());
			pst.setString(4, kunde.getNachname());

			pst.execute();
			con.close();
			DBConnection.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Diese Methode &ueberpr&ueft ob eine Mail einen bestimmten Kunden zugeordnet werden kann
	 * @param mail pr&uefende Mail
	 * @return stimmt / stimmt nicht
	 */
	public static boolean mailIstVorhanden(String mail) {
		Connection con = DBConnection.getConnection();
		PreparedStatement prprdQuery;
		try {

			prprdQuery = con.prepareStatement(MAIL_KUNDE_VERGLEICH);
			prprdQuery.setString(1, mail);

			ResultSet rs = prprdQuery.executeQuery();

			if (rs.next()) {
				con.close();
				return true;
			} else {
				con.close();
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConnection();
		return true;

	}
	/**
	 * SELECT 
	 * Diese Methode holt einen bestimmten Kunden aus der DB raus.
	 * @param nutzer das @Nutzer Objekt anhand dessen der Kunde bestimmt wird
	 * @return der Kunde
	 */
	public static Kunde kundeausdbholen (Nutzer nutzer){

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(KUNDEN_ABFRAGE);
			pst.setInt(1, nutzer.getNutzer_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int adress_id = rs.getInt(2);
				String vorname = rs.getString(3);
				String nachname= rs.getString(4);
			    Adresse adresse = AdresseOperations.adresseAusDbHolen(adress_id);
			    Kunde kunde = new Kunde(nutzer.getNutzer_id(), nutzer.getPasswort(), nutzer.getEmail(), adresse, vorname, nachname);
			    DBConnection.closeConnection();
			    return kunde;
			}
			con.close();
			DBConnection.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return null;
		
	}
	/**
	 * UPDATE
	 * Diese Methode updatet eine bestimmten Kunden.
	 * @param kunde der ge&aenderte Kunde
	 */
	public static void kundenUpdateDaten(Kunde kunde) {
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement pst = con.prepareStatement(KUNDE_UPDATE);
			pst.setString(1, kunde.getVorname());
			pst.setString(2, kunde.getNachname());
			pst.setInt(3,kunde.getNutzer_id());
			pst.execute();
			
			con.close();
			DBConnection.closeConnection();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NutzerOperations.nutzerDataUpdate(kunde);
		Adresse adresse = kunde.getAdresse();
		adresse.setAdress_id(kunde.getNutzer_id());
		AdresseOperations.adresseDataUpdate(adresse);
	}
	
	/**
	 * DELETE 
	 * Diese methode l&oescht einen bestimmten Kunden aus der DB
	 * @param kunde
	 */
	public static void entferneKunde(Kunde kunde) {
		BewertungsOperations.entferneBewertungmitKundennr(kunde.getNutzer_id());
		Connection con = DBConnection.getConnection();
		 try {
			PreparedStatement pst = con.prepareStatement(KUNDE_LOESCHEN);
			pst.setInt(1, kunde.getNutzer_id());
			pst.execute();
			con.close();
			NutzerOperations.entferneNutzermitID(kunde.getNutzer_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 DBConnection.closeConnection();
	}
	/**
	 * Diese Methode holt alle vorhanden Kunden aus der DB.
	 * @return liste aller Kunden
	 */
	public static List<Kunde> holeAlleKunden() {
		List<Kunde> kunden  = new ArrayList<>();
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement pst = con.prepareStatement(KUNDE_LISTE);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				Nutzer nutzer = NutzerOperations.nutzerAusDbHolen(id);
				int adress_id = rs.getInt(2);
				System.out.println(id);
				System.out.println(nutzer.getEmail());
				String mail = nutzer.getEmail();
				String vorname = rs.getString(3);
				String nachname = rs.getString(4);
				Adresse adresse = AdresseOperations.adresseAusDbHolen(adress_id);
				String passwort = nutzer.getPasswort();
				Kunde kunde = new Kunde(id,passwort, mail, adresse, vorname , nachname);
				kunden.add(kunde);
				
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBConnection.closeConnection();
		return kunden;
	}
}
