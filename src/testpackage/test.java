package testpackage;



import de.classes.Nutzer;
import de.databaseOperations.Nutzeroperations;


public class test {

	public static void main(String[] args) {
		Nutzeroperations nutzeroperation = new Nutzeroperations();
		int id = nutzeroperation.hoechsteID();
		Nutzer nutzer = new Nutzer( id, "arsch@yahoo.de", "blablabla");
		
		
		nutzeroperation.anlegen(nutzer);
	}
}