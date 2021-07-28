package com.fdm.proj.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.fdm.proj.model.Comment;
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
		
	public void initUser(HttpSession session) {
		// set current user instance
		Integer userId = (Integer) session.getAttribute("currentUserId");

		if (userId != null) {
			user = feedService.returnUser(userId);
		}
		
		// else throw UserNotFoundException
	}	
	
	
	// generate feed
	public List<Post> loadPosts() {
		return feedService.returnFeedPosts();
	}
	
	public HashMap<Integer, List<Comment>> loadComments(List<Post> posts) {
		HashMap<Integer, List<Comment>> commentMap = new HashMap<>();
		for (Post p : posts) {			
			int postID = p.getPostId();
			List<Comment> comments = feedService.returnAllPostComments(p);
			commentMap.put(postID, comments);
		}
		return commentMap;
	}
	
	
	// user actions
	public void writePost(String postText) {

		if (postText != null && postText != "") {
			
			feedService.userCreatePost(user, postText);
			INFO.info("New Post created by " + user.getUsername());		
		}
	}

	public void writeComment(String commentText, String commentButton, String modifiedPostId) {

		// post must exist, comment box must not be empty and comment button must be clicked
		if (modifiedPostId != null && commentText != null && commentText != "" && commentButton != null) {

			Post commentedPost = feedService.returnPost(Integer.parseInt(modifiedPostId));			
			feedService.userCreateComment(user, commentedPost, commentText);
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
