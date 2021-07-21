package com.fdm.proj.commands;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.proj.services.RegisterService;


public class RegisterCommand extends Command {

	
	public RegisterCommand(ServletContext sc, HttpServletRequest req, HttpServletResponse resp) {
		super(sc, req, resp);
	}

	@Override
	public String execute() throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		
		RegisterService rs = (RegisterService) sc.getAttribute("registerService");
		boolean status = rs.registerUser(username, password, confirmPassword);
		
		if (status) {
			
			INFO.info("Account creation success! " + username + " account created");
			return "feed";
			
		} else {
			
			String errorMessage = rs.getErrorMessage();
			ERROR.error("Account creation failed! Error: " + errorMessage);			
			req.setAttribute("registerFailedMessage", errorMessage);
			return "login";
		}
	}
}
