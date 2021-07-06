package fun.madeby.springfilms.services;

import fun.madeby.springfilms.models.Actor;

import java.util.List;

public interface ActorService {
	void register(Actor actor);
	// register entire cast at same time:
	void register(List<Actor> actors);
	List<Actor> retrieveAll();
	List<Actor> searchByName(String searchTerm);

}
