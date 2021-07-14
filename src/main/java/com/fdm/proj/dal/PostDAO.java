package com.fdm.proj.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fdm.proj.entities.Post;
import com.fdm.proj.entities.User;


public class PostDAO extends ObjectDAO<Post> {

	public PostDAO(EntityManagerFactory emf) {
		super(emf);
	}

	
	@Override
	public Post findById(int id) {
		EntityManager em = emf.createEntityManager();
		Post post = em.find(Post.class, id);	
		em.close();
		return post;
	}

	
	@Override
	public List<Post> findAll() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT p FROM Post p", Post.class);
		List<Post> posts = query.getResultList();

		em.close();
		return posts;
	}

	
	@Override
	public void delete(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Post post = em.find(Post.class, id);
		em.remove(post);
		et.commit();
		em.close();
	}
	
	
	public void updateElementTitle(int id, String newTitle) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Post post = em.find(Post.class, id);
		post.setTitle(newTitle);
		et.commit();
		em.close();
	}

}
