package de.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Servlet Filter implementation class XSSFilter
 * @author paul Blanke
 * Diese Klasse Filtert die Eingabe nach XSS Angriffe
 * Zum gr&oessten Teil Analog zum Learnweb allerdings wurden weiteren Pr&uefungen erg&aenzt.
 */
@WebFilter("/*")
public class XSSFilter extends BaseFilter implements Filter {
       
    /**
     * @see BaseFilter#BaseFilter()
     */
    public XSSFilter() {
        super();
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
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		XSSRequestWrapper wrapper = new XSSRequestWrapper((HttpServletRequest) request);
		chain.doFilter(wrapper, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
class XSSRequestWrapper extends HttpServletRequestWrapper implements HttpServletRequest {

	public XSSRequestWrapper(HttpServletRequest request) {
		super(request);
		
	}
	/**
	 * Diese Methode &ueberpr&ueft die Eingabe anhand vorgegebener Parameter und erstetzt diese.
	 * @param str Eingabe
	 * @return bereinigte Eingabe.
	 */
	public String getParameter(String str) {
		str = super.getParameter(str);
		if(str != null) {
			Pattern scriptPattern = null;
			scriptPattern = Pattern.compile("javascript", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll(" ");
			scriptPattern = Pattern.compile("onload(.*?)", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll(" ");
			scriptPattern = Pattern.compile("onmouseover=", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll("");
			scriptPattern = Pattern.compile("alert()", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll("");
			scriptPattern = Pattern.compile("<script>", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll("");
			scriptPattern = Pattern.compile("function", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll("");
			
			
			
			
		}
		return str;
	}
	
}

