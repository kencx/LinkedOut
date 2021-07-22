package com.fdm.proj.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdm.proj.commands.LoginCommand;
import com.fdm.proj.commands.LoginCommandFactory;
import com.fdm.proj.commands.LogoutCommand;
import com.fdm.proj.commands.LogoutCommandFactory;



@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	
	private LogoutCommandFactory cf;
	private LogoutCommand command;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		cf = (LogoutCommandFactory) req.getServletContext().getAttribute("lgcf");
		command = cf.createCommand(req.getServletContext(), req, resp);
		String page = command.execute();
		
		resp.sendRedirect(req.getContextPath() + "/" + page);
	}
}
