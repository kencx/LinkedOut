package com.fdm.proj.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="TAGS")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tagId;
	
	private String tagName;
	
	@ManyToMany(mappedBy="tags", cascade={
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
			})
	private Set<Post> posts = new HashSet<>();
	
	
	public Tag() {
		
	}
	
	public Tag(String tagName) {
		this.tagName = tagName;
	}
	
	public int getTagId() {
		return tagId;
	}
	
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void addPost(Post post) {
		this.posts.add(post);
	}
	
	
}
