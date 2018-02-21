package de.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.classes.WebNutzer;

/**
 * Servlet implementation class EingabeServlet
 */
@WebServlet("/EingabeServlet")
public class EingabeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EingabeServlet() {
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
	 * Diese Methode prüft anhand einer Instanz des WebNutzer, ob der Login richtig war.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bname = request.getParameter("bname");
		String passwort = request.getParameter("passwort");
		WebNutzer nutzer = new WebNutzer(bname, passwort);
		nutzer.loginIn();
		PrintWriter out = response.getWriter();
		String ausgabe ="Hallo.";
		
		if(nutzer.loginIn()== true) {
			ausgabe +="herzlichen Willkommen. " + nutzer.getBenutzername();
		} else {
			ausgabe += "Der Login ist fehl geschlagen";
		}
		out.append(ausgabe);
		
		request.getRequestDispatcher("index.jsp").include(request, response);
		
	}

}
