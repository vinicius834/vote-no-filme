package br.org.votenofilme.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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

@Entity
@Table(name="PERSON")
public class Person implements Serializable {

	private static final DAOImpl<Person, Long> DAO = new DAOImpl<Person, Long>(Person.class);
	
	@Id
	@Column(name = "PERSON_ID")
	@SequenceGenerator(name="seq_person_id", sequenceName="PUBLIC.SEQ_PERSON_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_person_id")
//	@GeneratedValue
	private Long id;
	
	@Column(name="NAME", unique=true)
	private String name;
	
	@Column(name="EMAIL")
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "filmPersonVoteId.person", cascade = CascadeType.PERSIST)
	private Set<FilmPersonVote> filmPersonVotes = new HashSet<FilmPersonVote>();
	
	public Person() {
	}

	public Person(String name, String email) {
		this.name = name;
		this.email = email;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<FilmPersonVote> getFilmPersonVotes() {
		return filmPersonVotes;
	}
	
	public void setFilmPersonVotes(Set<FilmPersonVote> filmPersonVotes) {
		this.filmPersonVotes = filmPersonVotes;
	}

	public void save() {
		DAO.insert(this);
	}
	
	public void update() {
		DAO.update(this);
	}
	
	public static Person findById(final long id) {
		return DAO.findById(id);
	}
	
	public void delete() {
		DAO.delete(this);
	}
	
	public static Collection<Person> list() {
		return DAO.list();
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}