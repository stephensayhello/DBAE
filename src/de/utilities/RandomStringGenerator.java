package de.utilities;
import java.util.UUID;
/**
 * Diese Klasse genertiert einen Zufallsstring.
 * @author Benjamin Gajewski
 *
 */
public class RandomStringGenerator {
  
	/**
	 * Diese Methode generiet einen Zufallskey f&uumlr das Zur&uecksetzen der Passw&oumlrter.
	 * @return
	 */
    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return  uuid;
    }
}
