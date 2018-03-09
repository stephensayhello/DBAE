package de.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.datenbank.DBConnection;


/**
 * Diese Klasse repräsentiert den Nutzer.
 * @author paul4
 *
 */
abstract class Nutzer {
	/**
	 *@param Der Name des Nutzers.
	 */
	private String email;
	
	private String passwort;
	



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	

}
