package com.fdm.proj.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.entities.Post;
import com.fdm.proj.entities.User;
import com.fdm.proj.services.PostService;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	
	private static final Logger ERROR = LogManager.getLogger("com.fdm.proj.servlets.Error");
	private static final Logger INFO = LogManager.getLogger("com.fdm.proj.servlets.Info");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserDAO userDAO = (UserDAO) getServletContext().getAttribute("userDAO");
		
		HttpSession session = req.getSession();
		Integer userID = (Integer) session.getAttribute("currentUserID");
		
		User user = userDAO.findById(userID);
		req.setAttribute("user", user);

		PostService ps = (PostService) getServletContext().getAttribute("ps");
		List<Post> posts = ps.returnAllPosts();
		session.setAttribute("posts", posts);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UserDAO userDAO = (UserDAO) getServletContext().getAttribute("userDAO");

		HttpSession session = req.getSession();
		Integer userID = (Integer) session.getAttribute("currentUserID");
		
		User user = userDAO.findById(userID);
		req.setAttribute("user", user);

		PostService ps = (PostService) getServletContext().getAttribute("ps");

		// create new post
		// log new Post created

		String newPostBody = req.getParameter("post-text-box");
		ps.userCreatePost(user, newPostBody);
		INFO.info("New Post created by " + user.getUsername());

		// get all comments
		
		// create new comment on post
		// log new comment created
		
		
		List<Post> posts = ps.returnAllPosts();
		session.setAttribute("posts", posts);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
		rd.forward(req, resp);
	
	}
}
