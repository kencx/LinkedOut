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
import com.fdm.proj.services.HomeFeedService;


public class HomeFeedCommand extends FeedCommand {
	
	public HomeFeedCommand(ServletContext sc, HttpServletRequest req, HttpServletResponse resp) {
		super(sc, req, resp);
		fService = (HomeFeedService) sc.getAttribute("hfService");
		initUser();
	}

	
	public String execute() throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		// get all posts
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
		
		
		return "homefeed";
	}
}