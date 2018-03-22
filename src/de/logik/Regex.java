package de.logik;

import java.util.regex.Pattern;

public class Regex {

	private final static String HAUSNUMMER_REGEX = "[0-9]+[a-zA-Z]?";
	private final static String POSTLEITZAHL_REGEX = "[0-9]{5}";
	private final static String EMAIL_REGEX = "([0-9a-zA-Z.])+[@][0-9a-zA-Z]+[.][a-z]{1,3}";
	private final static String PASSWORT_REGEX = "([a-zA-Z_0-9?!]){8,255}";
	private final static String NUR_ZAHLEN_REGEX = "^[-1]*||[0-9]{1,45}$";
	private final static String NUR_DOUBLE_REGEX = "/^[0-9]+(\\.[0-9]+)?$";
	

	public static boolean pruefeRegexHausnummer(String hausnummer) {
		return Pattern.matches(HAUSNUMMER_REGEX, hausnummer);
	}

	public static boolean pruefeRegexPostleitzahl(String postleitzahl) {
		return Pattern.matches(POSTLEITZAHL_REGEX, postleitzahl);
	}

	public static boolean pruefeRegexEMail(String email) {
		return Pattern.matches(EMAIL_REGEX, email);
	}

	public static boolean pruefeRegexPasswort(String passwort) {
		return Pattern.matches(PASSWORT_REGEX, passwort);
	}
	public static boolean pruefeNurZahlen(String zahl) {
		return Pattern.matches(NUR_ZAHLEN_REGEX, zahl);
	}
	public static boolean pruefeNurDouble(String zahl) {
		return Pattern.matches(NUR_DOUBLE_REGEX, zahl);
	}
}
