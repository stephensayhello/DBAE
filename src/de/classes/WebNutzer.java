package de.classes;
/**
 * Die Klasse webnutzer mit dem Name und dem Passwort. 
 * @author paul4
 *
 */
public class WebNutzer {
	/**
	 *@param benutzername: Der Login Name. 
	 */
	private String benutzername;
	/**
	 *@param passwort: Das Passwort für den Login. 
	 */
	private String passwort;
	/**
	 *@param hashwert: Der Hashwert vom Passwort. 
	 */
	private int hashwert;
	
// get und set	
	public String getBenutzername() {
		return benutzername;
	}
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public int getHashwert() {
		return hashwert;
	}
	public void setHashwert(int hashwert) {
		this.hashwert = hashwert;
	}
	
// Konstruktor	
	public WebNutzer() {
		verschluesseln(this.getPasswort());
	}
	
	public WebNutzer(String benutzername, String passwort) {
		
		this.setBenutzername(benutzername);
		this.setPasswort(passwort);
		this.verschluesseln(passwort);
	}
	
	
/**
 * Die Methode überprüft den Login.
 * @true gilt im Moment, wenn benutzername gleich passwort.	
 * @return erfolgreich ja / nein.
 */
	public boolean loginIn() {
		if(this.getBenutzername() == this.getPasswort()) {
			return true;
		} else {
			return false;
		}
	}
/**
 * Die Methode liefert den HashWert des Passwortes zurück(?).	
 * @param passwort: das zu verschlüssende passwort.
 */
	private void verschluesseln(String passwort) {
		int hashwert = passwort.hashCode();
		this.setHashwert(hashwert);
		
	}
	
	

}
