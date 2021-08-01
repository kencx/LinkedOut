package com.fdm.proj.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.TagDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.model.Comment;
import com.fdm.proj.model.Post;
import com.fdm.proj.model.Tag;
import com.fdm.proj.model.User;

public class TestHomeFeedService {

	@Mock
	private UserDAO userDAO;
	
	@Mock
	private PostDAO postDAO;
	
	@Mock
	private TagDAO tagDAO;
	
	@InjectMocks
	private HomeFeedService homeFeedService;
	
	private String mockPostBody;
	private User u1;
	private Post p1, p2;
	private List<Post> listOfPosts = new ArrayList<>();
	private Comment c1, c2;
	private Tag t1;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		mockPostBody = "test";
		u1 = new User("johnAdams", "password123");
		p1 = new Post(mockPostBody, Instant.now());
		c1 = new Comment("testComment");
		c2 = new Comment("testComment2");
		t1 = new Tag("testTag");
		
		doNothing().when(userDAO).updateUser(u1.getUserId(), u1);
	}
	
	@Test
	public void returnUserTest() {
		
		when(userDAO.findById(u1.getUserId())).thenReturn(u1);
		User returnedUser = homeFeedService.returnUser(u1.getUserId());
		assertEquals(returnedUser, u1);
	}
	
	@Test
	public void returnAllHomeFeedPostsTest() {
		
		p2 = new Post("test2", Instant.now());
		listOfPosts.add(p1);
		listOfPosts.add(p2);
		listOfPosts.sort((p1,p2) -> Long.compare(p1.getTimePassedInMilli(), p2.getTimePassedInMilli()));
		
		when(postDAO.findAll()).thenReturn(listOfPosts);
		List<Post> posts = homeFeedService.returnFeedPosts();
		
		assertEquals(posts, listOfPosts);
	}
	
	@Test
	public void createPostWithoutTagsTest() {
		
		String mockPostBody = "test";
		
		homeFeedService.userCreatePost(u1, mockPostBody, Instant.now(), null);
		Post createdPost = u1.getCreatedPosts().get(0);
		assertEquals(createdPost.getBody(), mockPostBody);
		assertEquals(createdPost.getUser(), u1);
	}
	
	@Test
	public void createPostWithNewTagTest() {
		
		List<Tag> listOfTags = new ArrayList<>();
		listOfTags.add(t1);
		
		when(tagDAO.findByTagName(t1.getTagName())).thenReturn(null);
		homeFeedService.userCreatePost(u1, mockPostBody, Instant.now(), listOfTags);
		
		Post createdPost = u1.getCreatedPosts().get(0);
		List<Tag> createdTags = new ArrayList<>(createdPost.getTags());
		Tag createdTag = createdTags.get(0);
		
		assertTrue(createdPost.getTags().contains(t1));
		assertTrue(createdTag.getPosts().contains(createdPost));
	}
	
	@Test
	public void createPostWithExistingTagTest() {
		
		List<Tag> listOfTags = new ArrayList<>();
		listOfTags.add(t1);
		assertTrue(t1.getPosts().size() == 0);
		
		when(tagDAO.findByTagName(t1.getTagName())).thenReturn(t1);
		homeFeedService.userCreatePost(u1, mockPostBody, Instant.now(), listOfTags);
		
		Post createdPost = u1.getCreatedPosts().get(0);
		List<Tag> existingTags = new ArrayList<>(createdPost.getTags());
		Tag tag = existingTags.get(0);
		
		assertTrue(createdPost.getTags().contains(t1));
		assertTrue(tag.getPosts().contains(createdPost));
	}
	
	@Test
	public void createPostWithExistingAndNonExistentTagTest() {
		
		Tag t2 = new Tag("testTag2");
		List<Tag> listOfTags = new ArrayList<>();
		listOfTags.add(t1);
		listOfTags.add(t2);
		assertTrue(t1.getPosts().size() == 0);
		assertTrue(t2.getPosts().size() == 0);
		
		when(tagDAO.findByTagName(t1.getTagName())).thenReturn(t1);
		when(tagDAO.findByTagName(t2.getTagName())).thenReturn(null);
		
		homeFeedService.userCreatePost(u1, mockPostBody, Instant.now(), listOfTags);
		
		Post createdPost = u1.getCreatedPosts().get(0);
		List<Tag> postTags = new ArrayList<>(createdPost.getTags());
		Tag existingTag = postTags.get(1);
		Tag nonExistentTag = postTags.get(0);
		
		assertTrue(postTags.contains(t1));
		assertTrue(postTags.get(0).getTagName().equals(t2.getTagName()));
		assertTrue(existingTag.getPosts().contains(createdPost));
		assertTrue(nonExistentTag.getPosts().contains(createdPost));
	}
	
	@Test
	public void createPostWith2ExistingAnd1NonExistentTagTest() {
		
		Tag t2 = new Tag("testTag2");
		Tag t3 = new Tag("testTag3");
		List<Tag> listOfTags = new ArrayList<>();
		listOfTags.add(t1);
		listOfTags.add(t2);
		listOfTags.add(t3);
		assertTrue(t1.getPosts().size() == 0);
		assertTrue(t2.getPosts().size() == 0);
		assertTrue(t3.getPosts().size() == 0);
		
		when(tagDAO.findByTagName(t1.getTagName())).thenReturn(t1);
		when(tagDAO.findByTagName(t2.getTagName())).thenReturn(null);
		when(tagDAO.findByTagName(t3.getTagName())).thenReturn(t3);
		
		homeFeedService.userCreatePost(u1, mockPostBody, Instant.now(), listOfTags);
		
		Post createdPost = u1.getCreatedPosts().get(0);
		List<Tag> postTags = new ArrayList<>(createdPost.getTags());
		Tag existingTag = postTags.get(1);
		Tag existingTag2 = postTags.get(2);
		Tag nonExistentTag = postTags.get(0);
		
		assertTrue(postTags.contains(t1));
		assertTrue(postTags.contains(t3));
		assertTrue(postTags.get(2).getTagName().equals(t2.getTagName()));
		assertTrue(existingTag.getPosts().contains(createdPost));
		assertTrue(existingTag2.getPosts().contains(createdPost));
		assertTrue(nonExistentTag.getPosts().contains(createdPost));
	}
	
	@Test
	public void createCommentTest() {
		
		String mockCommentBody = "test";
		doNothing().when(userDAO).updateUser(u1.getUserId(), u1);
		
		homeFeedService.userCreateComment(u1, p1, mockCommentBody, Instant.now());
		Comment createdComment = u1.getComments().get(0);
		assertEquals(createdComment.getCommentBody(), mockCommentBody);
		assertEquals(createdComment.getUser(), u1);
		assertEquals(p1.getComments().get(0).getCommentBody(), mockCommentBody);	
	}
	
	@Test
	public void likePostTest() {
		doNothing().when(postDAO).updatePost(p1.getPostId(), p1);
		
		homeFeedService.likePost(u1, p1);
		assertTrue(u1.getLikedPosts().contains(p1));
		assertTrue(p1.getUsersWhoLiked().contains(u1));
	}
	
	@Test
	public void returnPostTest() {
		when(postDAO.findById(p1.getPostId())).thenReturn(p1);
		
		Post returnedPost = homeFeedService.returnPost(p1.getPostId());
		assertEquals(p1, returnedPost);
	}
	
	@Test
	public void returnAllPostCommentsTest() {
		
		p1.addComment(c1);
		p1.addComment(c2);
		
		List<Comment> returnedListOfComments = homeFeedService.returnAllPostComments(p1);
		assertTrue(returnedListOfComments.contains(c1));
		assertTrue(returnedListOfComments.contains(c2));
	}
}
