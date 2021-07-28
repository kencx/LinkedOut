package com.fdm.proj.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.model.User;


public class TestRegisterService {
	
	@Mock
	private UserDAO userDAO;
	
	@InjectMocks
	private RegisterService rs;	
	private String username, password, confirmPassword, firstname, lastname;
	private User user;
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		firstname = "John";
		lastname = "Adams";
	}
	
	
	@Test
	public void emptyFieldsTest() {
		username = null;
		password = "Passw0rd@";
		confirmPassword = "Passw0rd@";
		String expectedMessage = "Required fields are empty!";
		
		boolean status = rs.registerUser(username, password, confirmPassword, firstname, lastname);
		assertFalse(status);
		assertEquals(expectedMessage, rs.getErrorMessage());
	}
	
	@Test
	public void passwordMatchTest() {
		username = "johnAdams";
		password = "Passw0rd@";
		confirmPassword = "failedMatch";
		String expectedMessage = "Passwords do not match!";
		
		boolean status = rs.registerUser(username, password, confirmPassword, firstname, lastname);
		assertFalse(status);
		assertEquals(expectedMessage, rs.getErrorMessage());
	}
	
	@Test
	public void duplicateUsernameTest() {
		username = "johnAdams";
		password = "Passw0rd@";
		confirmPassword = "Passw0rd@";
		String expectedMessage = "Username already exists!";
		
		user = new User("johnAdams", "Passw0rd@");
		when(userDAO.findByUsername(username)).thenReturn(user);
		
		boolean status = rs.registerUser(username, password, confirmPassword, firstname, lastname);
		assertFalse(status);
		assertEquals(expectedMessage, rs.getErrorMessage());
	}
	
	@Test
	public void usernameRegexTest() {
		username = "abc";
		password = "Passw0rd@";
		confirmPassword = "Passw0rd@";
		String expectedMessage = "Username must be more than 5 characters!";
		
		when(userDAO.findByUsername(username)).thenReturn(user);
		
		boolean status = rs.registerUser(username, password, confirmPassword, firstname, lastname);
		assertFalse(status);
		assertEquals(expectedMessage, rs.getErrorMessage());
	}
	
	@Test
	public void passwordRegexTest() {
		username = "johnAdams";
		password = "password";
		confirmPassword = "password";
		String expectedMessage = "Password must be at least 8 characters with at least 1 capital letter, number and !@#$%^&*";
		
		when(userDAO.findByUsername(username)).thenReturn(user);
		
		boolean status = rs.registerUser(username, password, confirmPassword, firstname, lastname);
		assertFalse(status);
		assertEquals(expectedMessage, rs.getErrorMessage());
	}
	
	@Test
	public void registerUserSuccessFulTest() {
		username = "johnAdams";
		password = "Passw0rd@";
		confirmPassword = "Passw0rd@";
		
		user = new User(username, password);
		when(userDAO.findByUsername(username)).thenReturn(null);
		doNothing().when(userDAO).add(user);
		
		boolean status = rs.registerUser(username, password, confirmPassword, firstname, lastname);
		assertTrue(status);
		assertNull(rs.getErrorMessage());
		verify(userDAO, times(1)).add(any(User.class));
	}
}
