package br.org.votenofilme.controllers;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.org.votenofilme.models.Film;
import br.org.votenofilme.models.FilmPersonVote;
import br.org.votenofilme.models.Person;
import br.org.votenofilme.util.FilmVote;

public class FilmVoteControllerTest {

	private MockSerializationResult resultSerialization = null;
	private FilmVoteController filmVoteController = null;
	private List<FilmVote> filmsVotedPerson = null;
	private List<FilmVote> filmsVotedPerson1 = null;
	private List<FilmVote> filmsVotedPerson2 = null;
	private FilmVote filmVote = null;
	private FilmVote filmVote1 = null;
	private FilmVote filmVote2 = null;
	private Film film = null;
	private Film film1 = null;
	private Film film2 = null;
	private Film film3 = null;
	private Person person = null;
	private Person person1 = null;
	private Person person2 = null;
	
	@Before
	public void setUp() throws Exception {
		resultSerialization = new MockSerializationResult();
		filmVoteController = new FilmVoteController(resultSerialization);

		filmsVotedPerson = new ArrayList<FilmVote>();
		filmsVotedPerson1 = new ArrayList<FilmVote>();
		filmsVotedPerson2 = new ArrayList<FilmVote>();
		
		film = new Film("Velozes e Furiosos 1", "");
		film1 = new Film("Velozes e Furiosos 2", "");
		film2 = new Film("Velozes e Furiosos 3", "");
		film3 = new Film("Harry Potter", "");
		
		person = new Person("João", "");
		person1 = new Person("Steve Jobs", "");
		person2 = new Person("Messi", "");
		
		filmSave();
		createFilmVotePerson();
		createFilmVotePerson1();
		createFilmVotePerson2();
	}
	
	private void filmSave() {
		film.save();
		film1.save();
		film2.save();
		film3.save();
	}

	private void createFilmVotePerson() {
		filmVote = new FilmVote();
		filmVote.setFilm(film);
		filmVote.setName(person.getName());
		filmVote.setEmail("Person@mail");
		filmVote.setNumberOfVotes(2);
		filmsVotedPerson.add(filmVote);
		
		filmVote = new FilmVote();
		filmVote.setFilm(film1);
		filmVote.setName(person.getName());
		filmVote.setEmail("Person@mail");
		filmVote.setNumberOfVotes(2);
		filmsVotedPerson.add(filmVote);
		
		filmVote = new FilmVote();
		filmVote.setFilm(film2);
		filmVote.setName(person.getName());
		filmVote.setEmail("Person@mail");
		filmVote.setNumberOfVotes(1);
		filmsVotedPerson.add(filmVote);
		
		filmVote = new FilmVote();
		filmVote.setFilm(film3);
		filmVote.setName(person.getName());
		filmVote.setEmail("Person@mail");
		filmVote.setNumberOfVotes(1);
		filmsVotedPerson.add(filmVote);
	}
	
	private void createFilmVotePerson1() {
		filmVote1 = new FilmVote();
		filmVote1.setFilm(film);
		filmVote1.setName(person1.getName());
		filmVote1.setEmail("Person1@mail");
		filmVote1.setNumberOfVotes(3);
		filmsVotedPerson1.add(filmVote1);
		
		filmVote1 = new FilmVote();
		filmVote1.setFilm(film1);
		filmVote1.setName(person1.getName());
		filmVote1.setEmail("Person1@mail");
		filmVote1.setNumberOfVotes(2);
		filmsVotedPerson1.add(filmVote1);
		
		filmVote1 = new FilmVote();
		filmVote1.setFilm(film2);
		filmVote1.setName(person1.getName());
		filmVote1.setEmail("Person1@mail");
		filmVote1.setNumberOfVotes(1);
		filmsVotedPerson1.add(filmVote1);
	}
	
	private void createFilmVotePerson2() {
		filmVote2 = new FilmVote();
		filmVote2.setFilm(film);
		filmVote2.setName(person2.getName());
		filmVote2.setEmail("Person2@mail");
		filmVote2.setNumberOfVotes(1);
		filmsVotedPerson2.add(filmVote2);
		
		filmVote2 = new FilmVote();
		filmVote2.setFilm(film1);
		filmVote2.setName(person2.getName());
		filmVote2.setEmail("Person2@mail");
		filmVote2.setNumberOfVotes(3);
		filmsVotedPerson2.add(filmVote2);
		
		filmVote2 = new FilmVote();
		filmVote2.setFilm(film2);
		filmVote2.setName(person2.getName());
		filmVote2.setEmail("Person2@mail");
		filmVote2.setNumberOfVotes(1);
		filmsVotedPerson2.add(filmVote2);
		
		filmVote2 = new FilmVote();
		filmVote2.setFilm(film3);
		filmVote2.setName(person2.getName());
		filmVote2.setEmail("Person2@mail");
		filmVote2.setNumberOfVotes(2);
		filmsVotedPerson2.add(filmVote2);
	}
	
	@Test
	public void saveVoteTest() {
		Person personTest = null;
		filmVoteController.saveVote(filmsVotedPerson.toArray(new FilmVote[0]));
		personTest = resultSerialization.included("person");
		Assert.assertFalse(FilmPersonVote.listByPerson(personTest.getId()).isEmpty());
	
		filmVoteController.saveVote(filmsVotedPerson1.toArray(new FilmVote[0]));
		personTest = resultSerialization.included("person");
		Assert.assertFalse(FilmPersonVote.listByPerson(personTest.getId()).isEmpty());

		filmVoteController.saveVote(filmsVotedPerson2.toArray(new FilmVote[0]));
		personTest = resultSerialization.included("person");
		Assert.assertFalse(FilmPersonVote.listByPerson(personTest.getId()).isEmpty());
	}
}