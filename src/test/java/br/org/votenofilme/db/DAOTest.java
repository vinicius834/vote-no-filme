package br.org.votenofilme.db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




import br.org.votenofilme.models.Film;

import java.util.List;

public class DAOTest {

	private Film film = null;
	private Film film1 = null;
	private Film film2 = null;
	private static final DAO<Film, Long> DAO = new DAO<Film, Long>(Film.class);
	
	@Before
	public void setUp() throws Exception {
		film = new Film("Film", "");
		film1 = new Film("Film1", "");
		film2 = new Film("Film2", "");
		persistFilmToTest();
	}
	
	private void persistFilmToTest() {
		DAO.insert(film);
		DAO.insert(film1);
		DAO.insert(film2);
	}
	
	@Test
	public void testSave() {
		List<Film> entitiesToTest = (List<Film>) Film.list();
		Assert.assertFalse(entitiesToTest.isEmpty());
	}
	
	@Test
	public void updateTest() {
		String oldName = film.getName();
		String newName = "Film Qualquer";
		film.setName(newName);
		film.update();
		Film entityFind = Film.findById(film.getId());
		Assert.assertTrue(entityFind.getName().equals(newName));
		Assert.assertFalse(entityFind.getName().equals(oldName));
	}
	
	@Test
	public void deleteTest() {
		long idFilm = film.getId();
		film.delete();
		Film filmFind = Film.findById(idFilm);
		Assert.assertTrue(filmFind == null);
	}

	@Test
	public void testList() {
		List<Film> entitiesToTest = (List<Film>) DAO.list();
		Assert.assertFalse(entitiesToTest.isEmpty());
	}
}
