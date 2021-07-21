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

import com.fdm.proj.services.LoginService;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	
	private static final Logger INFO = LogManager.getLogger("com.fdm.proj.servlets.Info");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// clear all session data
		HttpSession session = req.getSession();
		int userID = (int) session.getAttribute("currentUserID");
		
		session.removeAttribute("currentUserID");
		session.invalidate();

		resp.setHeader("Cache-Control", "no-store");
		INFO.info("Session info cleared successfully!");
		
		resp.sendRedirect(req.getContextPath() + "/login");
		// TODO log here loggged out successfully
		INFO.info("User " + userID + " logged out successfully");
	}
}
