package de.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 * @author Benjamin Gajewski
 * Dieser Filter filtert die *.jsp Seiten und leitet unberechtigte Anfragen auf die artikeluebersicht.jsp um.
 */
@WebFilter(urlPatterns = { "*.jsp" })
public class LoginFilter implements Filter {

	private static final String[] NUR_FUER_KUNDE = { "/profil.jsp", "/bestellung.jsp", "/profilaendern.jsp",
			"/produktinfos.jsp", "/profilinfos.jsp" };

	private static final String[] NUR_FUER_ADMIN = { "/produkt_anlegen.jsp", "/produkt_bearbeiten.jsp",
			"/produktgruppe_bearbeiten.jsp", "/produktinfos.jsp", "/bestellungadmin.jsp", "/kundenuebersicht.jsp" };

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * Diese methode unterscheidet die Weiterleitung anhand der Rolle
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String eingeloggt = (String) ((HttpServletRequest) request).getSession().getAttribute("rolle");
		String path = ((HttpServletRequest) request).getRequestURI()
				.substring(((HttpServletRequest) request).getContextPath().length());

		if (eingeloggt == null) {
			if (Arrays.asList(NUR_FUER_ADMIN).contains(path)) {
				((HttpServletResponse) response).sendRedirect("artikeluebersicht.jsp");
			} else if (Arrays.asList(NUR_FUER_KUNDE).contains(path)) {
				((HttpServletResponse) response).sendRedirect("artikeluebersicht.jsp");
			} else {
				chain.doFilter(request, response);
			}

		} else if (eingeloggt.equals("kunde")) {

			if (Arrays.asList(NUR_FUER_ADMIN).contains(path)) {
				((HttpServletResponse) response).sendRedirect("artikeluebersicht.jsp");
			} else {
				chain.doFilter(request, response);
			}

		} else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
