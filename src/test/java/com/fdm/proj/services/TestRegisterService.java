package com.fdm.proj.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.entities.User;


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
		String expectedMessage = "Username must have 5 to 30 characters with letters and numbers!";
		
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
		String expectedMessage = "Password must be between 8-20 characters with letters, numbers and symbols";
		
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
