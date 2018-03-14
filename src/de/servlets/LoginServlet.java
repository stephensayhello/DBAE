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

import de.classes.Admin;
import de.classes.Kunde;
import de.classes.Nutzer;
import de.databaseOperations.AdminOperations;
import de.databaseOperations.KundenOperations;
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
		session = request.getSession();
		Nutzer nutzer = NutzerOperations.nutzerAusDbHolen(email);
		System.out.println(nutzer);
		List<String> messages = new ArrayList<>();
		request.setAttribute("messages", messages);
		if (nutzer != null) {
			if (!SaltedHash.isPwdEqual(password, nutzer.getPasswort())) {
				// hier fehlt noch was
				String fehler = "passwort falsch";
				messages.add(fehler);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			if (NutzerOperations.nutzeristKunde(nutzer)) {

				Kunde kunde = KundenOperations.kundeausdbholen(nutzer);
                System.out.println(kunde);
				session.setAttribute("kundeeingeloggt", kunde);
				
				session.setAttribute("kundenadresse", kunde.getAdresse());
				messages.add("Erfolgreicher login");
				request.getRequestDispatcher("artikeluebersicht.jsp").forward(request, response);
			}
			if (NutzerOperations.nutzeristAdmin(nutzer)) {
				Admin admin = AdminOperations.holeAdminausDB(nutzer);

				session.setAttribute("admineingeloggt", admin);
			}

		} else {

			String falschernutzername = "Name falsch";
			messages.add(falschernutzername);
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}

	}

}
