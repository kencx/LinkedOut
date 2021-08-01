package com.fdm.proj.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
	
	protected HttpServletRequest req;
	protected FeedService feedService;
	protected User user;
	
	public FeedController(FeedService feedService, HttpServletRequest req) {
		this.feedService = feedService;
		this.req = req;
	}
	
	/**
	 * Abstract method that handles all incoming requests to the respective feed. 
	 * @param model
	 * @return String Respective feed page
	 */
	public abstract String loadFeed(Model model);
		
	
	/**
	 * Initializes the key User attributes in FeedController and ProfileFeedService.
	 * Sets user attribute to OWN user account.
	 */
	public void initializeToUser() {
		
		Integer userId = (Integer) req.getSession().getAttribute("currentUserId");		
		user = feedService.returnUser(userId);
		req.setAttribute("user", user);
		
		// set specific user profile in ProfileFeedService
		if (this instanceof ProfileFeedController) {
			ProfileFeedService feedService = (ProfileFeedService) getFeedService();
			feedService.setUser(user);
		}
	}
	
	/**
	 * Initializes the key User attributes in ProfileFeedService. This method is necessary to load other user profiles.
	 * Sets user attributes to OTHER users.
	 * @param user Other user of interest
	 */
	public void initializeToUser(User user) {
		req.setAttribute("user", user);
		
		if (this instanceof ProfileFeedController) {
			ProfileFeedService feedService = (ProfileFeedService) getFeedService();
			feedService.setUser(user);
		}
	}
	
	
	// user actions
	
	public void writePost(String postText, Instant timeCreated, String tagNames) {
		
		List<Tag> tags = new ArrayList<>();
		if (tagNames != null && tagNames != "") {
			List<String> tagNameList = Arrays.stream(tagNames.split(",")).map(String::trim).collect(Collectors.toList());
			

			for (String name : tagNameList) {
				Tag tag = new Tag(name);
				tags.add(tag);
			}
		}

		if (postText != null && postText != "" && timeCreated != null) {
			
			feedService.userCreatePost(user, postText, timeCreated, tags);
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
