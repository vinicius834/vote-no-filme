package br.org.votenofilme.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import br.org.votenofilme.db.impl.DAOImpl;

@Entity
@Table(name="FILM_PERSON_VOTE")
@AssociationOverrides({
	@AssociationOverride(name = "filmPersonVoteId.person", 
		joinColumns = @JoinColumn(name = "PERSON_ID")),
	@AssociationOverride(name = "filmPersonVoteId.film", 
		joinColumns = @JoinColumn(name = "FILM_ID")) })
public class FilmPersonVote implements Serializable, Comparable<FilmPersonVote> {
	
	private static final DAOImpl<FilmPersonVote, Long> DAO = new DAOImpl<FilmPersonVote, Long>(FilmPersonVote.class);
	
	@EmbeddedId
	private FilmPersonVoteId filmPersonVoteId = new FilmPersonVoteId();
	
	@Column(name="NUMBER_OF_VOTES")
	private int numberOfVotes;
	
	public FilmPersonVote() {
	}
	
	public FilmPersonVote(FilmPersonVoteId filmPersonVoteId, int numberOfVotes) {
		super();
		this.filmPersonVoteId = filmPersonVoteId;
		this.numberOfVotes = numberOfVotes;
	}

	public FilmPersonVoteId getFilmPersonVoteId() {
		return filmPersonVoteId;
	}

	public void setFilmPersonVoteId(FilmPersonVoteId filmPersonVoteId) {
		this.filmPersonVoteId = filmPersonVoteId;
	}

	public int getNumberOfVotes() {
		return numberOfVotes;
	}
	
	public void setNumberOfVotes(int numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}
	
	public Film getFilm() {
		return getFilmPersonVoteId().getFilm();
	}
	
	public void setFilm(Film film) {
		getFilmPersonVoteId().setFilm(film);
	}
	
	public Person getPerson() {
		return getFilmPersonVoteId().getPerson();
	}
	
	public void setPerson(Person person) {
		getFilmPersonVoteId().setPerson(person);
	}
	
	public void save() {
		DAO.insert(this);
	}
	
	public static Collection<FilmPersonVote> list() {
		return DAO.list();
	}
	
	public static Collection<FilmPersonVote> listByPerson(long id) {
		StringBuilder query = new StringBuilder("from FilmPersonVote ");
		query.append("where PERSON_ID = " + id);
		return DAO.list(query);
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((filmPersonVoteId.getFilm().getId() == null) ? 0 : filmPersonVoteId.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmPersonVote other = (FilmPersonVote) obj;
		if (filmPersonVoteId == null) {
			if (other.filmPersonVoteId != null)
				return false;
		} else if (!filmPersonVoteId.equals(other.filmPersonVoteId))
			return false;
		return true;
	}

	public int compareTo(FilmPersonVote o) {
		return this.getFilm().getId().compareTo(o.getFilm().getId());
	}
}