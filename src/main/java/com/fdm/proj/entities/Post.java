package com.fdm.proj.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "POST_TABLE")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int postId;
		
	private String title;
	private String body;
	
	@ManyToOne
	@JoinColumn(name = "fk_userId", nullable=false)
	private User user;
	
	@OneToMany(mappedBy = "post")
	private List<Comment> comments;
	
	@ManyToMany(mappedBy = "likedPosts")
	private List<User> usersWhoLiked;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="POST_TAG", 
		joinColumns=@JoinColumn(name="fk_postId", nullable=false), 
		inverseJoinColumns=@JoinColumn(name="fk_tagId")
		)
	private List<Tag> tags;
	
	
	public Post() {
		
	}
	
	public Post(String title, String body) {
		this.title = title;
		this.body = body;
	}
	
	
	public int getPostId() {
		return postId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}	
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
