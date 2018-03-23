package de.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.classes.Produkt;
import de.databaseOperations.ProduktUpdateOperations;
import de.logik.Regex;

/**
 * Servlet implementation class ProduktBearbeitenServlet
 */
@WebServlet("/ProduktBearbeitenServlet")
public class ProduktBearbeitenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduktBearbeitenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<>();
		String rolle = (String) session.getAttribute("rolle");
		request.setAttribute("messages", messages);
		if(rolle.contains("admin")) {
		
			Produkt produkt = (Produkt) request.getAttribute("produkt");
			System.out.println(produkt);
		//ProduktUpdateOperations.entferneProdukt(produkt);
			session.removeAttribute("produkt");
			messages.add("Das Produkt wurde aus den Sortiment entfernt !");
		
			request.getRequestDispatcher("produktinfos.jsp");
		} else {
			messages.add(" Sie haben nicht die Berechtigung für den Zugriff auf diese Funktionen.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<>();
		String rolle = (String) session.getAttribute("rolle");
		request.setAttribute("messages", messages);
		if(rolle.contains("admin")) {
		
		
		session.removeAttribute("produkte");
		
		if(session.getAttribute("produkt")==null){
			messages.add("Sie haben kein Produkt ausgewählt. Wählen Sie bitte ein Produkt aus!");
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("produktinfos.jsp").forward(request, response);
			}else{
		
				Produkt produkt = (Produkt) session.getAttribute("produkt");
				session.removeAttribute("produkt");
		
		
				String versanddauer = request.getParameter("versanddauer");
				String mengeS = request.getParameter("menge");
				String status= request.getParameter("status");
		
				System.out.println("bearbeitenservlet");
				System.out.println(versanddauer);
				System.out.println(mengeS);
				System.out.println(status);
				System.out.println(mengeS.equals(""));
		
				boolean mengenfeldgeprueft = Regex.pruefeNurZahlen(mengeS);
				boolean versanddauerfeldgeprueft = Regex.pruefeNurZahlen(versanddauer);
				System.out.println(mengenfeldgeprueft);
				System.out.println(versanddauerfeldgeprueft);
			if(mengenfeldgeprueft && versanddauerfeldgeprueft){
		
				int versanddauergeprueft = Integer.parseInt(versanddauer);
				int mengegeprueft = Integer.parseInt(mengeS);
				ProduktUpdateOperations.updateProdukt(produkt.getProdukt_id(), mengegeprueft, versanddauergeprueft, status);
				messages.add("Die Daten wurden erfolgreich aktualisiert.");
	
			}	else if (mengeS.equals("") && versanddauer.equals("")){
				int versanddauergeprueft = -1;
				int mengegeprueft = -1;
				System.out.println(mengegeprueft);
				ProduktUpdateOperations.updateProdukt(produkt.getProdukt_id(), mengegeprueft, versanddauergeprueft, status);
				messages.add("Die Daten wurden erfolgreich aktualisiert.");
			}else{
				messages.add("Keine Zahlen eingegeben!");
			}
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("produktinfos.jsp").forward(request, response);
			
		
		}
		} else {
			messages.add(" Sie haben nicht die Berechtigung für den Zugriff auf diese Funktionen.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
