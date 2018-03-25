package de.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.classes.Adresse;
import de.classes.Kunde;
import de.databaseOperations.KundenOperations;
import de.logik.Regex;
import de.utilities.SaltedHash;
import de.utilities.mail;

/**
 * Servlet implementation class SignUpServlet
 * Dieses Servlet liefert die Logik um Neukunden anzulegen, deren Daten zu prüfen und sie dann in 
 * die DB zu schreiben.
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// leere methode
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String passwort = request.getParameter("psw");
		String repeatedpasswort = request.getParameter("psw_repeat");
		String strasse = request.getParameter("strasse");
		String plz = request.getParameter("postleitzahl");
		String hausnr = request.getParameter("hausnummer");
		String ort = request.getParameter("ort");
		String email = request.getParameter("email");
		String text = " Herzlich Willkommen bei SportWeb! Sie sind nun registriert.";
		
		List<String> messages = new ArrayList<>();
		
		if (!Regex.pruefeRegexPasswort(passwort)) {
			// die fehlermeldung muss noch näher erläutert werden!
			messages.add("Bitte passwort neu eingeben!");
		} 

		if (!Regex.pruefeRegexHausnummer(hausnr)) {
			messages.add("Bitte Hausnummer neu eingeben!");
		}

		if (!Regex.pruefeRegexPostleitzahl(plz)) {
			messages.add("Bitte plz neu eingeben!");
		}
		
		if (!Regex.pruefeRegexEMail(email)) {
			messages.add("Bitte Email neu eingeben!");
		}
		
		if (!repeatedpasswort.equals(passwort)){
			messages.add("Passwörter stimmen nicht überein!");
		}
		
		if(KundenOperations.mailIstVorhanden(email)){
			messages.add("Mail ist bereits registriert!");
		}
		
		if (messages.isEmpty()) {
			int postleitzahl = Integer.parseInt(plz);
			Adresse adresse = new Adresse(strasse, hausnr, postleitzahl, ort);
			try {
				String saltedHashPassword = SaltedHash.getSaltedHash(passwort);
				Kunde kunde = new Kunde(saltedHashPassword, email, adresse, vorname, nachname);
				KundenOperations.anlegen(kunde);
				mail.SendMailTLS(kunde.getEmail(), "Ihre Registrierung bei SportWeb", text);
				messages.add("Ihre Registrierung war erfolgreich. Checken Sie Ihren Mail Account.");
			} catch (Exception e) {
				messages.add("Es ist ein Fehler bei der Registrierung aufgetreten. Bitte Admin kontaktieren.");
				e.printStackTrace();
			}

		}

		request.setAttribute("messages", messages);
		request.getRequestDispatcher("signup.jsp").forward(request, response);

	}
}
