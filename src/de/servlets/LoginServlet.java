package de.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import de.classes.Nutzer;
import de.databaseOperations.NutzerOperations;
import de.utilities.SaltedHash;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("psw");

		Nutzer nutzer = NutzerOperations.nutzerAusDbHolen(email);
		if (nutzer != null) {
			if (!SaltedHash.isPwdEqual(password, nutzer.getPasswort())) {
				// hier fehlt noch was
				String fehler = "passwort falsch";
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			if (NutzerOperations.nutzeristKunde(nutzer)) {
				session = request.getSession();
				session.setAttribute("kundeeingeloggt", nutzer);
				session.setAttribute("emailadresse", email);
			}
			
			if(NutzerOperations.nutzeristAdmin(nutzer)){
				
			}

		}

	}

}
