package br.org.votenofilme.util;

import java.io.Serializable;
import br.org.votenofilme.models.Film;

public class FilmVote implements Serializable {
	private Film film;
	private int numberOfVotes;
	private String name;
	private String email;
	
	public Film getFilm() {
		return film;
	}
	
	public void setFilm(Film film) {
		this.film = film;
	}
	
	public int getNumberOfVotes() {
		return numberOfVotes;
	}
	
	public void setNumberOfVotes(int numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}

