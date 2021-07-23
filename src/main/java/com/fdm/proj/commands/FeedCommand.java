package com.fdm.proj.commands;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.internal.build.AllowSysOut;

import com.fdm.proj.entities.Post;
import com.fdm.proj.entities.User;
import com.fdm.proj.services.FeedService;


public abstract class FeedCommand extends Command {

	protected FeedService fService;
	protected User user;

	public FeedCommand(ServletContext sc, HttpServletRequest req, HttpServletResponse resp) {
		super(sc, req, resp);
	}

	public void initUser() {
		// set current user instance
		Integer userId = (Integer) req.getSession().getAttribute("currentUserId");

		if (userId != null) {
			user = fService.returnUser(userId);
			req.setAttribute("user", user);
		} else {
			
		}
	}

	public void writePost() {

		// only when text box form is not empty
		String postText = req.getParameter("post-text-box");
		
		if (postText != null && postText != "") {	
			
			fService.userCreatePost(user, postText);
			INFO.info("New Post created by " + user.getUsername());		
		}
	}

	public void writeComment() {

		// only when text box form is not empty and post exists
		String commentedPostId = req.getParameter("changedPost"); 
		String commentText = req.getParameter("comment-text-box");
		
		if (commentedPostId != null && commentText != null && commentText != "" && req.getParameter("comment-button") != null) {

			Post commentedPost = fService.returnPost(Integer.parseInt(commentedPostId));			
			fService.userCreateComment(user, commentedPost, commentText);
			INFO.info("New Comment created by " + user.getUsername() + " on post " + commentedPost.getPostId());			
		}	
	}

	public void likePost() {

		String likedPostId = req.getParameter("changedPost"); 
		String liked = req.getParameter("like-button"); 
		
		if (likedPostId != null && liked != null && req.getParameter("like-button") != null) {
			
			Post likedPost = fService.returnPost(Integer.parseInt(likedPostId));
			fService.likePost(user, likedPost);
			INFO.info(user.getUsername() + " liked post " + likedPost.getPostId() + " for " + likedPost.getUsersWhoLiked().size() + " likes.");
		}
	}
	
	
	public FeedService getfService() {
		return fService;
	}

	public void setfService(FeedService fService) {
		this.fService = fService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
