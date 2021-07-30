package com.fdm.proj.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.model.User;

public class TestOptionsService {
	
	@Mock
	private UserDAO userDAO;
	
	@InjectMocks
	private OptionsService optionsService;
	
	private User u1;
	private String currentPw, newPw, confirmPw;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		u1 = new User("johnAdams", "password123");
		optionsService.setUser(u1);
	}

	@Test
	public void updatePasswordSuccessTest() {
		currentPw = "password123";
		newPw = "P@ssword123";
		confirmPw = "P@ssword123";
		
		String message = optionsService.updatePassword(currentPw, newPw, confirmPw);
		assertEquals("Password updated!", message);
		assertEquals(newPw, u1.getPassword());
	}
	
	@Test
	public void currentPasswordWrongTest() {
		
		currentPw = "wrongPw";
		newPw = "P@ssword123";
		confirmPw = "P@ssword123";
		
		String message = optionsService.updatePassword(currentPw, newPw, confirmPw);
		assertEquals("Current password is wrong!", message);
		assertEquals("password123", u1.getPassword());
	}
	
	@Test
	public void newPasswordsNotMatchTest() {

		currentPw = "password123";
		newPw = "P@ssword123";
		confirmPw = "P@ssword1";
		
		String message = optionsService.updatePassword(currentPw, newPw, confirmPw);
		assertEquals("Passwords do not match!", message);
		assertEquals("password123", u1.getPassword());
	}
	
	@Test
	public void invalidNewPasswordTest() {

		currentPw = "password123";
		newPw = "abc";
		confirmPw = "abc";
		
		String message = optionsService.updatePassword(currentPw, newPw, confirmPw);
		assertEquals("Password must be at least 8 characters with at least 1 capital letter, number and !@#$%^&*", message);
		assertEquals("password123", u1.getPassword());
	}
	
	@Test
	public void updateAllFieldsTest() {
		
		String newFirstName = "Ben";
		String newLastName = "Lim";
		String newLocation = "Singapore";
		String newOccupation = "Developer";
		String bio = "Test bio";
		doNothing().when(userDAO).updateUser(u1.getUserId(), u1);
		
		String message = optionsService.update(newFirstName, newLastName, newLocation, newOccupation, bio);
		assertEquals("Particulars updated!", message);
		assertTrue(u1.getFirstname().equals(newFirstName));
		assertTrue(u1.getLastname().equals(newLastName));
		assertTrue(u1.getLocation().equals(newLocation));
		assertTrue(u1.getOccupation().equals(newOccupation));
		assertTrue(u1.getBio().equals(bio));
	}
	
	@Test
	public void UpdateOneFieldTest() {
		
		String newLocation = "Singapore";
		doNothing().when(userDAO).updateUser(u1.getUserId(), u1);
		
		String message = optionsService.update(null, null, newLocation, null, null);
		assertEquals("Particulars updated!", message);
		assertTrue(u1.getLocation().equals(newLocation));
	}
}

