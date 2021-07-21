package com.fdm.proj.commands;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.proj.entities.User;
import com.fdm.proj.services.LoginService;


public class LoginCommand extends Command {
	
	
	public LoginCommand(ServletContext sc, HttpServletRequest req, HttpServletResponse resp) {
		super(sc, req, resp);
	}

	@Override
	public String execute() throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		LoginService ls = (LoginService) sc.getAttribute("loginService");
		User user = ls.verifyUser(username, password);
		
		if(user != null) {
			
			INFO.info("Login success!");
			HttpSession session = req.getSession();
			session.setAttribute("currentUserID", user.getUserId());
			return "feed";
			
		} else {
			
			ERROR.error("Login failed!");
			String errorMessage = "Login failed. Please try again.";
			req.setAttribute("loginFailedMessage", errorMessage);
			return "login";
		}
	}
}
