package com.fdm.proj.controller;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.proj.model.User;
import com.fdm.proj.service.ProfileFeedService;
import com.fdm.proj.service.UserService;

/**
 * This controller inherits from {@link FeedController} and manages all requests in the user's profile feed.
 * It generates the user's own posts and manages user actions.
 * @author Kenneth
 *
 */
@Controller
public class ProfileFeedController extends FeedController {

	private UserService userService;
	
	@Autowired
	public ProfileFeedController(ProfileFeedService profileFeedService, HttpServletRequest req) {
		super(profileFeedService, req);
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Handles GET request to own user profile.
	 */
	@Override
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String loadFeed(Model model) {
		
		// if no user instance, redirect to login
		if (model.getAttribute("currentUserId") != null) {
			return "redirect:/login";
		}
		
		// set feedService attributes to current user
		initializeToUser();
		model.addAttribute("posts", feedService.returnFeedPosts());
		return "profile";
	}

	/**
	 * Handles POST request to own user profile, when the current user wishes to write a new post, comment or like their own posts.
	 * @param req
	 * @param modifiedPostId
	 * @param postText
	 * @param postTag
	 * @param commentText
	 * @param commentButton
	 * @param likeButton
	 * @return
	 */
	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public String performTasks(HttpServletRequest req, 
			@RequestParam(required=false) String modifiedPostId, 
			@RequestParam(required=false) String postText,
			@RequestParam(required=false) String postTag,
			@RequestParam(required=false) String commentText, 
			@RequestParam(required=false) String commentButton, 
			@RequestParam(required=false) String likeButton) {
		
		initializeToUser();
		
		// user actions
		writePost(postText, Instant.now(), postTag);
		writeComment(commentText, modifiedPostId, commentButton, Instant.now());
		likePost(likeButton, modifiedPostId);
		edit(req); // TODO refactor to model attribute
		
		return "redirect:/profile";
	}
	

	/**
	 * Handles GET request to other users' profiles.
	 * @param model
	 * @param username Other user's username
	 * @return
	 */
	@RequestMapping(value="/profile/{username}", method=RequestMethod.GET)
	public String loadOtherFeed(Model model, @PathVariable String username) {
		
		// find user subject of interest
		User user = userService.findByUsername(username);
		
		if (user == null) {
			return "redirect:/homefeed"; // TODO redirect to UserNotFound page
		} 
		
		// set feedService attributes to user subject of interest
		initializeToUser(user); 
		model.addAttribute("posts", feedService.returnFeedPosts());
		
		return "profile";
	}
	
	/**
	 * Handles POST requests to other users' profiles, when the current user wishes to comment or like the OTHER user's posts.
	 * @param model
	 * @param username OTHER user
	 * @param modifiedPostId
	 * @param postText
	 * @param postTag
	 * @param commentText
	 * @param commentButton
	 * @param likeButton
	 * @return
	 */
	@RequestMapping(value="/profile/{username}", method=RequestMethod.POST)
	public String performOtherFeedTasks(Model model, @PathVariable String username,
			@RequestParam(required=false) String modifiedPostId, 
			@RequestParam(required=false) String postText,
			@RequestParam(required=false) String postTag,
			@RequestParam(required=false) String commentText, 
			@RequestParam(required=false) String commentButton, 
			@RequestParam(required=false) String likeButton) {
		
		initializeToUser(); // sets own user attributes in Controller first
		User user = userService.findByUsername(username);
		initializeToUser(user); // override user attribute in Service to perform actions on OTHER profiles
		
		// user actions
		writeComment(commentText, commentButton, modifiedPostId, Instant.now());
		likePost(likeButton, modifiedPostId);
				
		return "redirect:/profile/" + username;
	}
	
	/**
	 * This method handles requests to update the user's personal particulars.
	 * @param req
	 * @return
	 */
	public String edit(HttpServletRequest req) {
		
		// downcast to access child methods
		ProfileFeedService feedService = (ProfileFeedService) getFeedService();
				
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String location = req.getParameter("location");
		String occupation = req.getParameter("occupation");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		
		feedService.updateUserDetails(firstname, lastname, location, occupation, password, confirmPassword);
		INFO.info("User " + user.getUsername() + " particulars updated!");
		
		return "profile";
	}

	
}
