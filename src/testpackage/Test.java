package testpackage;

import javax.swing.plaf.synth.SynthSeparatorUI;

import de.classes.Kunde;
import de.databaseOperations.BewertungsOperations;
import de.databaseOperations.KundenOperations;
import de.databaseOperations.NutzerOperations;
import de.utilities.SaltedHash;

/**
 * Eine Testklasse mit der Funktionen ohne den Weg u&eber den HTMl Code gepr&ueft werden 
 * k&oennen.
 * @author alle
 *
 */
public class Test {

	public static void main(String[]args) {
		
		Kunde kunde = KundenOperations.kundeausdbholen(NutzerOperations.nutzerAusDbHolen(1));
		String pwd = kunde.getPasswort();
		System.out.println(SaltedHash.isPwdEqual("90322b84-c10a-4e36-9f17-3f49f54dda07", pwd));
		
		
	}
}
