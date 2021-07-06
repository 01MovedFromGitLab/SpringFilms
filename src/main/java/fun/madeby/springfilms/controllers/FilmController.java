package fun.madeby.springfilms.controllers;

import fun.madeby.springfilms.models.Film;
import fun.madeby.springfilms.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilmController {
	private final FilmService filmService;



@GetMapping("/films")
public String allFilmsRequested(Model model) {
	List<Film> allMyFilms = filmService.retrieveAll();
	// model is required in order to share with views.
	model.addAttribute("kumquat", allMyFilms); // the model iterable will be named kumquat and will contain allMyFilms

	return "films";
}

@GetMapping("/films/{id}")
public String specificFilmRequested(Model model, @PathVariable Long id) {
	Film film = filmService.retrieveById(id);
	System.out.println(film.getCast());
	model.addAttribute("wigwam", film);

	return "filmDetail";
}

}
