package com.fdm.proj.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebFilter(urlPatterns = {"/homefeed", "/profile", "/search", "/logout"})
public class AuthenticationFilter implements Filter {

	private static final Logger ERROR = LogManager.getLogger(AuthenticationFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		boolean authenticated = false;
		HttpSession session = req.getSession(false);
		Integer currentUserId = (Integer) session.getAttribute("currentUserId");

		if (currentUserId != null && session != null) {
			authenticated = true;
		}
		
		if (authenticated) {
			chain.doFilter(request, response);
		} else {
			ERROR.error("Authentication failed, redirecting to login.");
			resp.sendRedirect(req.getContextPath() + "/login");
		}	
	}

	@Override
	public void destroy() {
		
	}

}
