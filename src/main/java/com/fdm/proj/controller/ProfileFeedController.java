package com.fdm.proj.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.proj.model.Comment;
import com.fdm.proj.model.Post;
import com.fdm.proj.service.ProfileFeedService;

@Controller
public class ProfileFeedController extends FeedController {


	@Autowired
	public ProfileFeedController(ProfileFeedService profileFeedService) {
		super(profileFeedService);
	}

	
	@Override
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String loadFeed(HttpSession session) {
		
		ProfileFeedService feedService = (ProfileFeedService) getFeedService();
		initUser(session);
		feedService.setUserId(user.getUserId()); 
			
		List<Post> posts = loadPosts();
		session.setAttribute("posts", posts);
				
		HashMap<Integer, List<Comment>> comments = loadComments(posts);
		session.setAttribute("comments", comments);
		
		return "profile";
	}


	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public String performTasks(HttpServletRequest req, HttpSession session, 
			@RequestParam(required=false) String modifiedPostId, 
			@RequestParam(required=false) String postText,
			@RequestParam(required=false) String commentText, 
			@RequestParam(required=false) String commentButton, 
			@RequestParam(required=false) String likeButton) {
		
		initUser(session);
		ProfileFeedService feedService = (ProfileFeedService) getFeedService();
		feedService.setUserId(user.getUserId()); 
				
		List<Post> posts = loadPosts();
		session.setAttribute("posts", posts);
				
		HashMap<Integer, List<Comment>> comments = loadComments(posts);
		session.setAttribute("comments", comments);
		
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
