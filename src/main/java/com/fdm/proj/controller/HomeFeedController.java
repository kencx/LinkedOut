package com.fdm.proj.controller;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.proj.service.HomeFeedService;

/**
 * This controller inherits from {@link FeedController} and manages all requests in the user's home feed.
 * It generates the entire feed and manages user actions.
 * @author Kenneth
 *
 */
@Controller
public class HomeFeedController extends FeedController {

	@Autowired
	public HomeFeedController(HomeFeedService homeFeedService, HttpServletRequest req) {
		super(homeFeedService, req);
	}
	
	@Override
	@RequestMapping(value="/homefeed", method=RequestMethod.GET)
	public String loadFeed(Model model) {
		
		// if no user instance, redirect to login
		if (model.getAttribute("currentUserId") != null) {
			return "redirect:/login";
		}
		
		initializeToUser();
		model.addAttribute("posts", feedService.returnFeedPosts());
		return "homefeed";
	}


	@RequestMapping(value="/homefeed", method=RequestMethod.POST)
	public String executeUserActions (
			@RequestParam(required=false) String tags,
			@RequestParam(required=false) String modifiedPostId,
			@RequestParam(required=false) String postText,
			@RequestParam(required=false) String postTag,
			@RequestParam(required=false) String commentText,
			@RequestParam(required=false) String commentButton,
			@RequestParam(required=false) String likeButton) {
		
		initializeToUser();
		
		// user actions
		writePost(postText, Instant.now(), tags);
		writeComment(commentText, commentButton, modifiedPostId, Instant.now());		
		likePost(likeButton, modifiedPostId);
		
		return "redirect:/homefeed";
	}

}
