package com.fdm.proj.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdm.proj.entities.User;
import com.fdm.proj.services.LoginService;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final Logger GENERAL = LogManager.getLogger("com.fdm.proj.servlets.LoginServlet.General");

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
			GENERAL.error("Login success!");
			req.setAttribute("userID", user.getUserId()); // or set to session?
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
			rd.forward(req, resp);
			
		} else {
			GENERAL.error("Login failed!");
			String errorMessage = "Login failed. Please try again.";
			req.setAttribute("loginFailedMessage", errorMessage);
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(req, resp);
		}
	}
}
