package com.fdm.proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.model.Post;

@Service
public class HomeFeedService extends FeedService {

	@Autowired
	public HomeFeedService(UserDAO userDAO, PostDAO postDAO) {
		super(userDAO, postDAO);
	}

	@Override
	public List<Post> returnFeedPosts() {
		List<Post> posts = postDAO.findAll();
		return posts;
	}
}
