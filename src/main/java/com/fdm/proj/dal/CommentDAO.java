package com.fdm.proj.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdm.proj.model.Comment;

@Repository
public class CommentDAO extends ObjectDAO<Comment> {

	@Autowired
	public CommentDAO(EntityManagerFactory emf) {
		super(emf);
	}

	public void updateCommentBody(int id, String commentBody) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Comment comment = em.find(Comment.class, id);
		comment.setCommentBody(commentBody);
		et.commit();
		em.close();		
	}

	@Override
	protected Class<Comment> getEntityClass() {
		return Comment.class;
	}

}
