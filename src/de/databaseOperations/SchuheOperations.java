package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Admin;
import de.classes.Hose;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.datenbank.DBConnection;

public class SchuheOperations {
	private final static String HOLE_SCHUHE_NACH_SCHID = "SELECT * FROM schuhe WHERE sch_id = ?;";
	
	private final static String SCHUHE_LOESCHEN = "DELETE FROM schuhe WHERE sch_id = ?;";

	public static Schuhe holeSchuheausdb(Produkt produkt) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(HOLE_SCHUHE_NACH_SCHID);
			pst.setInt(1, produkt.getProdukt_id());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int sch_id = rs.getInt(1);
				int groesse = rs.getInt(2);
				System.out.println(produkt.getArtikelnr()+ ", artikelnrprodukt");
				Schuhe schuhe = new Schuhe(sch_id, produkt.getName(), produkt.getBeschreibung(), produkt.getPreis(),
						groesse, produkt.getMenge(),produkt.getArtikelnr(),produkt.getVersanddauer());
				System.out.println(schuhe.getArtikelnr() + ", artikelnrschuhe");
				return schuhe;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}
	
	
	public static void entferneSchuhe(Schuhe schuhe) {
		Connection con = DBConnection.getConnection();
		 try {
			PreparedStatement pst = con.prepareStatement(SCHUHE_LOESCHEN);
			pst.setInt(1, schuhe.getProdukt_id());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
