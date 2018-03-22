package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Produkt;
import de.datenbank.DBConnection;

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
	private final static String ARTIKEL_LOESCHEN = "DELETE FROM produkt WHERE artikelnr = ?";
	
	private final static String PRODUKTID_DURCH_ARTIKELNUMMER ="SELECT produkt_id WHERE artikelnr =?;";

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
			PreparedStatement pst = con.prepareStatement(ARTIKEL_LOESCHEN);
			pst.setInt(1, produkt.getArtikelnr());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void updateProduktVersanddauerundMenge(int id, int menge, int versanddauer) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE_MENGE_VERSANDDAUER);

			pst.setInt(1, menge);
			pst.setInt(2, versanddauer);

			pst.setInt(3, id);

			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateProduktmengeundstatus(int id, int menge, String status) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE_MENGE_STATUS);

			pst.setInt(1, menge);
			pst.setString(2, status);

			pst.setInt(3, id);

			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateProduktVersanddauerundStatus(int id, int versanddauer, String status) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE_VERSANDDAUER_STATUS);

			pst.setInt(1, versanddauer);

			pst.setString(2, status);
			pst.setInt(3, id);

			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateProduktMenge(int id, int menge) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE_MENGE);

			pst.setInt(1, menge);
			;
			pst.setInt(2, id);

			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateProduktVersanddauer(int id, int versanddauer) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE_VERSANDDAUER);

			pst.setInt(1, versanddauer);

			pst.setInt(2, id);

			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateProduktStatus(int id, String status) {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKT_UPDATE_STATUS);

			pst.setString(1, status);
			pst.setInt(2, id);

			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void updateProduktgruppemitArtikelnrNameundPreis(int artikelnr, String name, double preis) {

		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(ARTIKEL_UPDATE_NAME_UND_PREIS);

			pst.setString(1, name);
			pst.setDouble(2, preis);

			pst.setInt(3, artikelnr);

			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateProduktgruppemitArtikelnrNameundBeschreibung(int artikelnr, String name,
			String beschreibung) {

		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(ARTIKEL_UPDATE_NAME_UND_BESCHREIBUNG);

			pst.setString(1, name);
			pst.setString(2, beschreibung);

			pst.setInt(3, artikelnr);

			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateProduktgruppemitArtikelnrPreisundBeschreibung(int artikelnr, double preis,
			String beschreibung) {

		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(ARTIKEL_UPDATE_PREIS_UND_BESCHREIBUNG);

			pst.setDouble(1, preis);
			pst.setString(2, beschreibung);

			pst.setInt(3, artikelnr);

			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateProduktgruppemitArtikelnrName(int artikelnr, String name) {

		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(ARTIKEL_UPDATE_NAME);

			pst.setString(1, name);

			pst.setInt(2, artikelnr);

			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
	
	
	public static int sucheProduktIDanhandArtikelNr(int artikelnummer) {
		Connection con = DBConnection.getConnection();
		int produkt_id = 1;
		try {
			PreparedStatement pst = con.prepareStatement(PRODUKTID_DURCH_ARTIKELNUMMER);
			pst.setInt(1, artikelnummer);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				produkt_id = rs.getInt(1);
			} else if(!rs.next()) {
				produkt_id = 1;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produkt_id;
	}
	
	
}
