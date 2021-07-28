package com.fdm.proj.dal;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdm.proj.model.Post;

@Repository
public class PostDAO extends ObjectDAO<Post> {

	@Autowired
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
