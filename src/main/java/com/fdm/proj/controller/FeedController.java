package com.fdm.proj.controller;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.fdm.proj.model.Comment;
import com.fdm.proj.model.Post;
import com.fdm.proj.model.Tag;
import com.fdm.proj.model.User;
import com.fdm.proj.service.FeedService;
import com.fdm.proj.service.ProfileFeedService;

/**
 * This controller is an abstract class that handles all common requests regarding post feed generation and user actions.
 * Users can create posts and comments or like posts.
 * @author Kenneth
 *
 */
@Controller
public abstract class FeedController {

	protected static final Logger INFO = LogManager.getLogger(FeedController.class);
	protected static final Logger ERROR = LogManager.getLogger(FeedController.class);
	
	protected HttpSession session;
	protected FeedService feedService;
	protected User user;
	
	public FeedController(FeedService feedService, HttpSession session) {
		this.feedService = feedService;
		this.session = session;
	}
	
	/**
	 * Abstract method that handles all incoming requests to the respective feed. 
	 * @param model
	 * @return String Respective feed page
	 */
	public abstract String loadFeed(Model model);
		
	public void initializeToUser() {
		
		// set current user instance
		Integer userId = (Integer) session.getAttribute("currentUserId");

		if (userId != null) {
			user = feedService.returnUser(userId);
		}
		
		// TODO else throw usernotfoundexception, kick back to login?
		
		if (this instanceof ProfileFeedController) {
			ProfileFeedService feedService = (ProfileFeedService) getFeedService();
			feedService.setUserId(user.getUserId());
		}
	}
	
	
	// user actions
	
	public void writePost(String postText, Instant timeCreated, String tagName) {
		
		Tag tag = null;
		if (tagName != null && tagName != "") {
			tag = new Tag(tagName);
		}

		if (postText != null && postText != "" && timeCreated != null) {
			
			feedService.userCreatePost(user, postText, timeCreated, tag);
			INFO.info("New Post created by " + user.getUsername());
		}
	}

	public void writeComment(String commentText, String commentButton, String modifiedPostId, Instant timeCreated) {

		// post must exist, comment box must not be empty and comment button must be clicked
		if (modifiedPostId != null && commentText != null && commentText != "" && commentButton != null) {

			Post commentedPost = feedService.returnPost(Integer.parseInt(modifiedPostId));			
			feedService.userCreateComment(user, commentedPost, commentText, timeCreated);
			INFO.info("New Comment created by " + user.getUsername() + " on post " + commentedPost.getPostId());			
		}	
	}

	public void likePost(String likeButton, String modifiedPostId) {

		if (modifiedPostId != null && likeButton != null) {
			
			Post likedPost = feedService.returnPost(Integer.parseInt(modifiedPostId));
			feedService.likePost(user, likedPost);
			INFO.info(user.getUsername() + " liked post " + likedPost.getPostId() + " for " + likedPost.getUsersWhoLiked().size() + " likes.");
		}
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
