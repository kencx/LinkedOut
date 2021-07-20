package com.fdm.proj.dal;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.proj.entities.User;


public class TestUserDAO {

	@Mock
	private EntityManagerFactory emf;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private EntityTransaction et;

	@InjectMocks
	private UserDAO userDAO;
	private User u1;
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		when(emf.createEntityManager()).thenReturn(em);
		when(em.getTransaction()).thenReturn(et);
		
		doNothing().when(et).begin();
		doNothing().when(et).commit();
		doNothing().when(em).close();
		
		u1 = new User("johnAdams", "password1");
	}
	
	
	@Test
	public void findByUsernameTest() {
		String testUsername = "testUsername";
		List<User> users = new ArrayList<>();
		TypedQuery<User> query = mock(TypedQuery.class);
		
		String queryString = "SELECT u FROM User u WHERE u.username LIKE :username";
		when(em.createQuery(queryString, User.class)).thenReturn(query);
		when(query.setParameter(anyString(), anyString())).thenReturn(query);
		when(query.getResultList()).thenReturn(users);
		
		userDAO.findByUsername(testUsername);
		verify(em, times(1)).createQuery(queryString, User.class);
		verify(query, times(1)).getResultList();
	}
}
