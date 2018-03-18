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

import de.classes.Produkt;
import de.databaseOperations.ProduktOperations;

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
		
		List<String> messages = new ArrayList<>();
		HttpSession session = request.getSession();
		Produkt produkt = (Produkt) request.getAttribute("produkt");
		ProduktOperations.entferneProdukt(produkt);
		session.removeAttribute("produkt");
		messages.add("Das Produkt wurde aus den Sortiment entfernt !");
		request.setAttribute("messages", messages);
		request.getRequestDispatcher("produktinfos.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String mengeS = request.getParameter("menge");
		String preisS = request.getParameter("preis");
		HttpSession session = request.getSession();
		Produkt produkt = (Produkt) session.getAttribute("produkt");
		session.removeAttribute("produkt");
		int menge = 0;
		double preis = 0.00;
		List<String> messages = new ArrayList<String>();
		
		try {
			menge = Integer.parseInt(mengeS);
			preis = Double.parseDouble(preisS);
		} catch(NumberFormatException nfe) {
			
		}
		
		
		if(!name.contains("") && preis >= 0) {
			if(!name.contains("") && name != produkt.getName()) {
				produkt.setName(name);
				
			} else if(menge != produkt.getMenge() && name.contains("") && preis >= 0) {
				produkt.setMenge(menge);
				
			} else if(preis >= 0 && preis != produkt.getPreis() && name.contains("")) {
				produkt.setName(name);
			} else if(!name.contains("") && name != produkt.getName() && menge != produkt.getMenge() && preis >= 0) {
				produkt.setName(name);
				produkt.setMenge(menge);
			} else if(name.contains("") && menge != produkt.getMenge() && preis >= 0 && preis != produkt.getPreis()) {
				produkt.setMenge(menge);
				produkt.setPreis(preis);
				
				
			} else if(!name.contains("") && name != produkt.getName() && menge != produkt.getMenge() && preis >= 0 && preis != produkt.getPreis())  {
				produkt.setName(name);
				produkt.setMenge(menge);
				produkt.setPreis(preis)
				;
			}
			
			
			ProduktOperations.updateProdukt(produkt);
			messages.add("Die Daten wurden erfolgreich aktualisiert.");
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("produktinfos.jsp").forward(request, response);
			
		} else if(name.contains("") && menge == 0 && preis == 0)  {
			messages.add("Das Produkt wurde nicht geändert.");
			
			request.setAttribute("messages", messages);
			
			request.getRequestDispatcher("produkt_bearbeiten.jsp").forward(request, response);
		}
	}

}
