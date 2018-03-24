package de.utilities;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * https://stackoverflow.com/a/11038230
 * @author Paul Blanke
 * Diese Klasse bietet Operations zum Ver und Entschlüsseln des 
 * Passwortes mit der Methode SaltedHash an.
 * 
 * 
 *
 */
public class SaltedHash {

	/**
	 * beshcreibt die Anzahl der Verschl&uesselungen
	 */
	private static final int ITERATIONS = 20 * 1000;
	/**
	 * Beschreibt den gesetzten Salt
	 */
	private static final int SALT_LEN = 32;
	/**
	 * Begrenzt die L&aenge des Keys
	 */
	private static final int KEY_LEN = 256;

	/***
	 * Diese Methode verschl&uesselt das Passwort.
	 * @param pwd Das zuverschl&uesselende Passwort
	 * @return Verschl&uesselungswert
	 * @throws Exception 
	 */
	public static String getSaltedHash(String pwd) throws Exception {

		byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(SALT_LEN);
		String saltString = Base64.getEncoder().encodeToString(salt);
		String saltedHashString = Base64.getEncoder().encodeToString(hash(pwd, salt));
		return saltString + "$" + saltedHashString;
	}
	
	/**
	 * Diese Methode entschl&uellest das Passwort wieder.
	 * @param rawInput Das verschl&uesselte Passwort.
	 * @param salt Der vewendete Key
	 * @return Das entsch&uesselte Passwort
	 */

	public static byte[] hash(String rawInput, byte[] salt) {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			SecretKey skey = skf.generateSecret(new PBEKeySpec(rawInput.toCharArray(), salt, ITERATIONS, KEY_LEN));
			return skey.getEncoded();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Diese Methode netschl&uesselt das Passwort und vergleicht es mit einem eingegebene Passwort.
	 * @param enteredPwd eingegebenes Passwort
	 * @param hashFromDatabase Hashwert aus der Tabelle
	 * @return Passwort richtig / falsch.
	 */
	public static boolean isPwdEqual(String enteredPwd, String hashFromDatabase){
		
		String[] saltAndHash = hashFromDatabase.split("\\$");
		String saltString = saltAndHash[0];
		String hashFromSalt = saltAndHash[1];
		
		byte[] saltByteArray = Base64.getDecoder().decode(saltString);
		return Base64.getEncoder().encodeToString(hash(enteredPwd, saltByteArray)).equals(hashFromSalt);
	}
	

	
}
