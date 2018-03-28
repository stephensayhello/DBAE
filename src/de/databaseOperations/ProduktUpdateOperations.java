package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import de.classes.Hose;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.classes.Shirt;
import de.datenbank.DBConnection;

/**
 * 
 * Diese Klasse verwaltet ProduktOperationen 2 von 2
 * 
 * @see {@link package-info}
 * 
 * @author Benjamin Gajewski
 *
 */
public class ProduktUpdateOperations {

	private final static String ARTIKEL_UPDATE_ALLES = "UPDATE produkt SET name = ?, preis = ?, beschreibung = ? WHERE artikelnr =?;";
	private final static String ARTIKEL_UPDATE_NAME_UND_PREIS = "UPDATE produkt SET name = ?, preis = ? WHERE artikelnr =?;";
	private final static String ARTIKEL_UPDATE_NAME_UND_BESCHREIBUNG = "UPDATE produkt SET name = ?,  beschreibung = ? WHERE artikelnr =?;";
	private final static String ARTIKEL_UPDATE_PREIS_UND_BESCHREIBUNG = "UPDATE produkt  preis = ?, beschreibung = ? WHERE artikelnr =?;";
	private final static String ARTIKEL_UPDATE_NAME = "UPDATE produkt SET name = ? WHERE artikelnr =?;";
	private final static String ARTIKEL_UPDATE_PREIS = "UPDATE produkt SET  preis = ? WHERE artikelnr =?;";
	private final static String ARTIKEL_UPDATE_BESCHREIBUNG = "UPDATE produkt SET  beschreibung = ? WHERE artikelnr =?;";

	private final static String PRODUKT_UPDATE_ALLES = "UPDATE produkt SET menge = ?, versanddauer = ?, status = ? WHERE produkt_id =?;";
	private final static String PRODUKT_UPDATE_MENGE_VERSANDDAUER = "UPDATE produkt SET menge = ?, versanddauer = ? WHERE produkt_id =?;";
	private final static String PRODUKT_UPDATE_MENGE_STATUS = "UPDATE produkt SET menge = ?,  status = ? WHERE produkt_id =?;";
	private final static String PRODUKT_UPDATE_VERSANDDAUER_STATUS = "UPDATE produkt SET  versanddauer = ?, status = ? WHERE produkt_id =?;";
	private final static String PRODUKT_UPDATE_MENGE = "UPDATE produkt SET menge = ? WHERE produkt_id =?;";
	private final static String PRODUKT_UPDATE_VERSANDDAUER = "UPDATE produkt SET  versanddauer = ? WHERE produkt_id =?;";
	private final static String PRODUKT_UPDATE_STATUS = "UPDATE produkt SET status = ? WHERE produkt_id =?;";

	private final static String PRODUKT_LOESCHEN = "DELETE FROM produkt WHERE produkt_id = ?";

	/**
	 * Diese Methode entfernt ein Produkt aus der DB.
	 * 
	 * @param produkt
	 *            das zu L&oeschende Produkt
	 */
	public static boolean entferneProdukt(Produkt produkt) {
		int zahl = ProduktOperations.zaehleArtikelnummern(produkt.getArtikelnr());
		if (zahl > 1) {
			BestellungUpdateOperations.entferneBestellungProdzuordnungmitID(produkt.getProdukt_id());
			if (produkt instanceof Hose) {
				HoseOperations.entferneHosemitid(produkt.getProdukt_id());

			} else if (produkt instanceof Schuhe) {
				SchuheOperations.entferneSchuhemitId(produkt.getProdukt_id());
			} else if (produkt instanceof Shirt) {
				ShirtOperations.entferneShirtmitId(produkt.getProdukt_id());
			}

			Connection con = DBConnection.getConnection();

			try {
				PreparedStatement pst = con.prepareStatement(PRODUKT_LOESCHEN);
				pst.setInt(1, produkt.getProdukt_id());
				pst.execute();
				con.close();

			} catch (SQLException e) {

				e.printStackTrace();
				return false;
			}
			return true;
		} else {
			return false;
		}

	}
	/**
	 *
	 * Diese Methode l&oescht ein Produkt.
	 * @param produkt eine Instanz der Klasse @produkt
	 */
	public static void entferneProduktvollstaendig(Produkt produkt) {
		System.out.println(produkt.getArtikelnr());
		BewertungsOperations.entferneBewertungmitArtikelnr(produkt.getArtikelnr());

		BestellungUpdateOperations.entferneBestellungProdzuordnungmitID(produkt.getProdukt_id());

		if (produkt instanceof Hose) {
			HoseOperations.entferneHosemitid(produkt.getProdukt_id());

		} else if (produkt instanceof Schuhe) {
			SchuheOperations.entferneSchuhemitId(produkt.getProdukt_id());
		} else if (produkt instanceof Shirt) {
			ShirtOperations.entferneShirtmitId(produkt.getProdukt_id());
		}

		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_LOESCHEN);
			pst.setInt(1, produkt.getProdukt_id());
			pst.execute();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param id
	 *            DB-Id
	 * @param menge
	 *            Anzahl auf Lager
	 * @param versanddauer
	 *            in Tagen
	 * @param status
	 *            Lieferbarkeit des Produktes
	 */
	public static void updateProdukt(int id, int menge, int versanddauer, String status) {
		if (versanddauer != -1 && menge != -1 && status.contains("nix")) {
			updateProduktVersanddauerundMenge(id, menge, versanddauer);
		} else if (versanddauer == -1 && menge != -1 && !status.contains("nix")) {
			updateProduktmengeundstatus(id, menge, status);
		} else if (versanddauer != -1 && menge == -1 && !status.contains("nix")) {
			updateProduktVersanddauerundStatus(id, versanddauer, status);
		} else if (versanddauer == -1 && menge != -1 && status.contains("nix")) {
			updateProduktMenge(id, menge);
		} else if (versanddauer != -1 && menge == -1 && status.contains("nix")) {
			updateProduktVersanddauer(id, versanddauer);
		} else if (versanddauer == -1 && menge == -1 && !status.contains("nix")) {
			updateProduktStatus(id, status);
		} else if (versanddauer != -1 && menge != -1 && !status.contains("nix")) {

			Connection con = DBConnection.getConnection();
			try {
				PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE_ALLES);

				pst.setInt(1, menge);
				pst.setInt(2, versanddauer);
				pst.setString(3, status);
				pst.setInt(4, id);

				pst.execute();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param id
	 *            DB-Id
	 * @param menge
	 *            Anzahl auf Lager
	 * @param versanddauer
	 *            in Tagen
	 */
	public static void updateProduktVersanddauerundMenge(int id, int menge, int versanddauer) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE_MENGE_VERSANDDAUER);

			pst.setInt(1, menge);
			pst.setInt(2, versanddauer);

			pst.setInt(3, id);

			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param id
	 *            DB-Id
	 * @param menge
	 *            Anzahl auf Lager
	 * @param status
	 *            Lieferbarkeit des Produktes
	 */
	public static void updateProduktmengeundstatus(int id, int menge, String status) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE_MENGE_STATUS);

			pst.setInt(1, menge);
			pst.setString(2, status);

			pst.setInt(3, id);

			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param id
	 *            DB-Id
	 * @param versanddauer
	 *            in Tagen
	 * @param status
	 *            Lieferbarkeit
	 */
	public static void updateProduktVersanddauerundStatus(int id, int versanddauer, String status) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE_VERSANDDAUER_STATUS);

			pst.setInt(1, versanddauer);

			pst.setString(2, status);
			pst.setInt(3, id);

			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param id
	 *            DB-Id
	 * @param menge
	 *            Anzahl auf Lager
	 * 
	 */
	public static void updateProduktMenge(int id, int menge) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE_MENGE);

			pst.setInt(1, menge);

			pst.setInt(2, id);

			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param id
	 *            DB-Id
	 * @param versanddauer
	 *            in Tagen
	 */
	public static void updateProduktVersanddauer(int id, int versanddauer) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE_VERSANDDAUER);

			pst.setInt(1, versanddauer);

			pst.setInt(2, id);

			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param id
	 *            DB-Id
	 * @param status
	 *            lieferbarkeit des produktes
	 */
	public static void updateProduktStatus(int id, String status) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE_STATUS);

			pst.setString(1, status);
			pst.setInt(2, id);

			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param artikelnr
	 *            DB-Id
	 * @param name
	 *            produktbezeichnung
	 * @param preis
	 *            der Preis incl. Ust.
	 * @param beschreibung
	 *            eine Prodktbeschreibung
	 */
	public static void updateProduktgruppemitArtikelnr(int artikelnr, String name, double preis, String beschreibung) {
		if (!name.equals("") && preis != 0 && beschreibung.equals("")) {

			updateProduktgruppemitArtikelnrNameundPreis(artikelnr, name, preis);

		} else if (!name.equals("") && preis == 0 && !beschreibung.equals("")) {
			updateProduktgruppemitArtikelnrNameundBeschreibung(artikelnr, name, beschreibung);
		} else if (name.equals("") && preis != 0 && !beschreibung.equals("")) {
			updateProduktgruppemitArtikelnrPreisundBeschreibung(artikelnr, preis, beschreibung);
		} else if (!name.equals("") && preis == 0 && beschreibung.equals("")) {
			updateProduktgruppemitArtikelnrName(artikelnr, name);

		} else if (name.equals("") && preis != 0 && beschreibung.equals("")) {
			updateProduktgruppemitArtikelnrPreis(artikelnr, preis);
		} else if (name.equals("") && preis == 0 && !beschreibung.equals("")) {
			updateProduktgruppemitArtikelnrBeschreibung(artikelnr, beschreibung);
		} else if (!name.equals("") && preis != 0 && !beschreibung.equals("")) {

			Connection con = DBConnection.getConnection();
			try {
				PreparedStatement pst = con.prepareStatement(ARTIKEL_UPDATE_ALLES);

				pst.setString(1, name);
				pst.setDouble(2, preis);
				pst.setString(3, beschreibung);
				pst.setInt(4, artikelnr);

				pst.execute();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param artikelnr
	 *            DB-Id
	 * @param name
	 *            Produktbezeichnung
	 * @param preis
	 *            der Preis incl. Ust.
	 */
	public static void updateProduktgruppemitArtikelnrNameundPreis(int artikelnr, String name, double preis) {

		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(ARTIKEL_UPDATE_NAME_UND_PREIS);

			pst.setString(1, name);
			pst.setDouble(2, preis);

			pst.setInt(3, artikelnr);

			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param artikelnr
	 *            DB-Id
	 * @param name
	 *            Produktbezeichnung
	 * @param beschreibung
	 *            die Produktbeschreibung
	 */
	public static void updateProduktgruppemitArtikelnrNameundBeschreibung(int artikelnr, String name,
			String beschreibung) {

		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(ARTIKEL_UPDATE_NAME_UND_BESCHREIBUNG);

			pst.setString(1, name);
			pst.setString(2, beschreibung);

			pst.setInt(3, artikelnr);

			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param artikelnr
	 *            DB-Id
	 * @param preis
	 *            der Preis incl. Ust.
	 * @param beschreibung
	 *            eine beschreibung des Produktes
	 */
	public static void updateProduktgruppemitArtikelnrPreisundBeschreibung(int artikelnr, double preis,
			String beschreibung) {

		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(ARTIKEL_UPDATE_PREIS_UND_BESCHREIBUNG);

			pst.setDouble(1, preis);
			pst.setString(2, beschreibung);

			pst.setInt(3, artikelnr);

			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param artikelnr
	 *            DB-Id
	 * @param name
	 *            Produktbezeichnung
	 * 
	 */
	public static void updateProduktgruppemitArtikelnrName(int artikelnr, String name) {

		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(ARTIKEL_UPDATE_NAME);

			pst.setString(1, name);

			pst.setInt(2, artikelnr);

			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param artikelnr
	 *            DB-Id
	 * @param preis
	 *            der Preis incl. Ust.
	 */
	public static void updateProduktgruppemitArtikelnrPreis(int artikelnr, double preis) {

		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(ARTIKEL_UPDATE_PREIS);

			pst.setDouble(1, preis);

			pst.setInt(2, artikelnr);

			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Diese Methode updatet eine Instanz @Produkt in der DB
	 * 
	 * @param artikelnr
	 *            DB-Id
	 * 
	 * @param beschreibung
	 *            eine Produktbeschreibung
	 */
	public static void updateProduktgruppemitArtikelnrBeschreibung(int artikelnr, String beschreibung) {

		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(ARTIKEL_UPDATE_BESCHREIBUNG);

			pst.setString(1, beschreibung);

			pst.setInt(2, artikelnr);

			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
