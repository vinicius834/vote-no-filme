package br.org.votenofilme.db.impl;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.EntityManager;

import br.org.votenofilme.db.DAO;

public class DAOImpl<T, K extends Serializable> implements DAO<T, K> {
	
	private Class<T> type;
	private EntityManager entityManager;
	
	public DAOImpl(Class<T> type) {
		this.type = type;
	}
	
	public EntityManager getEntityManager() {
		return EntityManagerProvider.getEntityManager();
	}
	
	public void insert(final T object) {
		entityManager = getEntityManager();
		boolean completed = false;
		
		if (object == null) {
			throw new IllegalArgumentException("Object não pode ser nulo.");
		}
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(object);
			entityManager.getTransaction().commit();
			completed = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!completed) {
				entityManager.getTransaction().rollback();
			}
		}
	}
	
	public T update(final T object) {
		entityManager = getEntityManager();
		boolean completed = false;
		T toReturn = null;
		if (object == null) {
			throw new IllegalArgumentException("Object não pode ser nulo.");
		}
		try {
			entityManager.getTransaction().begin();
			toReturn = entityManager.merge(object);
			entityManager.getTransaction().commit();
			completed = true;
			return toReturn;
		} catch (Exception e) {
			e.printStackTrace();
			return toReturn;
		} finally {
			if (!completed) {
				entityManager.getTransaction().rollback();
			}
		}
	}
	
	public void delete(T object) {
		entityManager = getEntityManager();
		boolean completed = false;
		if (object == null) {
			throw new IllegalArgumentException("Object não pode ser nulo.");
		}
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(object);
			entityManager.getTransaction().commit();
			completed = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!completed) {
				entityManager.getTransaction().rollback();
			}
		}
	}
	
	public Collection<T> list() {
		entityManager = getEntityManager();
		return entityManager.createQuery("from " + type.getSimpleName()).getResultList();
	}
	
	public Collection<T> list(StringBuilder query) {
		entityManager = getEntityManager();
		return entityManager.createQuery(query.toString()).getResultList();
	}
	
	public T findById(final K id) {
		entityManager = getEntityManager();
		return entityManager.find(type, id);
	}
}