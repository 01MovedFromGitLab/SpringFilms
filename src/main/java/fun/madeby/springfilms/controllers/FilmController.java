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

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilmController {
	private final FilmService filmService;

// Route Only
@GetMapping(value="films/new")
public String newFilmForm(Model model) {
	// give view a Film model to map against
	model.addAttribute("sorgum", new Film());
	return "newOrEditFilmForm";
}


// See earlier versions below
@PostMapping(value="films/new")
public String postFilmRequest(Film film) {
	filmService.register(film);
	return "newOrEditFilmForm";
}

// Edit button from film detail
@GetMapping("films/{id}/edit")
public String getEditFilmModel(Model model, @PathVariable Long id) {
	model.addAttribute("sorgum",(filmService.retrieveById(id)));
	return "newOrEditFilmForm";
}

// Route & GET
@GetMapping("films")
public String allFilmsRequested(Model model) {
	List<Film> allMyFilms = filmService.retrieveAll();
	// model is required in order to share with views.
	model.addAttribute("kumquat", allMyFilms); // the model iterable will be named kumquat and will contain allMyFilms

	return "films";
}

@GetMapping("films/{id}")
public String specificFilmRequested(Model model, @PathVariable Long id) {
	Film film = filmService.retrieveById(id);
	model.addAttribute("wigwam", film);

	return "filmDetail";
}

@GetMapping(value = "films", params = "title")
public String searchFilmRequested(Model model,
                                  @RequestParam(name = "title") String titleSearchTerm){

	List<Film>	searchTermFilms = filmService.searchFilms(titleSearchTerm);
	model.addAttribute("kumquat", searchTermFilms);
	return "films";
}



}

//Earlier post versions for reference
// POST how you'd expect to do it.
/*@PostMapping(value="/films/new")
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
}*/

/*// POST how Spring lets you do it but release Year fails so have to handle yourself:
@PostMapping(value="/films/new")
public String postFilmRequest(Film film,
                              @RequestParam("releaseYear") String releaseYear) {
	int year = Integer.parseInt(releaseYear);
	film.setReleaseDate(LocalDate.of(year, Month.JANUARY, 1));
	filmService.register(film);
	return "newFilmForm";
}*/
