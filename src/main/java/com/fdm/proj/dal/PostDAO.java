package com.fdm.proj.dal;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdm.proj.entities.Post;


public class PostDAO extends ObjectDAO<Post> {

	public PostDAO(EntityManagerFactory emf) {
		super(emf);
	}	
	

	public void updatePost(int id, Post newPost) {
		em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.merge(newPost);
		et.commit();
		em.close();
	}

	@Override
	protected Class<Post> getEntityClass() {
		return Post.class;
	}

}
