package com.fdm.proj.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.proj.commands.ProfileFeedCommand;
import com.fdm.proj.commands.ProfileFeedCommandFactory;


@WebServlet("/profile")
public class ProfileServlet extends HttpServlet{
	
	private ProfileFeedCommandFactory cf;
	private ProfileFeedCommand command;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		cf = (ProfileFeedCommandFactory) req.getServletContext().getAttribute("pcf");
		command = cf.createCommand(req.getServletContext(), req, resp);
		String page =  command.execute();
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + page + ".jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		cf = (ProfileFeedCommandFactory) req.getServletContext().getAttribute("pcf");
		command = cf.createCommand(req.getServletContext(), req, resp);
		String page = command.execute();
		
		command.writePost();
		command.writeComment(); 
		
		// TODO edit personal particulars
		command.edit();
		
		resp.sendRedirect(req.getContextPath() + "/" + page);
	}
}
