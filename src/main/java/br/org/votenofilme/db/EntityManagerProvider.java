package br.org.votenofilme.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EntityManagerProvider {
	private static volatile ThreadLocal<EntityManager> ENTITY_MANAGERS = new ThreadLocal<EntityManager>();
	private static EntityManagerFactory entityManagerFactory;
	private static final String persistenceUnit = "VoteNoFilme";
	
	private static void createEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
			DataInitial.getInstance();
		}
	}
	
	public static void setEntityManager(EntityManager entityManager) {
		ENTITY_MANAGERS.set(entityManager);
	}
	
	public static EntityManager getEntityManager() {
		EntityManager em = ENTITY_MANAGERS.get();
		if (em != null) {
			return em;
		}
		return EntityManagerProvider.getInstance();
	}
	
	public static void clear() {
		ENTITY_MANAGERS.set(null);
	}
	
	private static EntityManager getInstance() {
		EntityManager em = ENTITY_MANAGERS.get();
		if (em != null) {
			return em;
		}
		createEntityManagerFactory();
		em = entityManagerFactory.createEntityManager();
		ENTITY_MANAGERS.set(em);
		return em;
	}
}