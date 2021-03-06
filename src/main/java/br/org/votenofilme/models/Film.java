package br.org.votenofilme.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import br.org.votenofilme.db.impl.DAOImpl;
import br.org.votenofilme.db.DAO;

@Entity
@Table(name="FILM")
public class Film implements Serializable {

	private static final DAO<Film, Long> DAO = new DAOImpl<Film, Long>(Film.class);
	
	@Id
	@Column(name="FILM_ID")
	@SequenceGenerator(name="seq_film_id", sequenceName="PUBLIC.SEQ_FILM_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_film_id")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="URL_IMAGE")
	private String urlImage;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "filmPersonVoteId.film")
	private Set<FilmPersonVote> filmPersonVotes = new HashSet<FilmPersonVote>();
	
	public Film() {
	}
	
	public Film(Long id) {
	}
	
	public Film(String name, String urlImage) {
		this.name = name;
		this.urlImage = urlImage;
	}
	
	public Film(Long id, String name, String urlImage) {
		this.name = name;
		this.urlImage = urlImage;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<FilmPersonVote> getFilmPersonVotes() {
		return filmPersonVotes;
	}
	
	public void setFilmPersonVotes(Set<FilmPersonVote> filmPersonVotes) {
		this.filmPersonVotes = filmPersonVotes;
	}
	
	public String getUrl() {
		return urlImage;
	}
	
	public void setUrl(String urlImage) {
		this.urlImage = urlImage;
	}

	public void save() {
		DAO.insert(this);
	}
	
	public void update() {
		DAO.update(this);
	}
	
	public static Film findById(final long id) {
		return DAO.findById(id);
	}
	
	public static Collection<Film> list() {
		return DAO.list();
	}
	
	public void delete() {
		DAO.delete(this);
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}