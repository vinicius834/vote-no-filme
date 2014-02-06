package br.org.votenofilme.controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FilmControllerTest.class, FilmVoteControllerTest.class,
		RankingControllerTest.class })
public class AllTestsControllers {
}