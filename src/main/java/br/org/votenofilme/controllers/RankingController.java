package br.org.votenofilme.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.org.votenofilme.models.Film;
import br.org.votenofilme.models.FilmPersonVote;
import br.org.votenofilme.models.Ranking;

@Resource
public class RankingController {
	private Result result;

	public RankingController(Result result) {
		this.result = result;
	}
	
	@Path("/ranking")
	public void ranking() {
		result.include("Sucesso");
	}
	
	@Get
	@Path(value="ranking/ranking-geral")
	public void createGeneralRanking() {
		List<FilmPersonVote> filmPersonVotes = (List<FilmPersonVote>) FilmPersonVote.list();
		Set<Ranking> generalRanking = createRanking(filmPersonVotes);
		this.result.include("generalRanking", generalRanking);
		this.result.use(Results.json()).from(generalRanking.toArray(new Ranking[generalRanking.size()])).serialize();
	}
	
	@Get
	@Path(value="ranking/ranking-person/{personId}")
	public void createPersonRanking(final long personId) {
		List<FilmPersonVote> filmPersonVotes = (List<FilmPersonVote>) FilmPersonVote.listByPerson(personId);
		Set<Ranking> personRanking = createRanking(filmPersonVotes);
		this.result.include("personRanking", personRanking);
		this.result.use(Results.json()).from(personRanking.toArray(new Ranking[personRanking.size()])).serialize();
	}
	
	private Set<Ranking> createRanking(final List<FilmPersonVote> filmPersonVotes) {
		Set<Ranking> rankings = new HashSet<Ranking>();
		List<Film> films = (List<Film>) Film.list();
		Ranking filmRanking;
		int totalVotes = 0;
		for (Film film: films) {
			filmRanking = new Ranking(film.getName(), 0);
			for (FilmPersonVote filmPersonVote: filmPersonVotes) {
				if (film.getId() == filmPersonVote.getFilm().getId()) {
					totalVotes += filmPersonVote.getNumberOfVotes();
					filmRanking.setNumberOfVotes(totalVotes);
				}
			}
			totalVotes = 0;
			rankings.add(filmRanking);
		}
		return rankings;
	}
}