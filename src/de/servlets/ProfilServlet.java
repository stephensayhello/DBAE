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
import de.utilities.SaltedHash;

/**
 * Servlet implementation class ProfilServlet
 */
@WebServlet("/ProfilServlet")
public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfilServlet() {
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
		HttpSession session = request.getSession();
		String password = request.getParameter("psw");
		
		Kunde kunde = (Kunde) session.getAttribute("kundeeingeloggt");
        System.out.println(kunde);
		List<String> messages = new ArrayList<>();
		request.setAttribute("messages", messages);
		System.out.println(kunde.getPasswort());
		if (SaltedHash.isPwdEqual(password, kunde.getPasswort())) {
			
			session.setAttribute("kundeeingeloggt", kunde);
			System.out.println("blabla");
			request.getRequestDispatcher("profilinfos.jsp").forward(request, response);
		} else {
			String fehlermeldung = "Das eingegebene Passwort ist nicht falsch!";
			messages.add(fehlermeldung);
			request.getRequestDispatcher("profil.jsp").forward(request, response);
		}

	}

}
