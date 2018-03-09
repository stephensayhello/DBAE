package de.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.datenbank.DBConnection;

/**
 * Diese Klasse repräsentiert den Nutzer.
 * 
 * @author paul4
 *
 */
public class Nutzer {
	/**
	 * @param Der
	 *            Name des Nutzers.
	 */
	private String email;

	private String passwort;

	private int nutzer_id;

	public Nutzer(int nutzerid, String email, String passwort) {

		this.nutzer_id = nutzerid;
		this.email = email;
		this.passwort = passwort;

	}

	public int getNutzer_id() {
		return nutzer_id;
	}

	public void setNutzer_id(int nutzer_id) {
		this.nutzer_id = nutzer_id;
	}

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
