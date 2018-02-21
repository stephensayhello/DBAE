package de.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class BaseFilter implements Filter {
	protected FilterConfig config;
	
	public BaseFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		config = null;
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(arg0, arg1);
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		
	}

}
