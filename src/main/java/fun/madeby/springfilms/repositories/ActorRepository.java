package fun.madeby.springfilms.repositories;

import fun.madeby.springfilms.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
	List<Actor> findActorsByNameIsLike(String searchTerm);
}
