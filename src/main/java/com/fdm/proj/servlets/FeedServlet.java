package com.fdm.proj.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.proj.commands.FeedCommand;
import com.fdm.proj.commands.FeedCommandFactory;


@WebServlet("/feed")
public class FeedServlet extends HttpServlet {
	
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		FeedCommandFactory cf = (FeedCommandFactory) req.getServletContext().getAttribute("fcf");
		FeedCommand command = (FeedCommand) cf.createCommand(req.getServletContext(), req, resp);
		String page =  command.execute();
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + page + ".jsp");
		rd.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		FeedCommandFactory cf = (FeedCommandFactory) req.getServletContext().getAttribute("fcf");
		FeedCommand command = (FeedCommand) cf.createCommand(req.getServletContext(), req, resp);
		String page = command.execute();
		
		command.write();
		command.writeComment(null); // get the relevant postID
		
		resp.sendRedirect(req.getContextPath() + "/" + page);
	
	}
}
