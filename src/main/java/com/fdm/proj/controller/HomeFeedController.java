package com.fdm.proj.controller;

import javax.servlet.http.HttpSession;

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
	public HomeFeedController(HomeFeedService homeFeedService, HttpSession session) {
		super(homeFeedService, session);
	}
	
	@Override
	@RequestMapping(value="/homefeed", method=RequestMethod.GET)
	public String loadFeed(Model model) {
		
		model.addAttribute("posts", feedService.returnFeedPosts());
		model.addAttribute("comments", loadComments(feedService.returnFeedPosts()));
		return "homefeed";
	}


	@RequestMapping(value="/homefeed", method=RequestMethod.POST)
	public String performTasks(
			@RequestParam(required=false) String modifiedPostId,
			@RequestParam(required=false) String postText,
			@RequestParam(required=false) String commentText,
			@RequestParam(required=false) String commentButton,
			@RequestParam(required=false) String likeButton) {
		
		initializeToUser();
		
		// user actions
		writePost(postText);
		writeComment(commentText, commentButton, modifiedPostId);		
		likePost(likeButton, modifiedPostId);
		
		return "redirect:/homefeed";
	}

}
