package com.fdm.proj.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This class represents the user entity. It defines all attributes of a user account and actions that users can perform.
 * <br>
 * Each user can perform the following:
 * <ul>
 * 	<li>Create posts</li>
 * 	<li>Create comments on posts</li>
 * 	<li>Like and unlike posts</li>
 * </ul>
 * These interactions add the respective entities into their mapped collections to be updated into the database.

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
	private String firstname;
	private String lastname;
	private String email;
	private String location;
	private String occupation;
	
	// TODO add more user attributes
	private String bio;
	private String avatarUrl;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Post> createdPosts = new ArrayList<>();
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToMany(cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinTable(name="USERS_POSTS_LIKED",
		joinColumns=@JoinColumn(name="fk_UserId"),
		inverseJoinColumns=@JoinColumn(name="fk_PostId")
		)
	private Set<Post> likedPosts = new HashSet<>();
	
	// Constructors
	
	public User() {}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String firstname, String lastname) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	
	// Getters and Setters

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
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
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
	
	// TODO refactor this
	@Override
	public boolean equals(Object other) {
		Integer userId = (Integer) this.userId;
		if ((other instanceof User) && (userId != null)) {
			return userId.equals(((User) other).userId);
		} else {
			return other == this;
		}
	}
	
	@Override
	public String toString() {
		return String.format("User[d=%d, username=%s, firstname=%s, lastname=%s]", userId, username, firstname, lastname);
	}
	
}
