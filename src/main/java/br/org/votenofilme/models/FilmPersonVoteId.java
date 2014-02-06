package br.org.votenofilme.models;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class FilmPersonVoteId implements Serializable {
	@ManyToOne
	private Person person;
	
	@ManyToOne
	private Film film;
	
	public Film getFilm() {
		return film;
	}
	
	public void setFilm(Film film) {
		this.film = film;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((film == null) ? 0 : film.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmPersonVoteId other = (FilmPersonVoteId) obj;
		if (film == null) {
			if (other.film != null)
				return false;
		} else if (!film.equals(other.film))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}	
}