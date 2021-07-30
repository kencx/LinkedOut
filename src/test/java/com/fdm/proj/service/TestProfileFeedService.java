package com.fdm.proj.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.model.Post;
import com.fdm.proj.model.User;

public class TestProfileFeedService {
	
	@Mock
	private UserDAO userDAO;
	
	@InjectMocks
	private ProfileFeedService profileFeedService;
	
	private User u1;
	private Post p1, p2;
	private List<Post> listOfPosts = new ArrayList<>();


	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		u1 = new User("johnAdams", "password123");
		p1 = new Post("test", Instant.now());
		p2 = new Post("test2", Instant.now());
	}
	
	@Test
	public void returnUserFeedPostsTest() {
		
		u1.createPost(p1);
		u1.createPost(p2);
		
		listOfPosts.add(p1);
		listOfPosts.add(p2);
		listOfPosts.sort((p1,p2) -> Long.compare(p1.getTimePassedInMilli(), p2.getTimePassedInMilli()));
		
		profileFeedService.setUser(u1);
		List<Post> posts = profileFeedService.returnFeedPosts();
		
		assertTrue(posts.contains(p1));
		assertTrue(posts.contains(p2));
		assertEquals(posts.get(0).getUser(), u1);
		assertEquals(posts.get(1).getUser(), u1);
	}
}
