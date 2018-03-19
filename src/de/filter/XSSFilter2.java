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
 * Servlet Filter implementation class XSSFilter2
 */
@WebFilter("/*")
public class XSSFilter2 implements Filter {

    /**
     * Default constructor. 
     */
    public XSSFilter2() {
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
		XSSRequestWrapp wrapper = new XSSRequestWrapp((HttpServletRequest) request);
		chain.doFilter(wrapper, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
class XSSRequestWrapp extends HttpServletRequestWrapper implements HttpServletRequest {

	public XSSRequestWrapp(HttpServletRequest request) {
		super(request);
		
	}
	
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
			scriptPattern = Pattern.compile("SELECT *", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll("");
			scriptPattern = Pattern.compile("function", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll("");
			scriptPattern = Pattern.compile("Delete", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll("");
			scriptPattern = Pattern.compile("Insert into", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll("");
			scriptPattern = Pattern.compile(" ;", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll("");
			scriptPattern = Pattern.compile(" ('') ", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll("");
			scriptPattern = Pattern.compile(" ( )", Pattern.CASE_INSENSITIVE);
			str = scriptPattern.matcher(str).replaceAll("");
			
			
			
			
		}
		return str;
	}
	
}
