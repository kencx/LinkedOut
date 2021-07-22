package com.fdm.proj.commands;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.proj.entities.Comment;
import com.fdm.proj.entities.Post;
import com.fdm.proj.services.ProfileFeedService;


public class ProfileFeedCommand extends FeedCommand {
	
	public ProfileFeedCommand(ServletContext sc, HttpServletRequest req, HttpServletResponse resp) {
		super(sc, req, resp);
		fService = (ProfileFeedService) sc.getAttribute("pfService");
		initUser();
	}
	
	@Override
	public String execute() throws ServletException, IOException {
		
		// downcast to access child methods
		ProfileFeedService fService = (ProfileFeedService) getfService();
		fService.setUserId(getUser().getUserId()); // set id to retrieve correct user profile info
		
		HttpSession session = req.getSession();
		
		// get user's posts
		List<Post> posts = fService.returnFeedPosts();
		session.setAttribute("posts", posts);
		
		// get all comments
		HashMap<Integer, List<Comment>> commentMap = new HashMap<>();
		for (Post p : posts) {			
			int postID = p.getPostId();
			List<Comment> comments = fService.returnAllPostComments(p);
			commentMap.put(postID, comments);
		}
		session.setAttribute("comments", commentMap);
		
		return "profile";
	}
	
	
	public String edit() {
		
		// downcast to access child methods
		ProfileFeedService fService = (ProfileFeedService) getfService();
				
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String location = req.getParameter("location");
		String occupation = req.getParameter("occupation");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		
		fService.updateUserDetails(firstname, lastname, location, occupation, password, confirmPassword);
		INFO.info("User " + user.getUsername() + " particulars updated!");
		
		return "profile";
	}

}
