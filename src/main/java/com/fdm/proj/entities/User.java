package com.fdm.proj.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User class defines all attributes of a User account and actions that users can perform such as:
 * <ul>
 * 	<li>Creation and deletion of posts</li>
 * 	<li>Creation and deletion of comments</li>
 * 	<li>Liking and unliking posts</li>
 * </ul>
 * Each user has a collection of posts created by them, comments written by them and posts liked by them.
 * Each user can create multiple posts and comments, and like multiple posts.
 * 
 * @author Kenneth
 *
 */

@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@Column(unique=true)
	private String username;
	private String password;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Post> createdPosts = new ArrayList<>();
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	@JoinTable(name="USERS_POSTS_LIKED",
		joinColumns=@JoinColumn(name="fk_UserId"),
		inverseJoinColumns=@JoinColumn(name="fk_PostId")
		)
	private Set<Post> likedPosts = new HashSet<>();
	
	
	public User() {
		
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	public int getUserId() {
		return userId;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Post> getCreatedPosts() {
		return createdPosts;
	}
	
	public void createPost(Post post) {
		if (post != null) {
			if (!(post.getUser() instanceof User)) { // each post only has 1 unmodifiable author (user)
				createdPosts.add(post);
				post.setUser(this);
			}
		}
	}
	
	public void removePost(Post post) {
		if (createdPosts.contains(post)) {
			
			// remove all child comments, likes and tags
			post.removeAllComments();
			post.removeAllUsersFromLiked();
			post.removeAllTags();
			
			createdPosts.remove(post);
			post.setUser(null);
		}
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void createComment(Post post, Comment comment) {
		if (comment != null) {
			// each comment only has 1 unmodifiable author (user) and post
			if (!(comment.getUser() instanceof User) || !(comment.getPost() instanceof Post)) {
				this.comments.add(comment);
				post.addComment(comment);
				comment.setUser(this);
				comment.setPost(post);
			}
		}
	}

	public void removeComment(Post post, Comment comment) {
		// comment must be by user and must belong to post
		if (comments.contains(comment) && (post.getComments().contains(comment))) {
			this.comments.remove(comment);
			post.removeComment(comment);
			comment.setPost(null);
			comment.setUser(null);
		}
	}
	
	public Set<Post> getLikedPosts() {
		return likedPosts;
	}

	public void likePost(Post post) {
		this.likedPosts.add(post);
		post.addUserToLiked(this);
	}
	
	public void unlikePost(Post post) {
		if (likedPosts.contains(post)) {
			this.likedPosts.remove(post);
			post.removeUserFromLiked(this);
		}
	}
}
