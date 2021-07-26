package com.fdm.proj.dal;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.fdm.proj.entities.Tag;


public class TagDAO extends ObjectDAO<Tag> {

	public TagDAO(EntityManagerFactory emf) {
		super(emf);
	}
	
	public Tag findByTagName(String tagName) {

		em = emf.createEntityManager();
		TypedQuery<Tag> query = em.createQuery("SELECT t FROM Tag t WHERE t.tagName LIKE :tagName", Tag.class);
		query.setParameter("tagName", tagName);
		List<Tag> tags = query.getResultList();
		
		for (Tag tag : tags) {
			if (tag.getTagName().equals(tagName)) {
				return tag;
			}
		}
		return null;
	}

	@Override
	protected Class<Tag> getEntityClass() {
		return Tag.class;
	}


}
