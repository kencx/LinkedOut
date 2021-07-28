package com.fdm.proj.model;


import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fdm.proj.helper.DateTimeHelper;

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
public class Comment implements DateTimeHelper {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int commentId;
	
	private String commentBody;
	private Instant timeCreated;
		
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
	
	public Comment(String commentBody, Instant time) {
		this.commentBody = commentBody;
		this.timeCreated = time;
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

	public Instant getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Instant timeCreated) {
		this.timeCreated = timeCreated;
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
	
	public String getTimePassed() {
		return DateTimeHelper.timePassed(Instant.now(), this.timeCreated);
	}

	public long getTimePassedInMilli() {
		return DateTimeHelper.timePassedInMillis(Instant.now(), this.timeCreated);
	}
}
