package com.fdm.proj.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdm.proj.entities.User;
import com.fdm.proj.services.LoginService;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final Logger ERROR = LogManager.getLogger("com.fdm.proj.servlets.Error");
	private static final Logger INFO = LogManager.getLogger("com.fdm.proj.servlets.Info");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		LoginService ls = (LoginService) req.getServletContext().getAttribute("loginService");
		User user = ls.verifyUser(username, password);

		if(user != null) {
			INFO.info("Login success!");
			HttpSession session = req.getSession();
			session.setAttribute("currentUserID", user.getUserId());
			
			resp.sendRedirect(req.getContextPath() + "/home");
			
		} else {
			ERROR.error("Login failed!");
			String errorMessage = "Login failed. Please try again.";
			req.setAttribute("loginFailedMessage", errorMessage);
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(req, resp);
		}
	}
}
