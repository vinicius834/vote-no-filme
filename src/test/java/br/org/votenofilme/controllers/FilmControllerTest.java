package br.org.votenofilme.controllers;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.org.votenofilme.classestotests.CreateFilmsToTest;
import br.org.votenofilme.controllers.FilmController;
import br.org.votenofilme.models.Film;

public class FilmControllerTest {

	private MockSerializationResult resultSerialization = null;
	private FilmController filmController = null;
	private CreateFilmsToTest createFilmsToTest = null;
	
	@Before
	public void setUp() throws Exception {
		resultSerialization = new MockSerializationResult();
		filmController = new FilmController(resultSerialization);
		createFilmsToTest = CreateFilmsToTest.getInstance();
	}

	@Test
	public void listTest() {
		filmController.list();
		List<Film> films = resultSerialization.included("films");
		Assert.assertFalse(films.isEmpty());
	}
	
	@Test
	public void containsFilmsInListTest() {
		filmController.list();
		List<Film> films = resultSerialization.included("films");
		Assert.assertTrue(films.contains(createFilmsToTest.getFilm()));
		Assert.assertTrue(films.contains(createFilmsToTest.getFilm1()));
		Assert.assertTrue(films.contains(createFilmsToTest.getFilm2()));
	}
}
