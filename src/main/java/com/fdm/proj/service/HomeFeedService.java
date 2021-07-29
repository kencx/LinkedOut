package com.fdm.proj.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.TagDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.model.Post;

@Service
public class HomeFeedService extends FeedService {

	@Autowired
	public HomeFeedService(UserDAO userDAO, PostDAO postDAO, TagDAO tagDAO) {
		super(userDAO, postDAO, tagDAO);
	}

	/**
	 * This service method returns all posts by all users.
	 * All posts are sorted chronologically from most recent to least.
	 */
	@Override
	public List<Post> returnFeedPosts() {
		List<Post> posts = postDAO.findAll();
		
		Comparator<Post> sortByTimePassed = (p1, p2) -> Long.compare(p1.getTimePassedInMilli(), p2.getTimePassedInMilli());
		posts.sort(sortByTimePassed);
		
		return posts;
	}
}
