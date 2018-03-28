package de.datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to connect to an Postgresql Server.
 * Vorlage aus dem learnweb uebernommen
 * @author Jan Sarstedt
 * @author (alle)
 *
 */
public class DBConnection {
	protected static Connection instance;
	private static final String DB_SERVER = "207.154.234.136:5432";
	private static final String DB_NAME = "1718-1.0_minimum";
	private static final String DB_USER = "1718-1.0_minimum";
	private static final String DB_PASSWORD = "faeaac7bb82654e372650c901820be77";
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://" + DB_SERVER + "/" + DB_NAME;

	/**
	 * Initializes a connection to the data base defined by DB_URL.
	 * 
	 * @return java.sql.Connection or null if there is an error.
	 */
	private static Connection init() {
		try {
			Class.forName(DB_DRIVER);
			instance = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			return instance;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("PostgresDb: Something went wrong: ");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Returns a Connection or null if connecting to data base fails for some
	 * reason.
	 * 
	 * @return java.sql.Connection
	 */
	public static Connection getConnection() {
		try {
			return (instance == null || instance.isClosed()) ? init() : instance;
		} catch (SQLException e) {
			e.printStackTrace();
			return init();
		}
	}

	public static void closeConnection() {
		try {
			instance.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
