package com.fdm.proj.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for creation and deletion of tags in posts, and null objects.
 * @author Kenneth
 *
 */

public class TestPostActions {
	
	private Post p1;
	private Tag t1;

	@Before
	public void init() {
		p1 = new Post("testPost", "Hello World!");
		t1 = new Tag("test Tag");
	}
	
	
	// -- Tag Creation and Deletion --
	
	@Test
	public void addTagToPost() {
		p1.addTag(t1);
		
		assertTrue(p1.getTags().contains(t1));
		assertTrue(t1.getPosts().contains(p1));
	}
	
	@Test
	public void removeTagFromPost() {
		p1.addTag(t1);
		
		p1.removeTag(t1);
		assertTrue(!p1.getTags().contains(t1));
		assertTrue(!t1.getPosts().contains(p1));
	}
	
	@Test
	public void addNullTagToPost() {
		p1.addTag(null);
		assertEquals(p1.getTags().size(), 0);
		
	}
	
	@Test
	public void removeNullTagFromPost() {
		p1.removeTag(null);
		assertEquals(p1.getTags().size(), 0);
	}
	
	
	@Test
	public void removeAllCommentsFromPost() {
		User u1 = new User("johnAdams", "password1");
		Comment c1 = new Comment("First!");
		Comment c2 = new Comment("Last!");
		
		u1.createComment(p1, c1);
		u1.createComment(p1, c2);
		
		p1.removeAllComments();
		
		assertEquals(p1.getComments().size(), 0);
		assertEquals(u1.getComments().size(), 0);
	}
	
	@Test
	public void removeAllLikesFromPost() {
		User u1 = new User("johnAdams", "password1");
		User u2 = new User("benDover", "password2");
		
		u1.likePost(p1);
		u2.likePost(p1);
		
		p1.removeAllUsersFromLiked();
		assertEquals(p1.getUsersWhoLiked().size(), 0);
		assertEquals(u1.getLikedPosts().size(), 0);
		assertEquals(u2.getLikedPosts().size(), 0);
	}
	
	@Test
	public void removeAllTagsFromPost() {
		Tag t2 = new Tag("test tag 2");
		p1.addTag(t1);
		p1.addTag(t2);
		
		p1.removeAllTags();
		assertEquals(p1.getTags().size(), 0);
		assertEquals(t1.getPosts().size(), 0);
		assertEquals(t2.getPosts().size(), 0);
	}
	
}
