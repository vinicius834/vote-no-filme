package br.org.votenofilme.controllers;

import java.util.List;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.org.votenofilme.models.Film;

@Resource
public class FilmController {
	
	private final Result result;
	
	public FilmController(Result result) {
		this.result = result;
	}

	@Get
	@Path("/getfilms")
	public void list() {
		List<Film> films = (List<Film>) Film.list();
		this.result.include("films", films);
		this.result.use(Results.json()).from(films, "films").serialize();
	}
}