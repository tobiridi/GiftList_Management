package be.Jadoulle_Declercq.Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.Jadoulle_Declercq.JavaBeans.Customer;

public class AuthenticationFilter implements Filter {
	private FilterConfig filterConfig = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
		this.filterConfig = filterConfig;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {
			//TODO : remove, just test
			System.out.println(this.filterConfig.getFilterName());
			
			HttpSession session = ((HttpServletRequest) request).getSession(false);
			if(session != null) {
				Customer customerLog = (Customer) session.getAttribute("customerLog");
				if(customerLog != null) {
					//customer in session
					chain.doFilter(request, response);
				}
				//no customer in session
				else {
					((HttpServletResponse) response).sendRedirect("Index");
				}
			}
			else {
				//no session
				((HttpServletResponse) response).sendRedirect("Index");			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
