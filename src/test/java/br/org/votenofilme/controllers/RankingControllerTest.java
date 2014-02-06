package br.org.votenofilme.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.org.votenofilme.classestotests.CreateFilmsToTest;
import br.org.votenofilme.classestotests.CreateVotesToTest;
import br.org.votenofilme.models.Film;
import br.org.votenofilme.util.Ranking;

public class RankingControllerTest {

	private MockSerializationResult resultSerialization = null;
	private RankingController rankingController = null;
	private static CreateVotesToTest createVotesTest = null;
	private static CreateFilmsToTest createFilmsToTest = null;

	@Before
	public void setUp() throws Exception {
		resultSerialization = new MockSerializationResult();
		rankingController = new RankingController(resultSerialization);
		createVotesTest = CreateVotesToTest.getInstance();
		createFilmsToTest = CreateFilmsToTest.getInstance();
	}

	@Test
	public void createGeneralRankingTest() {
		rankingController.createGeneralRanking();
		Set<Ranking> generalRanking = resultSerialization.included("generalRanking");
		List<Ranking> rankings = new ArrayList<Ranking>(generalRanking); 
		int indexFilm = getIndexRankbyFilm(rankings, CreateFilmsToTest.getInstance().getFilm()); 
		int indexFilm1 = getIndexRankbyFilm(rankings, CreateFilmsToTest.getInstance().getFilm1());
		int indexFilm2 = getIndexRankbyFilm(rankings, CreateFilmsToTest.getInstance().getFilm2());
		int indexFilm3 = getIndexRankbyFilm(rankings, CreateFilmsToTest.getInstance().getFilm3());
		
		Assert.assertFalse(generalRanking.isEmpty());
		Assert.assertTrue(generalRanking.contains(new Ranking(CreateFilmsToTest.getInstance().getFilm().getName())));
		Assert.assertTrue(generalRanking.contains(new Ranking(CreateFilmsToTest.getInstance().getFilm1().getName())));
		Assert.assertEquals(createVotesTest.getTotalVotesFilm(), rankings.get(indexFilm).getNumberOfVotes());
		Assert.assertEquals(createVotesTest.getTotalVotesFilm1(), rankings.get(indexFilm1).getNumberOfVotes());
		Assert.assertEquals(createVotesTest.getTotalVotesFilm2(), rankings.get(indexFilm2).getNumberOfVotes());
		Assert.assertEquals(createVotesTest.getTotalVotesFilm3(), rankings.get(indexFilm3).getNumberOfVotes());
	}
	
	@Test
	public void createPersonRankingTest() {
		rankingController.createPersonRanking(createVotesTest.getPerson().getId());
		Set<Ranking> personRanking = resultSerialization.included("personRanking");
		Assert.assertFalse(personRanking.isEmpty());
		Assert.assertFalse(personRanking.contains(null));
		Assert.assertTrue(personRanking.contains(new Ranking(createFilmsToTest.getFilm().getName())));
		Assert.assertTrue(personRanking.contains(new Ranking(createFilmsToTest.getFilm1().getName())));
	}
	
	private int getIndexRankbyFilm(List<Ranking> rankings, Film film) {
		return rankings.indexOf(new Ranking(film.getName()));
	}
}