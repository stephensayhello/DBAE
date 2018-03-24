package de.classes;
/**
 * Diese Klasse präsentiert die Rolle des Admins.
 * @author Paul Blanke.
 * @see {@link Nutzer}
 *
 */
public class Admin extends Nutzer {

	/**
	 * Der benutzername des Admin
	 */
	private String name;
	// get und Set
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	// konstruktor
	/**
	 * 
	 * @param nutzer_id DB-Id.
	 * @param email Die E-Mail die als Benutzer dient.
	 * @param passwort DasPpasswort des Admin.
	 * @param name Benutzername.
	 */
	public Admin(int nutzer_id, String email, String passwort,String name) {
		super( nutzer_id, email, passwort);
		this.setName(name);
		
	}


}
