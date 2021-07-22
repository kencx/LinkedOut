package com.fdm.proj.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
			
	private String body;
	private Date time; // TODO format time to String
	

	@ManyToOne
	@JoinColumn(name="fk_userId")
	private User user;
	
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Comment> comments = new ArrayList<>();
	
	
	@ManyToMany(mappedBy="likedPosts", cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	private Set<User> usersWhoLiked = new HashSet<>();
	
	
	@ManyToMany(cascade={CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name="POSTS_TAGS", 
		joinColumns=@JoinColumn(name="fk_postId"), 
		inverseJoinColumns=@JoinColumn(name="fk_tagId")
		)
	private Set<Tag> tags = new HashSet<>();
	
	
	public Post() {
		
	}
	
	public Post(String body) {
		this.body = body;
	}
	
	
	public int getPostId() {
		return postId;
	}
		
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}	
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
	
	public void removeAllComments() {
		if (getComments().size() >= 0) {
			for (Iterator<Comment> iter = getComments().iterator(); iter.hasNext();) {
				Comment comment = (Comment) iter.next();
				User commenter = comment.getUser();
				
				// cannot use commenter.removeComment() due to ConcurrentModificationException
				// post.removeComment(comment) must be called at the last step
				// here, it is handled by iter.remove()
				if (commenter.getComments().contains(comment)) {
					commenter.getComments().remove(comment);
					comment.setPost(null);
					comment.setUser(null);
				}
				iter.remove();
			}
		}
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
	
	public void removeAllUsersFromLiked() {
		if (getUsersWhoLiked().size() >= 0) {
			for (Iterator<User> iter = getUsersWhoLiked().iterator(); iter.hasNext();) {
				User liker = (User) iter.next();
				
				// cannot use liker.unlike() due to ConcurrentModificationException
				// post.removeUserFromLiked() must be called at the last step
				// here, it is handled by iter.remove()
				if (liker.getLikedPosts().contains(this)) {
					liker.getLikedPosts().remove(this);
				}
				iter.remove();
			}
		}
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
	
	public void removeAllTags() {
		if (getTags().size() >= 0) {
			for (Iterator<Tag> iter = getTags().iterator(); iter.hasNext();) {
				Tag tag = (Tag) iter.next();
				
				if (tag.getPosts().contains(this)) {
					tag.removePost(this);
				}
				iter.remove();
			}
		}
	}

}
