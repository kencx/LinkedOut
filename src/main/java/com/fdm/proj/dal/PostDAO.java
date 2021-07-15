package com.fdm.proj.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdm.proj.entities.Post;


public class PostDAO extends ObjectDAO<Post> {

	public PostDAO(EntityManagerFactory emf) {
		super(emf);
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


	@Override
	protected Class<Post> getEntityClass() {
		return Post.class;
	}

}
