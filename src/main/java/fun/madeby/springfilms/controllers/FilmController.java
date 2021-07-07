package fun.madeby.springfilms.controllers;

import fun.madeby.springfilms.models.Film;
import fun.madeby.springfilms.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilmController {
	private final FilmService filmService;

// Route Only
@GetMapping(value="/films/new")
public String newFilmForm() {
	return "newFilmForm";
}

// POST how you'd expect to do it.
@PostMapping(value="/films/new")
public String postFilmRequested(Model model,
                                @RequestParam String title,
                                @RequestParam("releaseYear") String releaseYear,
                                @RequestParam("filmImageUrl") String filmImageUrl) {
	Film newFilm = new Film();
	newFilm.setTitle(title);
	int year = Integer.parseInt(releaseYear);
	newFilm.setReleaseDate(LocalDate.of(year, Month.JANUARY, 1));
	newFilm.setFilmImageUrl(filmImageUrl);
	filmService.register(newFilm);
	return "newFilmForm";
}

// Route & GET
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
	model.addAttribute("wigwam", film);

	return "filmDetail";
}

@GetMapping(value = "/films", params = "title")
public String searchFilmRequested(Model model,
                                  @RequestParam(name = "title") String titleSearchTerm){

	List<Film>	searchTermFilms = filmService.searchFilms(titleSearchTerm);
	model.addAttribute("kumquat", searchTermFilms);
	return "films";
}



}
