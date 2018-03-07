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
	private String name;
	/**
	 *@param Die Adresse des Nutzers. 
	 */
	private String adresse;
	/**
	 *@param Die Email Adresse. 
	 */
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
