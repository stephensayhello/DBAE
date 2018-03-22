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

import de.classes.Kunde;

/**
 * Servlet implementation class BewertungServlet
 */
@WebServlet("/BewertungServlet")
public class BewertungServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BewertungServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// To Post
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String punkteRe = request.getParameter("punkte");
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<>();
		session.setAttribute("messages", messages);
		Kunde kunde = (Kunde) session.getAttribute("kundeeingeloggt");
		int punkte = 1;
		String kommentar = request.getParameter("bewertung");
		if(!punkteRe.contains("")) {
			try {
				punkte = Integer.parseInt(punkteRe);
			} catch(NumberFormatException nfe) {
				System.out.println(nfe.toString());
			}
		} else if(kunde == null) {
			messages.add("Nur eingeloggte Kunden können bewerten !");
			
		} else {
			messages.add("Sie können das Produkt nur bewerten, wenn Sie es gekauft haben");
		}
		System.out.println("Mimi Bwertung erstellt!");
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
