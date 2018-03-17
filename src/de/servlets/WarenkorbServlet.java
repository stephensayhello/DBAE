package de.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.classes.Produkt;
import de.classes.Produkt_Anzahl_Zuordnung;
import de.classes.Warenkorb;

/**
 * Servlet implementation class WarenkorbServlet
 */
@WebServlet("/WarenkorbServlet")
public class WarenkorbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WarenkorbServlet() {
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
		if ((Warenkorb) request.getAttribute("warenkorb") != null) {
			Warenkorb warenkorb = (Warenkorb) request.getAttribute("warenkorb");
			List<Produkt> warenkorbinhalt = (List<Produkt>) session.getAttribute("warenkorbinhalt");
			for (int i = 0; i < warenkorbinhalt.size(); i++) {
				System.out.println(warenkorbinhalt.get(i).getAnzahl());

			}
			session.setAttribute("warenkorb", warenkorb);
			session.setAttribute("warenkorbinhalt", warenkorb.getInhalt());
		}

		request.getRequestDispatcher("warenkorb.jsp").forward(request, response);

	}

}
