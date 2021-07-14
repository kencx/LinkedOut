package com.fdm.proj.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="COMMENTS")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int commentId;
	
	private String commentBody;
		
	@ManyToOne
	@JoinColumn(name="fk_UserId", nullable=false, updatable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="fk_PostId", nullable=false, updatable=false)
	private Post post;

	
	public Comment() {
		
	}
	
	public Comment(String comment) {
		this.commentBody = comment;
	}
	
	public int getCommentId() {
		return commentId;
	}
	
	public String getComment() {
		return commentBody;
	}

	public void setComment(String comment) {
		this.commentBody = comment;
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
