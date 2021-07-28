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
		
		// load all posts
		List<Post> posts = feedService.returnFeedPosts();
		session.setAttribute("posts", posts);
		
		// load all comments
		HashMap<Integer, List<Comment>> commentMap = new HashMap<>();
		for (Post p : posts) {			
			int postID = p.getPostId();
			List<Comment> comments = feedService.returnAllPostComments(p);
			commentMap.put(postID, comments);
		}
		session.setAttribute("comments", commentMap);
				
		return "homefeed";
	}


	@Override
	@RequestMapping(value="/homefeed", method=RequestMethod.POST)
	public String performTasks(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		initUser(session);
		
		// load all posts
			List<Post> posts = feedService.returnFeedPosts();
			session.setAttribute("posts", posts);
				
		// load all comments
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
		
		return "redirect:/homefeed";
	}

}
