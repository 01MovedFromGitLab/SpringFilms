package fun.madeby.springfilms.services;

import fun.madeby.springfilms.models.Actor;
import fun.madeby.springfilms.repositories.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService{
	private final ActorRepository actorRepo;

@Override
public void register(Actor actor) {
	actorRepo.save(actor);
}

@Override
public void register(List<Actor> actors) {
	actorRepo.saveAll(actors);
}

@Override
public List<Actor> retrieveAll() {
	return actorRepo.findAll();
}

@Override
public List<Actor> searchByName(String searchTerm) {
	return actorRepo.findActorsByNameIsLike("%" + searchTerm + "%");
}
}
