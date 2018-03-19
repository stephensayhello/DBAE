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
import de.databaseOperations.KundenOperations;

/**
 * Servlet implementation class KundenOperationsServlet
 */
@WebServlet("/KundenOperationsServlet")
public class KundenOperationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KundenOperationsServlet() {
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
		List<Kunde> kunden =  KundenOperations.holeAlleKunden();
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<>();
		System.out.println("Soso");
		session.setAttribute("kunden", kunden);
		request.getRequestDispatcher("kundeninfos.jsp").forward(request, response);
		
	}

}
