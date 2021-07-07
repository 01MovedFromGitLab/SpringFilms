package fun.madeby.springfilms.repositories;

import fun.madeby.springfilms.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
List<Film> findFilmByTitleIsLike(String titleSearchTerm);
}
