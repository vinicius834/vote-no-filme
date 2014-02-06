package br.org.votenofilme.classestotests;

import java.util.List;
import br.org.votenofilme.models.Film;

public class CreateFilmsToTest {
	private Film film = null;
	private Film film1 = null;
	private Film film2 = null;
	private Film film3 = null;
	private List<Film> films = null;
	private static CreateFilmsToTest createFilmsToTest = null; 
	
	public static CreateFilmsToTest getInstance() {
		if (createFilmsToTest == null) {
			createFilmsToTest = new CreateFilmsToTest();
		}
		return createFilmsToTest;
	}
	
	public CreateFilmsToTest() {
		films = (List<Film>) Film.list();
		filmsInitialization(films);
	}
	
	private void filmsInitialization(List<Film> films) {
		setFilm(films.get(0));
		setFilm1(films.get(1));
		setFilm2(films.get(2));
		setFilm3(films.get(3));
	}

	public Film getFilm() {
		return film;
	}

	private void setFilm(Film film) {
		this.film = film;
	}

	public Film getFilm1() {
		return film1;
	}

	private void setFilm1(Film film1) {
		this.film1 = film1;
	}

	public Film getFilm2() {
		return film2;
	}

	private void setFilm2(Film film2) {
		this.film2 = film2;
	}

	public Film getFilm3() {
		return film3;
	}

	private void setFilm3(Film film3) {
		this.film3 = film3;
	}	
}