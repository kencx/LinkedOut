package com.fdm.proj.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Table(name="POSTS")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int postId;
		
	private String title;
	private String body;
	
	@ManyToOne
	@JoinColumn(name="fk_userId", nullable=false, updatable=false)
	private User user;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToMany(mappedBy="likedPosts", cascade={
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
			})
	private Set<User> usersWhoLiked = new HashSet<>();
	
	@ManyToMany(cascade={
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
			})
	@JoinTable(name="POSTS_TAGS", 
		joinColumns=@JoinColumn(name="fk_postId"), 
		inverseJoinColumns=@JoinColumn(name="fk_tagId")
		)
	private Set<Tag> tags = new HashSet<>();
	
	
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

	public Set<User> getUsersWhoLiked() {
		return usersWhoLiked;
	}

	public void addUserToLiked(User user) {
		this.usersWhoLiked.add(user);
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void addTag(Tag tag) {
		tag.addPost(this);
		this.tags.add(tag);
	}
	
	
	
	
}
