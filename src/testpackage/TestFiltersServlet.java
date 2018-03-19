package testpackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.classes.Nutzer;
import de.databaseOperations.NutzerOperations;

/**
 * Servlet implementation class TestFiltersServlet
 */
@WebServlet("/TestFiltersServlet")
public class TestFiltersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestFiltersServlet() {
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
		String eingabe = request.getParameter("eingabe");
		System.out.println("Mama ");
		HttpSession session = request.getSession();
		Nutzer nutzer = NutzerOperations.nutzerAusDbHolen(1);
		System.out.print(nutzer.getEmail());
		session.setAttribute("nutzer", nutzer);
		// request.setAttribute("nutzerR", nutzer);
		
		
		request.getRequestDispatcher("test.jsp").forward(request, response);
	}

}
