package com.fdm.proj.services;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.entities.User;


public class TestLoginService {
	
	@Mock
	private UserDAO userDAO;
	
	@InjectMocks
	private LoginService loginService;
	private User user;
	private String username, password;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);	
		username = "johnAdams";
		password = "password123";
		user = new User(username, password);
	}
	
	
	@Test
	public void verifyExistingUserTest() {
		when(userDAO.findByUsername(username)).thenReturn(user);
		User verifiedUser = loginService.verifyUser(username, password);
		
		assertSame(user, verifiedUser);
		assertTrue(verifiedUser.getUsername().equals(username));
	}
	
	@Test
	public void verifyNonExistentUsernameTest() {
		String wrongUsername = "wrongUsername";
		when(userDAO.findByUsername(wrongUsername)).thenReturn(null);
		User verifiedUser = loginService.verifyUser(wrongUsername, password);
		
		assertNull(verifiedUser);
	}
	
	@Test
	public void wrongPasswordGivenWhenVerifyUserTest() {
		String wrongPassword = "wrongPassword";
		when(userDAO.findByUsername(username)).thenReturn(user);
		User verifiedUser = loginService.verifyUser(username, wrongPassword);

		assertNull(verifiedUser);
	}
}
