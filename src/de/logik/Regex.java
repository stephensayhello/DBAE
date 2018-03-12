package de.logik;

import java.util.regex.Pattern;

public class Regex {

	private final static String HAUSNUMMER_REGEX = "[0-9]+[a-zA-Z]?";
	private final static String POSTLEITZAHL_REGEX = "[0-9]{5}";
	private final static String EMAIL_REGEX = "([0-9a-zA-Z.])+[@][0-9a-zA-Z]+[.][a-z]{1,3}";
	private final static String PASSWORT_REGEX = "([a-zA-Z_0-9?!]){8,255}";
	

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

}
