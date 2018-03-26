
/**
 * @author Paul Blanke, Benjamin Gajewski, Stephen Galla
 *
 */
package de.databaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.datenbank.DBConnection;

/**
 * Diese Datei enthält die übergreifende Dokumentation für alle Klasse dieses Packages.
 * Diese Klassen haben alle ähnliche Methoden, die an dieser Stelle vorgestellt werden.
 * 
 * Die Methoden und Attribute der Klassen orientieren sich bei an den folgenden SQL-Statements:
 * 
 * - UPDATE
 * -SELECT
 * -INSERT INTO
 * - DELETE
 * - SELECT MAX(ID) WHERE tabelle 
 * 
 * 
 * 
 */
class info {
	/***
	 * 
	 * SQL Statements haben diese Form
	 * 
	 **/
	private static final String SQL_Statements = "BEISPIELSTATEMENT";
	
	
	/**
	 * @throws SQLException 
	 * Diese Methode sucht die höchste ID aus der DB und erhöht sie um eins. 
	 *@return id 
	 */
	public static int hoechstwID() throws SQLException {
		Connection con = DBConnection.getConnection();
		/**
		 * Das PreparedStatemet und die ? werden gesetzt. 
		 */
		PreparedStatement pst = con.prepareStatement("SELECT MAX(ID)");
		return 0;
	}
	
	
	/**
	 * Die Methode holt ein objekt anhand einer ID aus der DB.
	 * Hier ist mehrere Varianten der Methode möglich möglich.
	 * @param id das zuholende Objekt
	 * @return Objekt zB. Kunde.
	 */
	public static Object holeEtwasausDB(int id) {
		return null;
	}
	
	
	
	/**
	 * Diese Methode updatet ein vorhandes Objekt.
	 * Verschiedene Varianten vorhanden.
	 *@param das zu updatete Objekt.
	 */
	public static void updateObjekt(Object o) {
		
	}
	/**
	 * Diese Methode schreibt ein neues Objekt in die DB.
	 * Verschiedene Varianten vorhanden.
	 *@param das neue Objekt.
	 */
	public static void insertInto(Object o) {
		
	}
	
	/**
	 * Diese Methode löscht ein übergegebenes Objekt aus der DB.
	 * Verschiedene Varianten vorhanden.
	 *@param das löschende Objekt.
	 */
	public static void deleteObject(Object o) {
		
	}
	
}

