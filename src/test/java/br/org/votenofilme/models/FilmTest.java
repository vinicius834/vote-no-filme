package br.org.votenofilme.models;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FilmTest {
	private Film film = null;
	private Film film1 = null;
	private Film film2 = null;

	@Before
	public void setUp() throws Exception {
		film = new Film("Velozes e Furiosos", "");
		film1 = new Film("Velozes e Furioso 2", "");
		film2 = new Film("Velozes e Furiosos 3", "");
		persistFilmsToTest();
	}

	private void persistFilmsToTest() {
		film.save();
		film1.save();
		film2.save();
	}
	
	@Test
	public void saveTest() {
		List<Film> films = (List<Film>) Film.list();
		Assert.assertFalse(films.isEmpty());
	}
	
	@Test
	public void listTest() {
		List<Film> films = (List<Film>) Film.list();
		Assert.assertFalse(films.isEmpty());
		Assert.assertTrue(films.size() > 0);
	}
	
	@Test
	public void deleteTest() {
		long idFilm = film.getId();
		film.delete();
		Film filmFind = Film.findById(idFilm);
		Assert.assertTrue(filmFind == null);
	}
	
	@Test
	public void updateTest() {
		String oldName = film.getName();
		String newName = "Filme Qualquer";
		film.setName(newName);
		film.update();
		Film personFind = Film.findById(film.getId());
		Assert.assertTrue(personFind.getName().equals(newName));
		Assert.assertFalse(personFind.getName().equals(oldName));
	}
}