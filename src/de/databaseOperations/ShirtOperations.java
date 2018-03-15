package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.classes.Produkt;

import de.classes.Shirt;
import de.datenbank.DBConnection;

public class ShirtOperations {
	public final static String HOLE_SHIRT_NACH_SHID = "SELECT * FROM shirt WHERE sh_id = ?;";

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
						groesse, produkt.getMenge());

				return shirt;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}
}