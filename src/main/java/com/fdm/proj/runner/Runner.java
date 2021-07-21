package com.fdm.proj.runner;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.entities.Post;
import com.fdm.proj.entities.User;


public class Runner {

	
	public static void main(String[] args) {
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("SocialMediaProject");
	UserDAO userDAO = new UserDAO(emf);
	PostDAO postDAO = new PostDAO(emf);
	
	User u1 = new User("johnAdams", "password1");
	User u2 = new User("benDover", "password2");
	Post p1 = new Post("testPost 123");
	
	userDAO.add(u1);
	userDAO.add(u2);
	
//	userDAO.delete(2);
//	userDAO.findById(1);
//	userDAO.updateElementUsername(1, "johnDoe");
//
//	List<User> users = userDAO.findAll();
//	for (User u : users) {
//		System.out.println(u.getUsername());
//	}
	
	User foundUser = userDAO.findByUsername("johnAdams");
	System.out.println(foundUser.getPassword());
	
	u1.createPost(p1);
	userDAO.updateUser(u1.getUserId(), u1); // not to update to cascade changes to DB
	
	
	List<Post> p = postDAO.findAll();
	System.out.println(p);
	for (Post post : p) {
		System.out.println(post.getBody());
	}
	
	}
}
