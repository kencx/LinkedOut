package com.fdm.proj.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TAG_TABLE")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tagId;
	
	private String tagName;
	
	@ManyToMany(mappedBy = "tags")
	private List<Post> posts;
	
	
	public Tag() {
		
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
}
