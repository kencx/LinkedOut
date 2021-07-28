package com.fdm.proj.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import org.springframework.stereotype.Component;

import com.fdm.proj.helper.DateTimeHelper;

/**
 * This class represents the post entity.
 * Post class defines all attributes of a Post entity.
 * <p>
 * Each post must have one User attribute which corresponds to its author.
 * Each post has a collection of comments, users who liked the post and tags.
 * Each post can have multiple comments, multiple users who liked the post and multiple tags.
 * </p>
 * @author Kenneth
 *
 */

@Component
@Entity
@Table(name="POSTS")
public class Post implements DateTimeHelper {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int postId;
			
	private String body;
	private Instant timeCreated;
	
	@ManyToOne
	@JoinColumn(name="fk_userId")
	private User user;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Comment> comments = new ArrayList<>();
	
	
	@ManyToMany(mappedBy="likedPosts", cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	private Set<User> usersWhoLiked = new HashSet<>();
	
	
	@ManyToMany(cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinTable(name="POSTS_TAGS", 
		joinColumns=@JoinColumn(name="fk_postId"), 
		inverseJoinColumns=@JoinColumn(name="fk_tagId")
		)
	private Set<Tag> tags = new HashSet<>();
	
	
	// constructors
	
	public Post() {}
	
	public Post(String body) {
		this.body = body;
	}
	
	public Post(String body, Instant time) {
		this.body = body;
		this.timeCreated = time;
	}
	
	public Post(String body, Instant time, List<Tag> tags) {
		this.body = body;
		this.timeCreated = time;
		
		for (Tag t : tags) {
			addTag(t);
		}
	}
	
	
	// Getters and setters
	
	public int getPostId() {
		return postId;
	}
		
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}	
	
	public Instant getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Instant time) {
		this.timeCreated = time;
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
	
	public Set<User> getUsersWhoLiked() {
		return usersWhoLiked;
	}

	public Set<Tag> getTags() {
		return tags;
	}
	
	public List<Comment> getCommentsSortedByTime() {
		Comparator<Comment> sortByCommentTimePassed = (c1, c2) -> Long.compare(c1.getTimePassedInMilli(), c2.getTimePassedInMilli());
		this.comments.sort(sortByCommentTimePassed);
		return comments;
	}
	
	public Set<String> printTags() {
		Set<String> tagNames = tags.stream().map(Tag::getTagName).collect(Collectors.toSet());
//		return String.join(", ", tagNames);
		return tagNames;
	}
	
	public String getTimePassed() {
		return DateTimeHelper.timePassed(Instant.now(), this.timeCreated);
	}
	
	public long getTimePassedInMilli() {
		return DateTimeHelper.timePassedInMillis(Instant.now(), this.timeCreated);
	}
	
	// Entity interactions
	
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
