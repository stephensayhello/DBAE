package de.logik;

import java.util.regex.Pattern;
/**
 * Diese Klasse stellt Regex �berpr�fungen bereit.
 * @author Benjamin Gajewski
 * 
 */
public class Regex {
	/**
	 * Diese String stellen die regex bereit.
	 */
	private final static String HAUSNUMMER_REGEX = "[0-9]+[a-zA-Z]?";
	private final static String POSTLEITZAHL_REGEX = "[0-9]{5}";
	private final static String EMAIL_REGEX = "([0-9a-zA-Z.])+[@][0-9a-zA-Z]+[.][a-z]{1,3}";
	private final static String PASSWORT_REGEX = "([a-zA-Z_0-9?!-]){8,255}";
	private final static String NUR_ZAHLEN_REGEX = "^[-1]*||[0-9]{1,45}$";
	private final static String NUR_DOUBLE_REGEX = "/^[0-9]+(\\.[0-9]+)?$";
	
	/**
	 * Diese Methode �berpr�ft die Eingabe auf Korrektheit.
	 * @param hausnummer zu pr�fende Eingabe,
	 * @return pr�fung war richtig / falsch.
	 */
	public static boolean pruefeRegexHausnummer(String hausnummer) {
		return Pattern.matches(HAUSNUMMER_REGEX, hausnummer);
	}
	/**
	 * Diese Methode �berpr�ft die Eingabe auf Korrektheit.
	 * @param hausnummer zu pr�fende Eingabe,
	 * @return pr�fung war richtig / falsch.
	 */
	public static boolean pruefeRegexPostleitzahl(String postleitzahl) {
		return Pattern.matches(POSTLEITZAHL_REGEX, postleitzahl);
	}
	/**
	 * Diese Methode �berpr�ft die Eingabe auf Korrektheit.
	 * @param email zu pr�fende Eingabe,
	 * @return pr�fung war richtig / falsch.
	 */
	public static boolean pruefeRegexEMail(String email) {
		return Pattern.matches(EMAIL_REGEX, email);
	}
	/**
	 * Diese Methode �berpr�ft die Eingabe auf Korrektheit.
	 * @param passwort zu pr�fende Eingabe,
	 * @return pr�fung war richtig / falsch.
	 */
	public static boolean pruefeRegexPasswort(String passwort) {
		return Pattern.matches(PASSWORT_REGEX, passwort);
	}
	/**
	 * Diese Methode �berpr�ft die Eingabe auf Korrektheit.
	 * @param zahl zu pr�fende Eingabe,
	 * @return pr�fung war richtig / falsch.
	 */
	public static boolean pruefeNurZahlen(String zahl) {
		return Pattern.matches(NUR_ZAHLEN_REGEX, zahl);
	}
	/**
	 * Diese Methode �berpr�ft die Eingabe auf Korrektheit.
	 * @param zahl zu pr�fende Eingabe,
	 * @return pr�fung war richtig / falsch.
	 */
	public static boolean pruefeNurDouble(String zahl) {
		return Pattern.matches(NUR_DOUBLE_REGEX, zahl);
	}
}
