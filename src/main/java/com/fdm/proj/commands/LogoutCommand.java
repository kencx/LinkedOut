package com.fdm.proj.commands;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutCommand extends Command {

	public LogoutCommand(ServletContext sc, HttpServletRequest req, HttpServletResponse resp) {
		super(sc, req, resp);
	}

	@Override
	public String execute() throws ServletException, IOException {
		
		// clear all session data
		HttpSession session = req.getSession();
		int userID = (int) session.getAttribute("currentUserID");
		session.invalidate();
		
		resp.setHeader("Cache-Control", "no-store");
		INFO.info("Session info cleared successfully!");
		
		INFO.info("User " + userID + " logged out successfully");
		return "login";
	}
	
}
