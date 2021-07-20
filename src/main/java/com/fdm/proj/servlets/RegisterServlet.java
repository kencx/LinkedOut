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

import com.fdm.proj.services.RegisterService;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	private static final Logger GENERAL = LogManager.getLogger("com.fdm.proj.servlets.RegisterServlet.General");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/register.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		
		RegisterService rs = (RegisterService) getServletContext().getAttribute("registerService");
		boolean status = rs.registerUser(username, password, confirmPassword);
		String errorMessage = rs.getErrorMessage();
		
		
		if (status) {
			GENERAL.error("Account creation success! " + username + "account created");
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(req, resp);
			
		} else {
			GENERAL.error("Account creation failed! Error: " + errorMessage);
			
			req.setAttribute("registerFailedMessage", errorMessage);
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/register.jsp");
			rd.forward(req, resp);
			
		}
		
	}
	
}
