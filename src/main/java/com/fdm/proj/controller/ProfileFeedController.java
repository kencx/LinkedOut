package com.fdm.proj.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		
		// get user's posts
		List<Post> posts = feedService.returnFeedPosts();
		session.setAttribute("posts", posts);
		
		// get all comments
		HashMap<Integer, List<Comment>> commentMap = new HashMap<>();
		for (Post p : posts) {			
			int postID = p.getPostId();
			List<Comment> comments = feedService.returnAllPostComments(p);
			commentMap.put(postID, comments);
		}
		session.setAttribute("comments", commentMap);
		
		return "profile";
	}


	@Override
	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public String performTasks(HttpServletRequest req) {
		
		ProfileFeedService feedService = (ProfileFeedService) getFeedService();
		HttpSession session = req.getSession();
		initUser(session);
		feedService.setUserId(user.getUserId()); 
		
		// get user's posts
		List<Post> posts = feedService.returnFeedPosts();
		session.setAttribute("posts", posts);
		
		// get all comments
		HashMap<Integer, List<Comment>> commentMap = new HashMap<>();
		for (Post p : posts) {			
			int postID = p.getPostId();
			List<Comment> comments = feedService.returnAllPostComments(p);
			commentMap.put(postID, comments);
		}
		session.setAttribute("comments", commentMap);
		
		// write post
		writePost(req);
		
		// write comment
		writeComment(req);
		
		// like post
		likePost(req);
		
		// edit profile
		edit(req);
		
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
