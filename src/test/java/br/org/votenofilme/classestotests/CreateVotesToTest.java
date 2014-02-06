package br.org.votenofilme.classestotests;

import java.util.ArrayList;
import java.util.List;
import br.org.votenofilme.models.Film;
import br.org.votenofilme.models.FilmPersonVote;
import br.org.votenofilme.models.Person;
import br.org.votenofilme.util.FilmVote;

public class CreateVotesToTest {

	private Person person = null;
	private Person person1 = null;
	private Person person2 = null;
	private FilmVote filmVote = null;
	private FilmVote filmVote1 = null;
	private FilmVote filmVote2 = null;
	private List<FilmVote> filmsVotedPerson = null;
	private List<FilmVote> filmsVotedPerson1 = null;
	private List<FilmVote> filmsVotedPerson2 = null;
	private static CreateVotesToTest createVotesTest = null; 
	private int totalVotesFilm = 0;
	private int totalVotesFilm1 = 0;
	private int totalVotesFilm2 = 0;
	private int totalVotesFilm3 = 0;
	
	public CreateVotesToTest() {
		filmsVotedPerson = new ArrayList<FilmVote>();
		filmsVotedPerson1 = new ArrayList<FilmVote>();
		filmsVotedPerson2 = new ArrayList<FilmVote>();
		
		person = new Person("Person", "");
		person1 = new Person("Person 1", "");
		person2 = new Person("Person 2", "");
		
		personSave();
		createFilmVotePerson();
		createFilmVotePerson1();
		createFilmVotePerson2();
	}
	
	public static CreateVotesToTest getInstance() {
		if (createVotesTest == null) {
			createVotesTest = new CreateVotesToTest();
		}
		return createVotesTest;
	}
	
	private void createFilmVotePerson() {
		filmVote = new FilmVote();
		filmVote.setFilm(CreateFilmsToTest.getInstance().getFilm());
		filmVote.setName(person.getName());
		filmVote.setEmail("Person@mail");
		filmVote.setNumberOfVotes(2);
		filmsVotedPerson.add(filmVote);
		totalVotesFilm += filmVote.getNumberOfVotes();
		
		filmVote = new FilmVote();
		filmVote.setFilm(CreateFilmsToTest.getInstance().getFilm1());
		filmVote.setName(person.getName());
		filmVote.setEmail("Person@mail");
		filmVote.setNumberOfVotes(2);
		filmsVotedPerson.add(filmVote);
		totalVotesFilm1 += filmVote.getNumberOfVotes();
		
		filmVote = new FilmVote();
		filmVote.setFilm(CreateFilmsToTest.getInstance().getFilm2());
		filmVote.setName(person.getName());
		filmVote.setEmail("Person@mail");
		filmVote.setNumberOfVotes(1);
		filmsVotedPerson.add(filmVote);
		totalVotesFilm2 += filmVote.getNumberOfVotes();
		
		filmVote = new FilmVote();
		filmVote.setFilm(CreateFilmsToTest.getInstance().getFilm3());
		filmVote.setName(person.getName());
		filmVote.setEmail("Person@mail");
		filmVote.setNumberOfVotes(1);
		filmsVotedPerson.add(filmVote);
		totalVotesFilm3 += filmVote.getNumberOfVotes();
		
		saveVote(filmsVotedPerson, person);
	}
	
	private void createFilmVotePerson1() {
		filmVote1 = new FilmVote();
		filmVote1.setFilm(CreateFilmsToTest.getInstance().getFilm());
		filmVote1.setName(person1.getName());
		filmVote1.setEmail("Person1@mail");
		filmVote1.setNumberOfVotes(3);
		filmsVotedPerson1.add(filmVote1);
		totalVotesFilm += filmVote1.getNumberOfVotes();
		
		filmVote1 = new FilmVote();
		filmVote1.setFilm(CreateFilmsToTest.getInstance().getFilm1());
		filmVote1.setName(person1.getName());
		filmVote1.setEmail("Person1@mail");
		filmVote1.setNumberOfVotes(2);
		filmsVotedPerson1.add(filmVote1);
		totalVotesFilm1 += filmVote1.getNumberOfVotes();
		
		filmVote1 = new FilmVote();
		filmVote1.setFilm(CreateFilmsToTest.getInstance().getFilm2());
		filmVote1.setName(person1.getName());
		filmVote1.setEmail("Person1@mail");
		filmVote1.setNumberOfVotes(1);
		filmsVotedPerson1.add(filmVote1);
		totalVotesFilm2 += filmVote1.getNumberOfVotes();
		saveVote(filmsVotedPerson1, person1);
	}
	
	private void createFilmVotePerson2() {
		filmVote2 = new FilmVote();
		filmVote2.setFilm(CreateFilmsToTest.getInstance().getFilm());
		filmVote2.setName(person2.getName());
		filmVote2.setEmail("Person2@mail");
		filmVote2.setNumberOfVotes(1);
		filmsVotedPerson2.add(filmVote2);
		totalVotesFilm += filmVote2.getNumberOfVotes();
		
		filmVote2 = new FilmVote();
		filmVote2.setFilm(CreateFilmsToTest.getInstance().getFilm1());
		filmVote2.setName(person2.getName());
		filmVote2.setEmail("Person2@mail");
		filmVote2.setNumberOfVotes(3);
		filmsVotedPerson2.add(filmVote2);
		totalVotesFilm1 += filmVote2.getNumberOfVotes();
		
		filmVote2 = new FilmVote();
		filmVote2.setFilm(CreateFilmsToTest.getInstance().getFilm2());
		filmVote2.setName(person2.getName());
		filmVote2.setEmail("Person2@mail");
		filmVote2.setNumberOfVotes(1);
		filmsVotedPerson2.add(filmVote2);
		totalVotesFilm2 += filmVote2.getNumberOfVotes();
		
		filmVote2 = new FilmVote();
		filmVote2.setFilm(CreateFilmsToTest.getInstance().getFilm3());
		filmVote2.setName(person2.getName());
		filmVote2.setEmail("Person2@mail");
		filmVote2.setNumberOfVotes(2);
		filmsVotedPerson2.add(filmVote2);
		totalVotesFilm3 += filmVote2.getNumberOfVotes();
		
		saveVote(filmsVotedPerson2, person2);
	}
	
	private void saveVote(List<FilmVote> filmsVotes, Person person) {
		 for (FilmVote filmVote: filmsVotes) {
			 FilmPersonVote filmPersonVote = getFilmPersonVote(filmVote.getFilm(), person, filmVote.getNumberOfVotes());
			 filmPersonVote.save();
		 }
	}
	
	private FilmPersonVote getFilmPersonVote(Film film, Person person, int numberOfVotes) {
		FilmPersonVote filmPersonVote = new FilmPersonVote();
		filmPersonVote.setFilm(film);
		filmPersonVote.setPerson(person);
		filmPersonVote.setNumberOfVotes(numberOfVotes);
		return filmPersonVote;
	}
	
	
	private void personSave() {
		person.save();
		person1.save();
		person2.save();
	}
	
//	public Film getFilm() {
//		return film;
//	}
//
//	public Film getFilm1() {
//		return film1;
//	}
//
//	public Film getFilm2() {
//		return film2;
//	}
//
//	public Film getFilm3() {
//		return film3;
//	}

	public Person getPerson() {
		return person;
	}
	
	public Person getPerson1() {
		return person1;
	}
	
	public Person getPerson2() {
		return person2;
	}
	
	public List<FilmVote> getFilmsVotedPerson() {
		return filmsVotedPerson;
	}
	
	public List<FilmVote> getFilmsVotedPerson1() {
		return filmsVotedPerson1;
	}
	
	public List<FilmVote> getFilmsVotedPerson2() {
		return filmsVotedPerson2;
	}
	
	public int getTotalVotesFilm() {
		return totalVotesFilm;
	}
	
	public int getTotalVotesFilm1() {
		return totalVotesFilm1;
	}
	
	public int getTotalVotesFilm2() {
		return totalVotesFilm2;
	}
	
	public int getTotalVotesFilm3() {
		return totalVotesFilm3;
	}
}