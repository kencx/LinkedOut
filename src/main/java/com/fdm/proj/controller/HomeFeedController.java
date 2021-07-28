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
import com.fdm.proj.service.HomeFeedService;

@Controller
public class HomeFeedController extends FeedController {

	@Autowired
	public HomeFeedController(HomeFeedService homeFeedService) {
		super(homeFeedService);
	}

	@Override
	@RequestMapping(value="/homefeed", method=RequestMethod.GET)
	public String loadFeed(HttpSession session) {
		
		List<Post> posts = loadPosts();
		session.setAttribute("posts", posts);
				
		HashMap<Integer, List<Comment>> comments = loadComments(posts);
		session.setAttribute("comments", comments);
				
		return "homefeed";
	}


	@RequestMapping(value="/homefeed", method=RequestMethod.POST)
	public String performTasks(HttpSession session, 
			@RequestParam(required=false) String modifiedPostId,
			@RequestParam(required=false) String postText,
			@RequestParam(required=false) String commentText,
			@RequestParam(required=false) String commentButton,
			@RequestParam(required=false) String likeButton) {
		
		initUser(session);
	
		List<Post> posts = loadPosts();
		session.setAttribute("posts", posts);
				
		HashMap<Integer, List<Comment>> comments = loadComments(posts);
		session.setAttribute("comments", comments);
		
		// user actions
		writePost(postText);
		writeComment(commentText, commentButton, modifiedPostId);		
		likePost(likeButton, modifiedPostId);
		
		return "redirect:/homefeed";
	}

}
