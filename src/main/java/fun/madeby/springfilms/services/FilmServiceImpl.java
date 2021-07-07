package fun.madeby.springfilms.services;

import fun.madeby.springfilms.models.Actor;
import fun.madeby.springfilms.models.Film;
import fun.madeby.springfilms.repositories.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService{
	private final FilmRepository filmRepo;


@Override
public void register(Film film) {
	filmRepo.save(film);
}

@Override
public Film retrieveById(Long id) {
	return filmRepo.findById(id).orElseThrow(RuntimeException::new);
}

@Override
public List<Film> retrieveAll() {
	return filmRepo.findAll();
}

@Override
public List<Film> searchFilms(String titleSearchTerm) {
	return filmRepo.findFilmByTitleIsLike("%" + titleSearchTerm + "%");
}
}
