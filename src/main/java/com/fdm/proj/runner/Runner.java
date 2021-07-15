package com.fdm.proj.runner;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdm.proj.dal.CommentDAO;
import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.entities.Comment;
import com.fdm.proj.entities.Post;
import com.fdm.proj.entities.Tag;
import com.fdm.proj.entities.User;


public class Runner {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SocialMediaProject");
		UserDAO userDAO = new UserDAO(emf);
		PostDAO postDAO = new PostDAO(emf);
		CommentDAO commentDAO = new CommentDAO(emf);
		
		User u1 = new User("johnAdams", "password1");
		User u2 = new User("benDover", "password2");

	
		Post p1 = new Post("testPost", "this is a test post");
		Post p2 = new Post("Hello", "Hello World!");

		Comment c1 = new Comment("First!");
		Tag t1 = new Tag("Test tag");
		
		
		// Testing DAO CRUD methods
//		userDAO.add(u1);
//		userDAO.add(u2);
	
//		userDAO.delete(2);
//		userDAO.findById(1);
//		userDAO.updateElementUsername(1, "johnDoe");
//
//		List<User> users = userDAO.findAll();
//		for (User u : users) {
//			System.out.println(u.getUsername());
//		}
		
		
		// Testing User actions
		u1.createPost(p1);
		u2.createPost(p2);
		
		userDAO.add(u1);
		userDAO.add(u2);
		
//		u1.likePost(p2);
//		u1.createComment(p2, c1);
//		userDAO.add(u1);
	}
}
