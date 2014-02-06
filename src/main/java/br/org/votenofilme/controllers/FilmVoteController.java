package br.org.votenofilme.controllers;

import java.util.List;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.org.votenofilme.models.Film;
import br.org.votenofilme.models.FilmPersonVote;
import br.org.votenofilme.models.Person;
import br.org.votenofilme.util.FilmVote;

@Resource
public class FilmVoteController {

	private final Result result;
	
	public FilmVoteController(Result result) {
		this.result = result;
	}
	
	@Post
	@Consumes("application/json")
	@Path(value="/savefilmvote/")
	public void saveVote(final FilmVote[] filmsVoted) {
		 FilmPersonVote filmPersonVote = null;
		 Person person = null;
		 
		 if (verificarAlreadyExistPerson(filmsVoted[0].getName())) {
			 this.result.use(Results.http()).setStatusCode(406); 
			 this.result.use(Results.json()).from("Nome já existe, informe outro.", "error").serialize();  
			 return;
		 }
		 person = new Person(filmsVoted[0].getName(), filmsVoted[0].getEmail());
		 person.save();
		 for (FilmVote filmVote: filmsVoted) {
			 filmPersonVote = getFilmPersonVote(filmVote.getFilm(), person, filmVote.getNumberOfVotes());
			 filmPersonVote.save();
		 }
		 this.result.include("person", person);
		 this.result.use(Results.json()).withoutRoot().from(person).serialize(); 
	}

	private boolean verificarAlreadyExistPerson(String name) {
		List<Person> persons = (List<Person>) Person.list();
		if (persons == null) return false;
		for (Person person: persons) {
			if (name.equals(person.getName())) {
				return true;
			}
		}
		return false;
	}

	private FilmPersonVote getFilmPersonVote(Film film, Person person, int numberOfVotes) {
		FilmPersonVote filmPersonVote = new FilmPersonVote();
		filmPersonVote.setFilm(film);
		filmPersonVote.setPerson(person);
		filmPersonVote.setNumberOfVotes(numberOfVotes);
		return filmPersonVote;
	}
}