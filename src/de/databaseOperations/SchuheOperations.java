package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Hose;
import de.classes.Produkt;
import de.classes.Schuhe;
import de.datenbank.DBConnection;

public class SchuheOperations {
	public final static String HOLE_SCHUHE_NACH_SCHID = "SELECT * FROM schuhe WHERE sch_id = ?;";

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
						groesse, produkt.getMenge());

				return schuhe;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}
}
