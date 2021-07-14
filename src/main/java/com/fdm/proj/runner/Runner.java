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
//		UserDAO userDAO = new UserDAO(emf);
//		PostDAO postDAO = new PostDAO(emf);
//		
//		User u1 = new User("johnAdams", "password1");
//		User u2 = new User("benDover", "password2");
//		User u3 = new User("mikeTan", "password3");

//		userDAO.addUser(u2);
//		userDAO.addUser(u3);
		
//		userDAO.deleteUser(2);
//		userDAO.findUser(1);
//		userDAO.updateUserUsername(3, "mikeLim");
		
//		List<User> users = userDAO.findAllUsers();
//		for (User u : users) {
//			System.out.println(u.getUsername());
//		}
		
		
//		Post post = new Post("testPost", "this is a test post");
//		u1.createPost(post);
//		userDAO.addUser(u1);
//		
//		List<Post> posts = postDAO.findAllPosts();
//		for (Post p : posts) {
//			System.out.println(p.getBody());
//		}
//		
		
	}
}
