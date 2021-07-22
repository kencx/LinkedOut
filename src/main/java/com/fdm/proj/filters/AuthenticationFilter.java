package com.fdm.proj.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/homefeed", "/profile", "/search", "/logout"})
public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		boolean authenticated = false;
		HttpSession session = req.getSession();
		Integer currentUserId = (Integer) session.getAttribute("currentUserId");

		if (currentUserId != null) {
			authenticated = true;
		}
		
		if (authenticated) {
			// TODO log user authenticated
			chain.doFilter(request, response);
		} else {
			// TODO log user authenticated failed, redirecting back to login
			resp.sendRedirect(req.getContextPath() + "/login");
		}	
	}

	@Override
	public void destroy() {
		
	}

}
