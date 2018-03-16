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
		
		if(vorname == null && nachname== null && email == null  && strasse == null  
				&& hausnr == null && ort == null) {
			// Mache Nichts keine Änderungen.
			messages.add("Sie haben Ihre Informationen nicht verändert.");
			
		} else if(plz >= 5)  {
			
			messages.add("Ihre Informationen wurden erfolgreich geändert.");
			if(vorname != kunde.getVorname()) {
				
				kunde.setVorname(vorname);
				messages.add("Der Vorname wurde geändert.");
				
			} else if(email != kunde.getEmail()) {
				
				kunde.setEmail(email);
				messages.add("Die E-Mail wurde geändert.");
			
			}
		} else {
			if(nachname.contains("Test") && email.contains("@web.de")) {
				messages.add("testlauf");
			}
			
		}
		
		
		
		
	
		
		request.setAttribute("messages", messages);
		request.getRequestDispatcher("profilaendern.jsp").forward(request, response);
	}

}
