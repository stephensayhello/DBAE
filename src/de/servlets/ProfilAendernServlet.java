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

/**
 * Servlet implementation class ProfilaendernServlet
 */
@WebServlet("/ProfilaendernServlet")
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Daten holen
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String email = request.getParameter("mail");
		String strasse = request.getParameter("strasse");
		String hausnr = request.getParameter("hausnr");
		String plzS = request.getParameter("plz");
		String ort = request.getParameter("ort");
		HttpSession session = request.getSession();
		
		Kunde kunde = (Kunde) session.getAttribute("kundeeingeloggt");
		Adresse adresse = kunde.getAdresse();
		List<String> messages = new ArrayList<>();
		session.removeAttribute("kundeeingeloggt");
		
		// Daten ändern
		if(vorname == null || nachname == null || email == null) {
			// keine Änderung.
		} else if(vorname != kunde.getVorname()) {
			
			kunde.setVorname(vorname);
			
		} else if(nachname != kunde.getNachname()) {
			
			kunde.setNachname(nachname);
			
		} else if(email != kunde.getEmail()) {
			
			kunde.setEmail(email);
		}
		// Update den Kunden.
		
		
		int plz = Integer.parseInt(plzS);
		
		if(strasse == null  || ort == null) {
			
		} else if(strasse != adresse.getStrasse()) {
			
			adresse.setStrasse(strasse);
			
		} else if(hausnr != adresse.getHausnummer()) {
			
			adresse.setHausnummer(hausnr);
			
		} else if(plz != adresse.getPlz()) {
			
			adresse.setPlz(plz);
			
		} else if(ort != adresse.getOrt()) {
			
			adresse.setOrt(ort);
		}
		// Data Update
		KundenOperations.kundenUpdateDaten(kunde);
		
		
		kunde.setAdresse(adresse);
		messages.add("Die Daten wurden geändert.");
		request.setAttribute("messages", messages);
		session.setAttribute("kundeeingeloggt", kunde);
		
		request.getRequestDispatcher("profilaendern.jsp").forward(request, response);
	}

}
