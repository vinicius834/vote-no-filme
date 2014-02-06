package br.org.votenofilme.classestotests;

import br.org.votenofilme.models.Film;

public class CreateFilmsToTest {
	private Film film = null;
	private Film film1 = null;
	private Film film2 = null;
	private Film film3 = null;
	private static CreateFilmsToTest createFilmsToTest = null; 
	
	public static CreateFilmsToTest getInstance() {
		if (createFilmsToTest == null) {
			createFilmsToTest = new CreateFilmsToTest();
		}
		return createFilmsToTest;
	}
	
	public CreateFilmsToTest() {
		film = new Film("Velozes e Furiosos", "");
		film1 = new Film("A origem", "");
		film2 = new Film("Poderoso Chefão", "");
		film3 = new Film("Harry Potter", "");
		filmSave();
	}
	
	private void filmSave() {
		film.save();
		film1.save();
		film2.save();
		film3.save();
	}
	
	public Film getFilm() {
		return film;
	}
	
	public Film getFilm1() {
		return film1;
	}
	
	public Film getFilm2() {
		return film2;
	}

	public Film getFilm3() {
		return film3;
	}
}
