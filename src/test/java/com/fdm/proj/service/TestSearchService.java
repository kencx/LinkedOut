package com.fdm.proj.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.proj.dal.TagDAO;
import com.fdm.proj.model.Post;
import com.fdm.proj.model.Tag;

public class TestSearchService {
	
	@Mock
	private TagDAO tagDAO;
	
	@InjectMocks
	private SearchService searchService;
	
	private Tag t1;
	private Post p1, p2;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		t1 = new Tag("testTag");
		p1 = new Post("test", Instant.now());
		p2 = new Post("test2", Instant.now());

	}
	
	@Test
	public void searchTest() {
		p1.addTag(t1);
		p2.addTag(t1);
		when(tagDAO.findByTagName(t1.getTagName())).thenReturn(t1);
		
		List<Post> foundPosts = searchService.search(t1.getTagName());
		List<Tag> foundTags = new ArrayList<>(foundPosts.get(0).getTags());
		assertEquals(t1, foundTags.get(0));
	}
	
}
