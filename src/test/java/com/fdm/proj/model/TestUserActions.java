package com.fdm.proj.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
 * Also tests for the creation and deletion of null objects, 
 * and interactions with posts and comments of other users.
 * 
 * @author Kenneth
 */


public class TestUserActions {

	private User u1;
	private Post p1;
	private Comment c1;
	

	@Before
	public void init() {
		u1 = new User("johnAdams", "password1");
		p1 = new Post("Hello World!");
	}
	
	
	// ## Post Creation and Deletion ##
	
	/**
	 * Tests user post creation. User can author a new post.
	 * @result Post should be added to the user's createdPost list.
	 * The post's user should be the author.
	 */
	@Test
	public void UserCreatePostTest() {
		u1.createPost(p1);
		assertEquals(p1.getUser(), u1);
		assertTrue(u1.getCreatedPosts().contains(p1));
	}
	
	/**
	 * Tests user post deletion. User can delete their own posts.
	 * @result Post should be deleted from user's createdPost list.
	 * The post's user should be null.
	 */
	@Test
	public void UserDeleteOwnPostTest() {
		u1.createPost(p1);
		u1.removePost(p1);
		
		assertNull(p1.getUser());
		assertTrue(!u1.getCreatedPosts().contains(p1));
	}
	
	
	// ## Comment Creation and Deletion ##
	
	/**
	 * Tests user comment creation. User can create their own comments.
	 * @result Comment should be added to user's comments list.
	 * The comment's user should be the author.
	 */
	@Test
	public void UserCreateCommentOnPostTest() {
		c1 = new Comment("First!");
		
		u1.createComment(p1, c1);
		assertEquals(c1.getUser(), u1);
		assertTrue(u1.getComments().contains(c1));
	}
	
	/**
	 * Tests user comment deletion. User can delete their own comments.
	 * @result Comment should be deleted from user's comments list.
	 * The comment's user should be null.
	 */
	@Test
	public void UserDeleteCommentOnPostTest() {
		c1 = new Comment("First!");
		u1.createComment(p1, c1);
		
		u1.removeComment(p1, c1);
		assertNull(c1.getUser());
		assertNull(c1.getPost());
		assertTrue(!u1.getComments().contains(c1));
	}
	
	
	// ## Like and Unlike Posts ##
	
	/**
	 * Tests user liking posts. User can like their or others' posts.
	 * @result Liked post should be added to user's likedPost set.
	 * The post's likedUser set should include the user.
	 */
	@Test
	public void UserLikesPostTest() {
		u1.likePost(p1);
		
		assertTrue(u1.getLikedPosts().contains(p1));
		assertTrue(p1.getUsersWhoLiked().contains(u1));
	}
	
	/**
	 * Tests user unliking posts. User can unlike their or others' posts.
	 * @result Liked post should be removed from user's likedPost set.
	 * The post's likedUser set should not include the user.
	 */
	@Test
	public void UserUnlikesPostTest() {
		u1.likePost(p1);
		u1.unlikePost(p1);
		
		assertTrue(!u1.getLikedPosts().contains(p1));
		assertTrue(!p1.getUsersWhoLiked().contains(u1));
	}

	
	// ## Creation and Deletion of Posts with Dependencies ##
	
	/**
	 * Tests user post creation with own dependencies. User interacts with their own post.
	 * @result Post should be added to the user's createdPost list.
	 * The post's user should be the author.
	 * Comment should be added to post's comments list and user's comments list.
	 * The comment's user should be the author.
	 * Post should be added to user's likedPosts set.
	 * User should be added to post's likedUsers set.
	 * Tag should be added to post's tags set.
	 */
	@Test
	public void UserCreatePostWithOwnCommentLikesAndTagTest() {
		c1 = new Comment("First!");
		Tag t1 = new Tag("test tag");
		
		u1.createPost(p1);
		p1.addTag(t1);
		u1.createComment(p1, c1);
		u1.likePost(p1);
		
		assertEquals(p1.getUser(), u1);
		assertTrue(u1.getCreatedPosts().contains(p1));
		assertEquals(c1.getUser(), u1);
		assertTrue(p1.getComments().contains(c1));
		assertTrue(u1.getComments().contains(c1));
		assertTrue(u1.getLikedPosts().contains(p1));
		assertTrue(p1.getUsersWhoLiked().contains(u1));
		assertTrue(p1.getTags().contains(t1));
		assertTrue(t1.getPosts().contains(p1));
	}
	
	
	/**
	 * Tests user post deletion with own dependencies. Author removes post with their own interactions
	 * @result Post should remove all comments, likes and tags dependencies from the stored collections.
	 * Each comment's post should be null.
	 * Each comment's user should be null.
	 * All child comments should be removed from every commenter's comments list.
	 * Post should be removed from every likedUser's likedPost set.
	 * Post should be removed from every tag's taggedPost set.
	 */
	@Test
	public void UserDeletePostWithOwnCommentLikesAndTagTest() {
		c1 = new Comment("First!");
		Tag t1 = new Tag("test tag");
		u1.createPost(p1);
		p1.addTag(t1);
		u1.createComment(p1, c1);
		u1.likePost(p1);
		
		u1.removePost(p1);
		assertTrue(!u1.getCreatedPosts().contains(p1));
		assertNull(p1.getUser());
		assertNull(c1.getUser());
		assertNull(c1.getPost());
		assertEquals(p1.getComments().size(), 0);
		assertEquals(p1.getUsersWhoLiked().size(), 0);
		assertEquals(p1.getTags().size(), 0);
		assertTrue(!u1.getComments().contains(c1));
		assertTrue(!u1.getLikedPosts().contains(p1));
		assertTrue(!t1.getPosts().contains(p1));
	}
	
	
	/**
	 * Tests user post creation with other dependencies. Multiple users interacts with author's post.
	 * @result Post should be added to the author's createdPost list.
	 * The post's user should be the author.
	 * Comment should be added to post's comments list and commenter's comments list.
	 * The comment's user should be the commenter.
	 * Post should be added to liker's likedPosts set.
	 * Liker should be added to post's likedUsers set.
	 * Tag should be added to post's tags set.
	 */
	@Test
	public void UserCreatePostWithOthersCommentLikesAndTagTest() {
		User u2 = new User("benDover", "password2");
		c1 = new Comment("First!");
		Tag t1 = new Tag("test tag");
		
		u2.createPost(p1);
		p1.addTag(t1);
		u1.createComment(p1, c1);
		u1.likePost(p1);
		
		assertEquals(p1.getUser(), u2);
		assertTrue(u2.getCreatedPosts().contains(p1));
		assertEquals(c1.getUser(), u1);
		assertTrue(p1.getComments().contains(c1));
		assertTrue(u1.getComments().contains(c1));
		assertTrue(u1.getLikedPosts().contains(p1));
		assertTrue(p1.getUsersWhoLiked().contains(u1));
		assertTrue(p1.getTags().contains(t1));
		assertTrue(t1.getPosts().contains(p1));
	}
	
	
	/**
	 * Tests user post deletion with other dependencies. Author removes post with interactions from other users.
	 * @result Post should remove all comments, likes and tags dependencies from the stored collections.
	 * Each comment's post should be null.
	 * Each comment's user should be null.
	 * All child comments should be removed from every commenter's comments list.
	 * Post should be removed from every likedUser's likedPost set.
	 * Post should be removed from every tag's taggedPost set.
	 */
	@Test
	public void UserDeletePostWithOthersCommentLikesAndTagTest() {
		User u2 = new User("benDover", "password2");
		c1 = new Comment("First!");
		Tag t1 = new Tag("test tag");
		u2.createPost(p1);
		p1.addTag(t1);
		u1.createComment(p1, c1);
		u1.likePost(p1);
		
		u2.removePost(p1);
		assertTrue(!u2.getCreatedPosts().contains(p1));
		assertNull(p1.getUser());
		assertNull(c1.getUser());
		assertNull(c1.getPost());
		assertEquals(p1.getComments().size(), 0);
		assertEquals(p1.getUsersWhoLiked().size(), 0);
		assertEquals(p1.getTags().size(), 0);
		assertTrue(!u1.getComments().contains(c1));
		assertTrue(!u1.getLikedPosts().contains(p1));
		assertTrue(!t1.getPosts().contains(p1));
	}
	
	
	/**
	 * Tests multiple users interacting with same posts.
	 * @result All comments should be added to post's comments list and respective commenter's comments list.
	 * The respective comment's user should be the commenter.
	 * Post should be added to respective liker's likedPosts set.
	 * All likers should be added to post's likedUsers set.
	 */
	@Test
	public void MultipleUsersCommentAndLikeOthersPostTest() {
		User u2 = new User("benDover", "password2");
		User u3 = new User("barryGrey", "password3");
		c1 = new Comment("First!");
		Comment c2 = new Comment("Last!");
		
		u1.createPost(p1);
		u2.createComment(p1, c1);
		u3.createComment(p1, c2);
		u2.likePost(p1);
		u3.likePost(p1);
		
		assertEquals(c1.getUser(), u2);
		assertEquals(c2.getUser(), u3);
		assertTrue(p1.getComments().contains(c1));
		assertTrue(p1.getComments().contains(c2));
		assertTrue(p1.getUsersWhoLiked().contains(u2));
		assertTrue(p1.getUsersWhoLiked().contains(u3));
		assertTrue(u2.getLikedPosts().contains(p1));
		assertTrue(u3.getLikedPosts().contains(p1));
		assertTrue(u2.getComments().contains(c1));
		assertTrue(u3.getComments().contains(c2));
	}
	
	
	// ## Modifying others posts and comments ##
	
	/**
	 * Tests user inability to create an existing post. 
	 * User should not be able to author an already existing post.
	 * @result Post should be added to the user's createdPost list.
	 * The post's user should be the original author.
	 */
	@Test
	public void UserAddOtherUsersPost() {
		User u2 = new User("benDover", "password2");
		u2.createPost(p1);
		
		u1.createPost(p1); // not allowed to use same object
		
		assertTrue(u2.getCreatedPosts().contains(p1));
		assertTrue(!u1.getCreatedPosts().contains(p1));
		assertTrue(p1.getUser().equals(u2));
	}
	
	/**
	 * Tests user inability to delete another user's post. 
	 * User should not be able to delete another user's existing post.
	 * @result Post should not be removed from the author's createdPost list.
	 * The post's user should still be the original author.
	 */
	@Test
	public void UserRemoveOtherUsersPost() {
		User u2 = new User("benDover", "password2");
		u2.createPost(p1);
		
		u1.removePost(p1);
		assertTrue(u2.getCreatedPosts().contains(p1));
	}
	
	/**
	 * Tests user inability to create an existing comment. 
	 * User should not be able to author an already existing comment.
	 * @result Comment should be added to the user's comments list.
	 * The comment's user should be the original author.
	 */
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
	
	/**
	 * Tests user inability to delete an existing comment. 
	 * User should not be able to delete an already existing comment.
	 * @result Comment should not be removed from the user's comments list.
	 * The comment's user should still be the original author.
	 */
	@Test 
	public void UserAddCommenttoOtherPost() {
		Post p2 = new Post("this is a post");
		c1 = new Comment("First!");
		u1.createComment(p1, c1);
		
		u1.createComment(p2, c1); // not allowed to use same object
		
		assertTrue(u1.getComments().contains(c1));
		assertTrue(p1.getComments().contains(c1));
		assertTrue(!p2.getComments().contains(c1));
		assertEquals(c1.getPost(), p1);
	}
	
	
	// ## Creation and Deletion of Null Objects ##
	
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
