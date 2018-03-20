package de.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.classes.Adresse;
import de.classes.Kunde;
import de.databaseOperations.KundenOperations;
import de.logik.Regex;

/**
 * Servlet implementation class ProfilAendernServlet
 */
@WebServlet("/ProfilAendernServlet")
public class ProfilAendernServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilAendernServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get Data
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String email = request.getParameter("mail");
		String strasse = request.getParameter("strasse");
		String hausnr = request.getParameter("hausnr");
		int plz = 0;
		// -> PLZ Fehler fangen.
		try {
			plz = Integer.parseInt(request.getParameter("plz"));
		} catch(NumberFormatException nfe) {
			System.out.println("Fehler gefangen.");
		}
		
		String ort = request.getParameter("ort");
		HttpSession session = request.getSession();
		Kunde kunde = (Kunde) session.getAttribute("kundeeingeloggt");

		List<String> messages = new ArrayList<>();
		session.removeAttribute("kundeeingeloggt");
		
		
		if(vorname.equals("") && nachname.equals("") && email.equals("")  && strasse.equals("") 
				&& hausnr.equals("") && ort.equals("")) {
			// Mache Nichts keine Änderungen.
			messages.add("Sie haben Ihre Informationen nicht verändert.");
			
		} else if(strasse.equals("") && hausnr.equals("") && ort.equals("")) {
			
			messages.add("Ihre Informationen wurden erfolgreich geändert.");
	
			if(vorname != kunde.getVorname() && !vorname.equals("") && nachname.equals("") && email.equals("")) {
				
				kunde.setVorname(vorname);
				messages.add("Der Vorname wurde geändert.");
				
			}  else if(nachname != kunde.getNachname() && !nachname.equals("") && vorname.equals("") && email.equals("")) {
				
				kunde.setNachname(nachname);
				messages.add("Der Nachname wurde geändert.");
				
			} else if(email != kunde.getEmail() && Regex.pruefeRegexEMail(email) && !email.equals("")  && nachname.equals("") && vorname.equals("")) {
				
				kunde.setEmail(email);
				messages.add("Die E-Mail wurde geändert.");
			
			}  else if(vorname != kunde.getVorname() && nachname != kunde.getNachname() && email.equals("")) {
				
				kunde.setVorname(vorname);
				kunde.setNachname(nachname);
				messages.add("Der Vor und Nachname wurde geändert!");
				
			} else if(vorname != kunde.getVorname() && email != kunde.getEmail() && nachname.equals("") && Regex.pruefeRegexEMail(email)) {
				kunde.setVorname(vorname);
				kunde.setEmail(email);
				messages.add("Der Vorname und die E-Mail wurden geändert!");
				
			}else if(nachname!= kunde.getNachname() && email != kunde.getEmail() && vorname.equals("") && Regex.pruefeRegexEMail(email)) {
				
				kunde.setNachname(nachname);
				kunde.setEmail(email);
				messages.add("Der Nachname und die E-Mail wurde geändert!");
				
			} else if(vorname != kunde.getVorname() && nachname != kunde.getNachname() && email != kunde.getEmail() && Regex.pruefeRegexEMail(email)) {
				
				kunde.setVorname(vorname);
				kunde.setNachname(nachname);
				kunde.setEmail(email);
				messages.add("Der Vor- und Nachname und die EMail wurden aktualisiert");
				
			} else {
				messages.add("Bei Aktualiserung ist leider ein technischer Fehler aufgetretten. Bitte geben Sie die Daten neu ein.");
			}
			
			
			
			KundenOperations.kundenUpdateDaten(kunde);
			session.setAttribute("kundeeingeloggt", kunde);
			
			
			
			
			
			
			// Änderungen an der Adresse
		} else if(vorname.equals("") && nachname.equals("") && email.equals("")) {
			
			messages.add(" Die Informationen Ihrer Adresse wurde aktualisiert.");
			Adresse adresse = kunde.getAdresse();
			
			if(strasse != adresse.getStrasse() && !strasse.equals("") && hausnr.equals("") && ort.equals("") && plz == 0) {
				adresse.setStrasse(strasse);
				messages.add("Die Straße wurde geändert.");
				
			} else if(hausnr != adresse.getHausnummer() && !hausnr.equals("")&& strasse.equals("") && ort.equals("") && plz == 0) {
				adresse.setHausnummer(hausnr);
				messages.add("Die Hausnummer wurde geändert.");
				
			} else if(plz != adresse.getPlz() && hausnr.equals("") && ort.equals("") && strasse.equals("")) {
				adresse.setPlz(plz);
				messages.add("Die PLZ wurde geändert.");
				
			} else if(ort != adresse.getOrt() && !ort.equals("") && hausnr.equals("") && strasse.equals("") && plz == 0) {
				adresse.setOrt(ort);
				messages.add("Der Ort wurde geändert.");
			// 2 und mehr Sachen werden geändert.	
			} else if(strasse != adresse.getStrasse() && !strasse.equals("") && hausnr != adresse.getHausnummer() && !hausnr.equals("")
					& ort.equals("") && plz == 0) {
				adresse.setStrasse(strasse);
				adresse.setHausnummer(hausnr);
				messages.add("Die Straße und Hausnummer wurden geändert.");
				
			} else if(strasse != adresse.getStrasse() && !strasse.equals("") &&hausnr.equals("") && ort!= adresse.getOrt() && !ort.equals("")
					& hausnr.equals("") && plz == 0) {
				adresse.setStrasse(strasse);
				adresse.setOrt(ort);
				messages.add("Die Straße und der Ort wurden geändert.");
				
			} else if(strasse != adresse.getStrasse() && !strasse.equals("") && plz != adresse.getPlz() && ort.equals("") && hausnr.equals("")) {
				adresse.setStrasse(strasse);
				adresse.setPlz(plz);
				messages.add("Die Straße und die PLZ wurden geändert.");
				
			} else if(strasse != adresse.getStrasse() && !strasse.equals("") && hausnr.equals("") && plz== 0
					& !ort.equals("") && ort != adresse.getOrt()) {
				adresse.setStrasse(strasse);
				adresse.setOrt(ort);
				messages.add("Die Straße und Hausnummer wurden geändert.");
				
			}  else if(strasse != adresse.getStrasse() && !strasse.equals("") && hausnr != adresse.getHausnummer() && !hausnr.equals("")
					& ort.equals("") && plz == 0) {
				adresse.setStrasse(strasse);
				adresse.setHausnummer(hausnr);
				messages.add("Die Straße und Hausnummer wurden geändert.");
				
			} else if(strasse != adresse.getStrasse() && !strasse.equals("") &&hausnr.equals("") && ort!= adresse.getOrt() && !ort.equals("")
					& hausnr.equals("") && plz == 0) {
				adresse.setStrasse(strasse);
				adresse.setOrt(ort);
				messages.add("Die Straße und der Ort wurden geändert.");
				
			}  else if(hausnr != adresse.getHausnummer() && !hausnr.equals("") && plz != adresse.getPlz() && ort.equals("") && strasse.equals("")) {
				adresse.setHausnummer(hausnr);
				adresse.setPlz(plz);
				messages.add("Die Hausnummer und PLZ wurden geändert.");
				
			} else if(hausnr != adresse.getHausnummer() && !hausnr.equals("") && plz == 0 && !ort.equals("") && ort != adresse.getOrt() && strasse.equals("")) {
				adresse.setHausnummer(hausnr);
				adresse.setOrt(ort);;
				messages.add("Die Hausnummer und der Ort wurden geändert.");
				
			}   else if(plz != adresse.getPlz() && !ort.equals("") && ort != adresse.getOrt() && strasse.equals("") && hausnr.equals("")) {
				adresse.setOrt(ort);;
				adresse.setPlz(plz);
				messages.add("Die PLZ und der Ort wurden geändert.");
				 //
			} else if(strasse != adresse.getStrasse() && !adresse.equals("") && hausnr != adresse.getHausnummer() && !hausnr.equals("") 
					&& plz!= adresse.getPlz() && ort.equals("")) {
				adresse.setStrasse(strasse);
				adresse.setHausnummer(hausnr);
				adresse.setPlz(plz);
				messages.add("Die Straße, die Hausnummer und die PLZ wurden geändert");
				
			} else if(strasse != adresse.getStrasse() && !adresse.equals("") && hausnr != adresse.getHausnummer() && !hausnr.equals("") &&
					ort != adresse.getOrt() && !ort.equals("") && plz == 0) {
				adresse.setStrasse(strasse);
				adresse.setHausnummer(hausnr);
				adresse.setOrt(ort);
				messages.add("Die Straße, die Hausnummer und der Ort wurden geändert");
				
			} else if(adresse.equals("") && hausnr != adresse.getHausnummer() && !hausnr.equals("") &&
					ort != adresse.getOrt() && !ort.equals("") && plz != adresse.getPlz()) {
			
				adresse.setHausnummer(hausnr);
				adresse.setOrt(ort);
				adresse.setPlz(plz);
				messages.add("Die PLZ, die Hausnummer und der Ort wurden geändert");
			} else if(strasse != adresse.getStrasse() && !adresse.equals("") && hausnr != adresse.getHausnummer() && !hausnr.equals("") &&
					ort != adresse.getOrt() && !ort.equals("") && plz != adresse.getPlz()) {
				adresse.setStrasse(strasse);
				adresse.setHausnummer(hausnr);
				adresse.setOrt(ort);
				adresse.setPlz(plz);
				messages.add("Die Straße, die Hausnummer , die PLZ und der Ort wurden geändert");
			}
			
			
			
			
			
			
			
			kunde.setAdresse(adresse);
			KundenOperations.kundenUpdateDaten(kunde);
			session.setAttribute("kundeeingeloggt", kunde);
			
		}
		
		
		
		
	
		
		request.setAttribute("messages", messages);
		request.getRequestDispatcher("profilaendern.jsp").forward(request, response);
	}

}
