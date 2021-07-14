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

/**
 * Post class defines all attributes of a Post entity.
 * <p>
 * Each post must have one User attribute which corresponds to its author.
 * Each post has a collection of comments, users who liked the post and tags.
 * Each post can have multiple comments, multiple users who liked the post and multiple tags.
 * </p>
 * @author Kenneth
 *
 */

@Entity
@Table(name="POSTS")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int postId;
		
	private String title;
	private String body;
	
	@ManyToOne
	@JoinColumn(name="fk_userId")
	private User user;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToMany(mappedBy="likedPosts", cascade={CascadeType.MERGE,CascadeType.PERSIST})
	private Set<User> usersWhoLiked = new HashSet<>();
	
	@ManyToMany(cascade=CascadeType.MERGE)
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
//		if (sameUser(user)) 
//			return;
		
//		User oldUser = this.user;
		this.user = user;
		
//		if (oldUser != null) 
//			oldUser.deletePost(this);
//		if (user != null)
//			this.user.createPost(this);
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	
	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}

	public Set<User> getUsersWhoLiked() {
		return usersWhoLiked;
	}

	public void addUserToLiked(User user) {
		this.usersWhoLiked.add(user);
	}

	public void removeUserFromLiked(User user) {
		this.usersWhoLiked.remove(user);
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void addTag(Tag tag) {
		if (tag != null) {
			this.tags.add(tag);
			tag.addPost(this);
		}
	}
	
	public void removeTag(Tag tag) {
		if (tags.contains(tag)) {
			this.tags.remove(tag);
			tag.removePost(this);
		}
	}

//	private boolean sameUser(User newUser) {
//		return this.user==null ? newUser == null : user.equals(newUser);
//	}
	
}
