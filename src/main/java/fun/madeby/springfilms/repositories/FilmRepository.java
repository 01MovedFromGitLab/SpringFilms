package fun.madeby.springfilms.repositories;

import fun.madeby.springfilms.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
