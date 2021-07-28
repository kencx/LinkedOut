package com.fdm.proj.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.proj.model.Comment;
import com.fdm.proj.model.Post;
import com.fdm.proj.service.ProfileFeedService;

/**
 * This controller inherits from {@link FeedController} and manages all requests in the user's profile feed.
 * It generates the user's own posts and manages user actions.
 * @author Kenneth
 *
 */
@Controller
public class ProfileFeedController extends FeedController {

	@Autowired
	public ProfileFeedController(ProfileFeedService profileFeedService, HttpSession session) {
		super(profileFeedService, session);
	}
	
	@Override
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String loadFeed(Model model) {
		
		initializeToUser();
		model.addAttribute("posts", feedService.returnFeedPosts());
		model.addAttribute("comments", loadComments(feedService.returnFeedPosts()));
		
		return "profile";
	}


	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public String performTasks(HttpServletRequest req, 
			@RequestParam(required=false) String modifiedPostId, 
			@RequestParam(required=false) String postText,
			@RequestParam(required=false) String commentText, 
			@RequestParam(required=false) String commentButton, 
			@RequestParam(required=false) String likeButton) {
		
		initializeToUser();
		
		// user actions
		writePost(postText);
		writeComment(commentText, modifiedPostId, commentButton);
		likePost(likeButton, modifiedPostId);
		edit(req); // TODO refactor to model attribute
		
		return "redirect:/profile";
	}
	
	
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
