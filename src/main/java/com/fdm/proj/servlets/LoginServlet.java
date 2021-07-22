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

import com.fdm.proj.commands.CommandFactory;
import com.fdm.proj.commands.LoginCommand;
import com.fdm.proj.commands.LoginCommandFactory;
import com.fdm.proj.commands.ProfileFeedCommand;
import com.fdm.proj.commands.ProfileFeedCommandFactory;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private LoginCommandFactory cf;
	private LoginCommand command;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		cf = (LoginCommandFactory) req.getServletContext().getAttribute("lcf");
		command = cf.createCommand(req.getServletContext(), req, resp);
		String page = command.execute();
		
		if (page.equals(req.getServletPath().substring(1))) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + page + ".jsp");
			rd.forward(req, resp);
			
		} else {
			resp.sendRedirect(req.getContextPath() + "/" + page);
			
		}
	}
}
