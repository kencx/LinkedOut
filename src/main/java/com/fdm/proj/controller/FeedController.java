package com.fdm.proj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.fdm.proj.model.Post;
import com.fdm.proj.model.User;
import com.fdm.proj.service.FeedService;

@Controller
public abstract class FeedController {

	protected static final Logger INFO = LogManager.getLogger(FeedController.class);
	protected static final Logger ERROR = LogManager.getLogger(FeedController.class);
	
	protected FeedService feedService;
	protected User user;
	
	public FeedController(FeedService feedService) {
		this.feedService = feedService;
	}
	
	public abstract String loadFeed(HttpSession session);
	
	public abstract String performTasks(HttpServletRequest req);
	
	
	public void initUser(HttpSession session) {
		// set current user instance
		Integer userId = (Integer) session.getAttribute("currentUserId");

		if (userId != null) {
			user = feedService.returnUser(userId);
		}
		
		// else throw UserNotFoundException
	}
	
	public void writePost(HttpServletRequest req) {

		// only when text box form is not empty
		String postText = req.getParameter("post-text-box");
		
		if (postText != null && postText != "") {
			
			feedService.userCreatePost(user, postText);
			INFO.info("New Post created by " + user.getUsername());		
		}
	}

	public void writeComment(HttpServletRequest req) {

		// only when text box form is not empty and post exists
		String commentedPostId = req.getParameter("changedPost"); 
		String commentText = req.getParameter("comment-text-box");
		
		if (commentedPostId != null && commentText != null && commentText != "" && req.getParameter("comment-button") != null) {

			Post commentedPost = feedService.returnPost(Integer.parseInt(commentedPostId));			
			feedService.userCreateComment(user, commentedPost, commentText);
			INFO.info("New Comment created by " + user.getUsername() + " on post " + commentedPost.getPostId());			
		}	
	}

	public void likePost(HttpServletRequest req) {

		String likedPostId = req.getParameter("changedPost"); 
		String liked = req.getParameter("like-button"); 
		
		if (likedPostId != null && liked != null && req.getParameter("like-button") != null) {
			
			Post likedPost = feedService.returnPost(Integer.parseInt(likedPostId));
			feedService.likePost(user, likedPost);
			INFO.info(user.getUsername() + " liked post " + likedPost.getPostId() + " for " + likedPost.getUsersWhoLiked().size() + " likes.");
		}
	}
	
	public void performSearch() {
		
		// accept inputs
		
		// if input not null, go to search with mapped variable
		
	}
	
	
	public FeedService getFeedService() {
		return feedService;
	}

	public void setFeedService(FeedService feedService) {
		this.feedService = feedService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
