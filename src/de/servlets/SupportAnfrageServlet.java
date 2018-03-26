package de.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.classes.Kunde;
import de.classes.SupportAnfrage;
import de.databaseOperations.SupportOperations;
import de.utilities.Mail;

/**
 * @author Paul Blanke
 * Dieses Servlet speichert die SupportAnfrage und informiert den Admin per E-mail.
 * Servlet implementation class SupportAnfrageServlet
 */
@WebServlet("/SupportAnfrageServlet")
public class SupportAnfrageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportAnfrageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// leere methode
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Daten holen
		String anfrage = request.getParameter("anfrage");
		String anfragetext = request.getParameter("anfragetext");
		String grund = request.getParameter("grund");
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<>();
		String ausgabe = "Ihre Anfrage ist eingegangen und wird sofort bearbeitet.";
		SupportAnfrage support = new SupportAnfrage(anfrage, anfragetext, grund);
		List<SupportAnfrage> supportAnfragen = support.getAnfragen();
		session.setAttribute("anfragen", supportAnfragen);
		// Logik
		messages.add(ausgabe);
		SupportOperations.speichereSupportAnfrage(support);
		Mail.SendMailTLS("Message.RecipientType.TO", "SupportAnfrage",  anfragetext);
		request.setAttribute("messages", messages);
		
		request.getRequestDispatcher("contact.jsp").forward(request, response);
		
	}

}
