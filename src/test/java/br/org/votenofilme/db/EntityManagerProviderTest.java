package br.org.votenofilme.db;

import org.junit.Assert;
import org.junit.Test;

import br.org.votenofilme.classestotests.EntityManagerProviderToTest;

public class EntityManagerProviderTest {
	@Test
	public void getEntityManagerTest() {
		Assert.assertNotNull(EntityManagerProviderToTest.getEntityManager());
	}
}