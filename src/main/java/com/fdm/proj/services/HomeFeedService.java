package com.fdm.proj.services;

import java.util.List;

import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.entities.Post;

public class HomeFeedService extends FeedService {

	public HomeFeedService(UserDAO userDAO, PostDAO postDAO) {
		super(userDAO, postDAO);
	}

	@Override
	public List<Post> returnFeedPosts() {
		List<Post> posts = postDAO.findAll();
		return posts;
	}
}
