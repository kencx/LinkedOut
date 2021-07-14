package com.fdm.proj.entities;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests of User actions including:
 * <ul>
 *  <li>Post creation and deletion</li>
 *  <li>Comment creation and deletion</li>
 *  <li>Liking and unliking posts</li>
 * </ul>
 * Also tests for the creation and deletion of null objects, and posts and comments of other users.
 * 
 * @author Kenneth
 */


public class UserActionsTest {

	private User u1;
	private Post p1;
	private Comment c1;
	

	@Before
	public void init() {
		u1 = new User("johnAdams", "password1");
		p1 = new Post("testPost", "Hello World!");
	}
	

	// -- Post Creation and Deletion --
	
	@Test
	public void UserCreatePostTest() {
		u1.createPost(p1);
		assertEquals(p1.getUser(), u1);
		assertTrue(u1.getCreatedPosts().contains(p1));
	}
	
	@Test
	public void UserDeleteOwnPostTest() {
		u1.createPost(p1);
		u1.removePost(p1);
		
		assertEquals(p1.getUser(), null);
		assertTrue(!u1.getCreatedPosts().contains(p1));
	}
	
	// -- Comment Creation and Deletion --
	
	@Test
	public void UserCreateCommentOnPostTest() {
		c1 = new Comment("First!");
		
		u1.createComment(p1, c1);
		assertEquals(c1.getUser(), u1);
		assertTrue(u1.getComments().contains(c1));
	}
	
	@Test
	public void UserDeleteCommentOnPostTest() {
		c1 = new Comment("First!");
		u1.createComment(p1, c1);
		
		u1.removeComment(p1, c1);
		assertEquals(c1.getUser(), null);
		assertEquals(c1.getPost(), null);
		assertTrue(!u1.getComments().contains(c1));
	}
	
	// -- Like and Unlike Post --
	
	@Test
	public void UserLikesPostTest() {
		u1.likePost(p1);
		
		assertTrue(u1.getLikedPosts().contains(p1));
		assertTrue(p1.getUsersWhoLiked().contains(u1));
	}
	
	@Test
	public void UserUnlikesPostTest() {
		u1.likePost(p1);
		u1.unlikePost(p1);
		
		assertTrue(!u1.getLikedPosts().contains(p1));
		assertTrue(!p1.getUsersWhoLiked().contains(u1));
	}

	// -- Creation and Deletion of Posts and Comments not belonging to User --
	
	@Test
	public void UserAddOtherUsersPost() {
		User u2 = new User("benDover", "password2");
		u2.createPost(p1);
		
		u1.createPost(p1); // not allowed to use same object
		
		assertTrue(u2.getCreatedPosts().contains(p1));
		assertTrue(!u1.getCreatedPosts().contains(p1));
		assertTrue(p1.getUser().equals(u2));
	}
	
	@Test
	public void UserRemoveOtherUsersPost() {
		User u2 = new User("benDover", "password2");
		u2.createPost(p1);
		
		u1.removePost(p1);
		assertTrue(u2.getCreatedPosts().contains(p1));
	}
	
	@Test 
	public void UserAddOtherUsersComment() {
		User u2 = new User("benDover", "password2");
		c1 = new Comment("First!");
		u2.createComment(p1, c1);
		
		u1.createComment(p1, c1); // not allowed to use same object
		
		assertTrue(u2.getComments().contains(c1));
		assertTrue(!u1.getComments().contains(c1));
		assertTrue(c1.getUser().equals(u2));
		assertTrue(c1.getPost().equals(p1));
	}
	
	@Test 
	public void UserAddCommenttoOtherPost() {
		Post p2 = new Post("post2", "this is a post");
		c1 = new Comment("First!");
		u1.createComment(p1, c1);
		
		u1.createComment(p2, c1); // not allowed to use same object
		
		assertTrue(u1.getComments().contains(c1));
		assertTrue(p1.getComments().contains(c1));
		assertTrue(!p2.getComments().contains(c1));
		assertEquals(c1.getPost(), p1);
	}
	
	// -- Creation and Deletion of Null Objects --
	
	@Test
	public void UserCreateNullPostTest() {
		u1.createPost(null);
		assertEquals(u1.getCreatedPosts().size(), 0);
	}
	
	@Test
	public void UserRemoveNullPostTest() {
		u1.removePost(null);
	}
	
	@Test
	public void UserCreateNullComment() {
		u1.createComment(p1, null);
		assertEquals(u1.getComments().size(), 0);
	}
}
