package br.org.votenofilme.classestotests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EntityManagerProviderToTest {
	private static volatile ThreadLocal<EntityManager> ENTITY_MANAGERS = new ThreadLocal<EntityManager>();
	private static EntityManagerFactory entityManagerFactory;
	
	private static void createEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("VoteNoFilmeTest");
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
		return EntityManagerProviderToTest.getInstance();
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
	
	public static void close() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
			entityManagerFactory = null;
		}
	}
}