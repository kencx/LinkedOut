package com.fdm.proj.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fdm.proj.entities.User;


public class UserDAO {
	
	private EntityManagerFactory emf;
	
	public UserDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	
	public void addUser(User user) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.persist(user);
		et.commit();
		em.close();
	}
	
	public User findUser(int userId) {
		EntityManager em = emf.createEntityManager();
		User user = em.find(User.class, userId);	
		em.close();
		return user;	
	}
	
	public List<User> findAllUsers() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT u FROM User u", User.class);
		List<User> users = query.getResultList();
		
		em.close();
		return users;
	}
	
	public void deleteUser(int userId) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		User user = em.find(User.class, userId);
		em.remove(user);
		et.commit();
		em.close();
	}
	
	public void updateUserUsername(int userId, String newUsername) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		User user = em.find(User.class, userId);
		user.setUsername(newUsername);
		et.commit();
		em.close();
	}
	
	
}
