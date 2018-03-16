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
			
		} else  {
			
			messages.add("Ihre Informationen wurden erfolgreich geändert.");
			Adresse adresse = kunde.getAdresse();
			if(vorname != kunde.getVorname() && !vorname.equals("")) {
				
				kunde.setVorname(vorname);
				messages.add("Der Vorname wurde geändert.");
				
			}  else if(nachname != kunde.getNachname() && !nachname.equals("")) {
				
				kunde.setNachname(nachname);
				messages.add("Der Nachname wurde geändert.");
				
			} else if(email != kunde.getEmail() && Regex.pruefeRegexEMail(email)) {
				
				kunde.setEmail(email);
				messages.add("Die E-Mail wurde geändert.");
			
			} else if(strasse != adresse.getStrasse()) {
				adresse.setStrasse(strasse);
				messages.add("Die Straße wurde geändert.");
				
			} else if(hausnr != adresse.getHausnummer()) {
				adresse.setHausnummer(hausnr);
				messages.add("Die Hausnummer wurde geändert.");
			} else if(plz != adresse.getPlz()) {
				adresse.setPlz(plz);
				messages.add("Die PLZ wurde geändert.");
			} else if(ort != adresse.getOrt()) {
				adresse.setOrt(ort);
				messages.add("Der Ort wurde geändert.");
				
			}
			kunde.setAdresse(adresse);
			KundenOperations.kundenUpdateDaten(kunde);
			session.setAttribute("kundeeingeloggt", kunde);
			
		}
		
		
		
		
	
		
		request.setAttribute("messages", messages);
		request.getRequestDispatcher("profilaendern.jsp").forward(request, response);
	}

}
