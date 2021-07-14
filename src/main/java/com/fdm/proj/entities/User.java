package com.fdm.proj.entities;

import java.util.ArrayList;
import java.util.HashSet;
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


@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<Post> createdPosts = new ArrayList<>();
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToMany(cascade={
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
			})
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
	
	public List<Post> getCreatedPost() {
		return createdPosts;
	}
	
	public void createPost(Post post) {
		post.setUser(this);
		this.createdPosts.add(post);
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void createComment(Post post, Comment comment) {
		comment.setUser(this);
		comment.setPost(post);
		this.comments.add(comment);
	}

	public Set<Post> getLikedPosts() {
		return likedPosts;
	}

	public void likePost(Post post) {
		post.addUserToLiked(this);
		this.likedPosts.add(post);
	}
	
}
