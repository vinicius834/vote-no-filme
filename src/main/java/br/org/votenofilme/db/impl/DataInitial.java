package br.org.votenofilme.db.impl;

import java.util.List;
import br.org.votenofilme.models.Film;

public class DataInitial {
	private Film film = null;
	private Film film1 = null;
	private Film film2 = null;
	private Film film3 = null;
	private Film film4 = null;
	private static DataInitial dataInitial = null;

	public static DataInitial getInstance() {
		if (dataInitial == null) {
			dataInitial = new DataInitial();
		}
		return dataInitial;
	}

	public DataInitial() {
		insertData();
	}

	private void insertData() {
		List<Film> films = (List<Film>) Film.list();
		if (films.isEmpty()) {
			film = new Film("Velozes e Furiosos", "/assets/images/velozesefuriosos.jpg");
			film1 = new Film("O Poderoso Chefão", "/assets/images/opoderosochefao.jpg");
			film2 = new Film("Clube da Luta", "/assets/images/clubedaluta.jpg");
			film3 = new Film("A Origem", "/assets/images/aorigem.jpg");
			film4 = new Film("Lobo de Wall Street", "/assets/images/lobo.jpg");

			film.save();
			film1.save();
			film2.save();
			film3.save();
			film4.save();
		}

	}
}