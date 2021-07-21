package com.fdm.proj.services;

import java.util.List;

import com.fdm.proj.dal.CommentDAO;
import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.entities.Comment;
import com.fdm.proj.entities.Post;
import com.fdm.proj.entities.User;

public class FeedService {
	
	private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;

	
	public FeedService( UserDAO userDAO, PostDAO postDAO, CommentDAO commentDAO) {
		this.postDAO = postDAO;
		this.userDAO = userDAO; 
		this.commentDAO = commentDAO;
	}
	
	
	public User returnUser(int userID) {
		User user = userDAO.findById(userID);
		return user;
	}
	
	public void userCreatePost(User user, String postBody) {
		Post post = new Post(postBody);
		user.createPost(post);
		userDAO.updateUser(user.getUserId(), user);
	}
	
	public void userCreateComment(User user, Post post, String commentBody) {
		Comment comment = new Comment(commentBody);
		user.createComment(post, comment);
		userDAO.updateUser(user.getUserId(), user);
	}
	
	public List<Post> returnAllPosts() {
		List<Post> posts = postDAO.findAll();
		return posts;
	}
	
	public List<Comment> returnAllPostComments(Post post) {
		List<Comment> comments = post.getComments();
		return comments;
	}
	
	
}
