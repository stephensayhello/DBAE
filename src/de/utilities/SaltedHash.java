package de.utilities;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * https://stackoverflow.com/a/11038230
 * 
 * 
 * 
 * 
 *
 */
public class SaltedHash {

	
	private static final int ITERATIONS = 20 * 1000;
	
	
	private static final int SALT_LEN = 32;
	
	
	private static final int KEY_LEN = 256;

	
	public static String getSaltedHash(String pwd) throws Exception {

		byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(SALT_LEN);
		String saltString = Base64.getEncoder().encodeToString(salt);
		String saltedHashString = Base64.getEncoder().encodeToString(hash(pwd, salt));
		return saltString + "$" + saltedHashString;
	}
	


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
	
	
	public static boolean isPwdEqual(String enteredPwd, String hashFromDatabase){
		
		String[] saltAndHash = hashFromDatabase.split("\\$");
		String saltString = saltAndHash[0];
		String hashFromSalt = saltAndHash[1];
		
		byte[] saltByteArray = Base64.getDecoder().decode(saltString);
		return Base64.getEncoder().encodeToString(hash(enteredPwd, saltByteArray)).equals(hashFromSalt);
	}
	

	
}
