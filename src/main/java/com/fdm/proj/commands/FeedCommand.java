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
import com.fdm.proj.entities.User;
import com.fdm.proj.services.FeedService;


public class FeedCommand extends Command {
	
	private FeedService feedService;
	private User user;

	public FeedCommand(ServletContext sc, HttpServletRequest req, HttpServletResponse resp) {
		super(sc, req, resp);
		user = initUser();
	}

	public User initUser() {

		Integer userID = (Integer) req.getSession().getAttribute("currentUserID");
		
		// set current user instance
		if (userID != null) {
			User user = feedService.returnUser(userID);
			req.setAttribute("user", user);
			return user;
		}
		return null;
	}
	
	
	@Override
	public String execute() throws ServletException, IOException {
		
		feedService = (FeedService) sc.getAttribute("feedService");
		HttpSession session = req.getSession(false);
		
		// get all posts
		List<Post> posts = feedService.returnAllPosts();
		session.setAttribute("posts", posts);
		
		// get all comments
		HashMap<Integer, List<Comment>> commentMap = new HashMap<>();
		for (Post p : posts) {			
			int postID = p.getPostId();
			List<Comment> comments = feedService.returnAllPostComments(p);
			commentMap.put(postID, comments);
		}
		session.setAttribute("comments", commentMap);
		
		return "feed";
	}
	
	
	public void write() {
		
		String newPostBody = req.getParameter("post-text-box");
		feedService.userCreatePost(user, newPostBody);
		INFO.info("New Post created by " + user.getUsername());		
	}
	
	
	public void writeComment(Post post) {
		
		Post commentedPost = post;
		String newCommentBody = req.getParameter("comment-text-box");
		feedService.userCreateComment(user, commentedPost, newCommentBody);
		INFO.info("New Comment created by " + user.getUsername() + " on post " + commentedPost.getPostId());			
	}
	
}
