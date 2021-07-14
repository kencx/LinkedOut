package com.fdm.proj.dal;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.fdm.proj.entities.User;

public class UserDAOTest {

	@Test
	public void test() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SocialMediaProject");
		UserDAO userDAO = new UserDAO(emf);
		
		User u1 = new User("johnAdams", "password1");
		
	}
	
}
