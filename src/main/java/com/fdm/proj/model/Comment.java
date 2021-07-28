package com.fdm.proj.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * This class represents the comment entity.
 * Each comment must have 1 User attribute that corresponds to its author and 1 Post attribute that corresponds to its parent post.
 * 
 * @author Kenneth
 *
 */

@Component
@Entity
@Table(name="COMMENTS")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int commentId;
	
	private String commentBody;
	private Date commentTime; // TODO time of comment
		
	@ManyToOne
	@JoinColumn(name="fk_UserId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="fk_PostId")
	private Post post;

	
	// constructors
	
	public Comment() {}
	
	public Comment(String commentBody) {
		this.commentBody = commentBody;
	}
	
	public Comment(String commentBody, Date time) {
		this.commentBody = commentBody;
		this.commentTime = time;
	}
	
	
	// getters and setters
	
	public int getCommentId() {
		return commentId;
	}
	
	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
