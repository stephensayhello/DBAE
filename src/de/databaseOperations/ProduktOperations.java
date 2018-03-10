package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import de.classes.Produkte;
import de.datenbank.DBConnection;

public class ProduktOperations {
	
	public final static String anlegenProdukt = "INSERT INTO produkt VALUES (?,?,?,?,?)";
	
	public static void anlegen(Produkte produkt) {
		Connection con = DBConnection.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(anlegenProdukt);
			pst.setDouble(1, produkt.getPreis());
			pst.setInt(2, produkt.getMenge());
			pst.setString(3, produkt.getName());
			pst.setString(4, produkt.getBeschreibung());
			//set produkt_id
			pst.setString(6, produkt.getArt());

			pst.execute();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public ProduktOperations() {
		// TODO Auto-generated constructor stub
	}

}
