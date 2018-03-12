package de.servlets;

import java.io.IOException;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String passwort = request.getParameter("psw");
		String strasse = request.getParameter("strasse");
		String plz = request.getParameter("postleitzahl");
		String hausnr = request.getParameter("hausnummer");
		String ort = request.getParameter("ort");
		String email = request.getParameter("email");
		String text = " Herzlich Willkommen bei SportWeb! Sie sind nun registriert.";
		
		if (!Regex.pruefeRegexPasswort(passwort)) {
			request.setAttribute("fehlermeldungPasswort", "Bitte passwort neu eingeben!");
		} // hier machen wir noch was geiles, ist eher zu testzwecken

		if (!Regex.pruefeRegexHausnummer(hausnr)) {

			request.setAttribute("fehlermeldungHausnr", "Bitte Hausnummer neu eingeben!");

		}

		if (!Regex.pruefeRegexPostleitzahl(plz)) {
			request.setAttribute("fehlermeldungplz", "Bitte plz neu eingeben!");
		}
		if (!Regex.pruefeRegexEMail(email)) {

			request.setAttribute("fehlermeldungEmail", "Bitte Email neu eingeben!");

		}

		if (Regex.pruefeRegexPasswort(passwort) && Regex.pruefeRegexHausnummer(hausnr) && Regex.pruefeRegexEMail(email)
				&& Regex.pruefeRegexPostleitzahl(plz)) {
			int hausnummer = Integer.parseInt(hausnr);
			int postleitzahl = Integer.parseInt(plz);
			Adresse adresse = new Adresse(strasse, hausnummer, postleitzahl, ort);
			try {
				String saltedHashPassword = SaltedHash.getSaltedHash(passwort);
				Kunde kunde = new Kunde(saltedHashPassword, email, adresse, vorname, nachname);
				KundenOperations.anlegen(kunde);
				mail.SendMailTLS(kunde.getEmail(),"Ihre Regsitrierung bei SportWeb" , text);
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		
		
		request.getRequestDispatcher("signup.jsp").forward(request, response);

	}
}
