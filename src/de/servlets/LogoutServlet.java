package de.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.classes.Warenkorb;
import de.databaseOperations.WarenkorbOperations;

/**
 * @author paul Blanke 
 * dieses Servlet loggt den Nutzer aus.
 * Der Warenkorb wird beim Ausloggen in die Db geschrieben und die Session wird entfernt. 
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Daten holen
		HttpSession session = request.getSession();
		Warenkorb warenkorb = (Warenkorb) session.getAttribute("warenkorb");
		if(warenkorb != null) {
			WarenkorbOperations.anlegenWarenkorb(warenkorb);
		}
		
		session.invalidate();
		request.getSession().removeAttribute("session");
		request.getRequestDispatcher("artikeluebersicht.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// DoPost wird nicht verwendet
		
		
	}

}
