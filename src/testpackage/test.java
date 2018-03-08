package testpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.datenbank.DBConnection;

public class test {

	public static void main(String[] args) {
		Connection con = DBConnection.getConnection();
		String sqlInsertStmt = "INSERT INTO nutzer VALUES (2, 'test','test', 'test' )";

		try {
			PreparedStatement stmt = con.prepareStatement(sqlInsertStmt);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
