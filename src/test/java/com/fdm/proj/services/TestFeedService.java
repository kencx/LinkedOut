package com.fdm.proj.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.entities.Comment;
import com.fdm.proj.entities.Post;
import com.fdm.proj.entities.User;


public class TestFeedService {

	@Mock
	private UserDAO userDAO;
	
	@Mock
	private PostDAO postDAO;
	
	@InjectMocks
	private HomeFeedService feedService;
		
	private User user;
	private Post post;

	private int userId;
	private String username, password, commentBody;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);	
		username = "johnAdams";
		password = "password123";
		
		user = new User(username, password);
		post = new Post("testPost");
		commentBody = "comment";
		
		doNothing().when(userDAO).updateUser(userId, user);
		doNothing().when(postDAO).updatePost(post.getPostId(), post);
	}
	
	
	@Test
	public void createPostServiceTest() {
		
		feedService.userCreatePost(user, "testPost");
		assertTrue(user.getCreatedPosts().size() > 0);
		verify(userDAO, times(1)).updateUser(user.getUserId(), user);
	}
	
	@Test public void createCommentServiceTest() {
		
		feedService.userCreateComment(user, post, commentBody);
		assertTrue(user.getComments().size() > 0);
		verify(userDAO, times(1)).updateUser(user.getUserId(), user);
	}
	
	@Test public void likePostServiceTest() {
		
		feedService.likePost(user, post);
		assertTrue(user.getLikedPosts().size() > 0);
		verify(postDAO, times(1)).updatePost(post.getPostId(), post);
	}
	
	
	
	
}
