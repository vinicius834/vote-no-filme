package br.org.votenofilme.models;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import br.org.votenofilme.classestotests.CreateFilmsToTest;

public class FilmPersonVoteTest {

	private Person person;
	private Person person1;
	private Person person2;
	private static CreateFilmsToTest createFilmsToTest = null;
	private FilmPersonVote filmPersonVote;
	private FilmPersonVote filmPersonVote1;
	private FilmPersonVote filmPersonVote2;
	
	@Before
	public void setUp() throws Exception {
		createFilmsToTest = CreateFilmsToTest.getInstance();
		person = new Person("Vinicius Souza", "");
		person1 = new Person("Rinaldo", "");
		person2 = new Person("Karina", "");
		personSave();
		filmPersonVote = new FilmPersonVote();
		filmPersonVote.setFilm(createFilmsToTest.getFilm());
		filmPersonVote.setPerson(person);
		filmPersonVote.setNumberOfVotes(2);
		
		filmPersonVote1 = new FilmPersonVote();
		filmPersonVote1.setFilm(createFilmsToTest.getFilm());
		filmPersonVote1.setPerson(person1);
		filmPersonVote1.setNumberOfVotes(3);
		
		filmPersonVote2 = new FilmPersonVote();
		filmPersonVote2.setFilm(createFilmsToTest.getFilm1());
		filmPersonVote2.setPerson(person);
		filmPersonVote2.setNumberOfVotes(1);
		
	}
	
	@Test
	public void saveTest() {
		filmPersonVote.save();
		filmPersonVote1.save();
		filmPersonVote2.save();
		List<FilmPersonVote> filmPersonVotes = (List<FilmPersonVote>) FilmPersonVote.list();
		Assert.assertFalse(filmPersonVotes.isEmpty());
	}
	
	private void personSave() {
		person.save();
		person1.save();
		person2.save();
	}
}