package com.fdm.proj.dal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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


public class TestObjectDAO {
	
	@Mock
	private EntityManagerFactory emf;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private EntityTransaction et;

	@InjectMocks
	private UserDAO userDAO;
	private User u1, u2;
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		when(emf.createEntityManager()).thenReturn(em);
		when(em.getTransaction()).thenReturn(et);
		
		doNothing().when(et).begin();
		doNothing().when(et).commit();
		doNothing().when(em).close();
		
		u1 = new User("johnAdams", "password1");
		u2 = new User("benDover", "password2");
	}

	
	@Test
	public void addUserTest() {
		doNothing().when(em).persist(u1);
		userDAO.add(u1);
		
		verify(et, times(1)).begin();
		verify(em, times(1)).persist(u1);
		verify(et, times(1)).commit();
		verify(em, times(1)).close();
	}
	
	
	@Test
	public void findUserByIdTest() {
		int id = u1.getUserId();
		when(em.find(User.class, id)).thenReturn(u1);
		
		User user = userDAO.findById(u1.getUserId());
		assertSame(u1, user);
		verify(em, times(1)).close();
	}
	
	
	@Test
	public void findAllUsersTest() {
		List<User> users = new ArrayList<>();
		TypedQuery<User> query = mock(TypedQuery.class);
		
		String queryString = "SELECT e FROM " + User.class.getName() + " e";
		when(em.createQuery(queryString, User.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(users);
		
		List<User> actual = userDAO.findAll();
		verify(em, times(1)).createQuery(queryString, User.class);
		verify(query, times(1)).getResultList();
		assertSame(users, actual);
	}
	
	
	@Test
	public void deleteUserByIdTest() {
		int id = u1.getUserId();
		when(em.find(User.class, id)).thenReturn(u1);

		userDAO.delete(id);
		verify(et, times(1)).begin();
		verify(em, times(1)).find(User.class, id);
		verify(em, times(1)).remove(u1);
		verify(et, times(1)).commit();
		verify(em, times(1)).close();
	}
	
}
