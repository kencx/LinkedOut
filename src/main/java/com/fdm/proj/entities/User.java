package com.fdm.proj.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "USER_TABLE")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	private String username;
	private String password;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<Post> createdPosts;
	
	@OneToMany(mappedBy = "user")
	private List<Comment> comments;
	
	@ManyToMany
	@JoinTable(name="USER_POST", 
		joinColumns=@JoinColumn(name="fkUserId"), 
		inverseJoinColumns=@JoinColumn(name="fkPostId")
		)
	private List<Post> likedPosts;
	
	
	public User() {
		
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.createdPosts = new ArrayList<>();
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
	
	public void createPost(Post post) {
		post.setUser(this);
		this.createdPosts.add(post);
	}
	
	public List<Post> getCreatedPost() {
		return createdPosts;
	}

}
