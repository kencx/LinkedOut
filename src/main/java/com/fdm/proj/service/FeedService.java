package com.fdm.proj.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
	 * It handles collections of tags with that are: 1. all new, 2. all exist, 3. a combination of both.
	 * @param user Author of post
	 * @param postBody Content of post
	 * @param timeCreated Time of creation
	 * @param tags Optional tags given to post
	 */
	public void userCreatePost(User user, String postBody, Instant timeCreated, List<Tag> tags) {
		Post post = null;
		
		if (tags != null) {
			
			HashMap<Tag,Integer> tagExistsMap = processTagExistence(tags);

			if (!tagExistsMap.containsValue(1)) { // all tags do not exist
				post = new Post(postBody, timeCreated, tags); 				
			}

			else {
				List<Tag> newTags = new ArrayList<>();
				List<Tag> existingTags = new ArrayList<>();

				tagExistsMap.forEach((tag, num) -> { // filter all new tags
					if (num.equals(0)) {
						newTags.add(tag);
					} else {
						existingTags.add(tag);
					}
				});
			
				if (newTags.size() > 0) {
					post = new Post(postBody, timeCreated, newTags); // create post with all non existing tags first
				} else {
					post = new Post(postBody, timeCreated);
				}
				
				if (existingTags.size() > 0) {
					for (Tag t : existingTags) {
						post.addTag(t);
					}
				}
			}
			
		} else {
			post = new Post(postBody, timeCreated);	
		}
		
		user.createPost(post);
		userDAO.updateUser(user.getUserId(), user);
	}
	
	/**
	 * Returns a HashMap that indicates if each element in tags already exists in the DB.
	 * @param tags
	 * @return HashMap<Tag,Integer> where value takes 1 if tag already exists, 0 if tag is new.
	 */
	private HashMap<Tag, Integer> processTagExistence(List<Tag> tags) {
		
		HashMap<Tag, Integer> tagExistenceMap = new HashMap<>();
		
		for (Tag tag : tags) {
			
			Tag t = tagDAO.findByTagName(tag.getTagName());
			if (t != null) {
				tagExistenceMap.put(t, 1);
			} else {
				tagExistenceMap.put(tag, 0);
			}
		}
		return tagExistenceMap;
	}
	
	
	public void userCreateComment(User user, Post post, String commentBody, Instant timeCreated) {
		Comment comment = new Comment(commentBody, timeCreated);
		user.createComment(post, comment);
		userDAO.updateUser(user.getUserId(), user);
	}
	
	public void likePost(User user, Post likedPost) {
		user.likePost(likedPost);
		postDAO.updatePost(likedPost.getPostId(), likedPost);
	}
	
	public Post returnPost(int postID) {
		Post post = postDAO.findById(postID);
		return post;
	}
	
	public List<Comment> returnAllPostComments(Post post) {
		List<Comment> comments = post.getComments();
		return comments;
	}	
}
