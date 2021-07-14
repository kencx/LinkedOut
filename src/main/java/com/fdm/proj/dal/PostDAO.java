package com.fdm.proj.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fdm.proj.entities.Post;


public class PostDAO {
	
private EntityManagerFactory emf;
	
	public PostDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	
	public void addPost(Post post) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.persist(post);
		et.commit();
		em.close();
	}
	
	public List<Post> findAllPosts() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT p FROM Post p", Post.class);
		List<Post> posts = query.getResultList();
		
		em.close();
		return posts;
	}

	
	
}
