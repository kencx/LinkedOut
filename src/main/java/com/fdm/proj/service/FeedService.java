package com.fdm.proj.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.TagDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.model.Comment;
import com.fdm.proj.model.Post;
import com.fdm.proj.model.Tag;
import com.fdm.proj.model.User;

@Service
public abstract class FeedService {
	
	protected UserDAO userDAO;
	protected PostDAO postDAO;
	protected TagDAO tagDAO;

	public FeedService(UserDAO userDAO, PostDAO postDAO, TagDAO tagDAO) {
		this.postDAO = postDAO;
		this.userDAO = userDAO; 
		this.tagDAO = tagDAO;
	}

	public abstract List<Post> returnFeedPosts();
	
	public User returnUser(int userId) {
		User user = userDAO.findById(userId);
		return user;
	}
	
	/**
	 * This method has the given user create a post with optional tags. If any of the given tags already exist, the post is mapped to the tag. Otherwise, a post is created normally.
	 * @param user Author of post
	 * @param postBody Content of post
	 * @param timeCreated Time of creation
	 * @param tags Optional tags given to post
	 */
	public void userCreatePost(User user, String postBody, Instant timeCreated, List<Tag> tags) {
		Post post = null;
		
		if (tags != null) {
			
			// check if any tags exist already
			for (Tag tag : tags) {
				
				Tag existingTag = tagDAO.findByTagName(tag.getTagName());
				
				if (existingTag != null) {
					post = new Post(postBody, timeCreated);
					post.addTag(existingTag);
				} else {
					post = new Post(postBody, timeCreated, tags);
				}
			}
			
		} else {
			post = new Post(postBody, timeCreated);	
		}
		
		user.createPost(post);
		userDAO.updateUser(user.getUserId(), user);
	}
	
	public void userCreateComment(User user, Post post, String commentBody, Instant timeCreated) {
		Comment comment = new Comment(commentBody, timeCreated);
		user.createComment(post, comment);
		userDAO.updateUser(user.getUserId(), user);
	}
	
	public Post returnPost(int postID) {
		Post post = postDAO.findById(postID);
		return post;
	}
	
	public List<Comment> returnAllPostComments(Post post) {
		List<Comment> comments = post.getComments();
		
		return comments;
	}

	public void likePost(User user, Post likedPost) {
		user.likePost(likedPost);
		postDAO.updatePost(likedPost.getPostId(), likedPost);
	}
	
}
