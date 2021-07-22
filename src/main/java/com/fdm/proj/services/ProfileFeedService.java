package com.fdm.proj.services;

import java.util.List;

import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.entities.Post;
import com.fdm.proj.entities.User;

public class ProfileFeedService extends FeedService {
	
	private int userId;
	
	public ProfileFeedService(UserDAO userDAO, PostDAO postDAO) {
		super(userDAO, postDAO);
	}

	@Override
	public List<Post> returnFeedPosts() {
		
		User currentUser = returnUser(userId);
		List<Post> userPosts = currentUser.getCreatedPosts();
		return userPosts;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	// edit profile helper methods
	
}
