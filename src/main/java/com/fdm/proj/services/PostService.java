package com.fdm.proj.services;

import java.util.List;

import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.entities.Post;
import com.fdm.proj.entities.User;

public class PostService {
	
	private UserDAO userDAO;
	private PostDAO postDAO;

	
	public PostService( UserDAO userDAO, PostDAO postDAO) {
		this.postDAO = postDAO;
		this.userDAO = userDAO; 
	}
	
	
	public void userCreatePost(User user, String postBody) {
		Post post = new Post(postBody);
		user.createPost(post);
		userDAO.updateUser(user.getUserId(), user);
	}
	
	public List<Post> returnAllPosts() {
		List<Post> posts = postDAO.findAll();
		for (Post post : posts) {
			System.out.println(post.getBody() + " by " + post.getUser().getUsername());
		}
		return posts;
	}
	
	
}
