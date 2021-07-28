package com.fdm.proj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.proj.dal.TagDAO;
import com.fdm.proj.model.Post;
import com.fdm.proj.model.Tag;

@Service
public class SearchService {

	private TagDAO tagDAO;

	@Autowired
	public SearchService(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}

	public List<Post> search(String searchTag) {
		
		Tag resultTag = tagDAO.findByTagName(searchTag);
		if (resultTag == null) {
			return null;
		} 
		
		Set<Post> resultPosts = resultTag.getPosts();
		List<Post> posts = new ArrayList<>(resultPosts);
		return posts;
	}
}
