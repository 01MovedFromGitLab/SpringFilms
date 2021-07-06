package fun.madeby.springfilms.services;

import fun.madeby.springfilms.models.Film;

import java.util.List;

public interface FilmService {
	void register(Film film);
	Film retrieveById(Long id);
	List<Film> retrieveAll();
}
