package fun.madeby.springfilms;

import fun.madeby.springfilms.models.Actor;
import fun.madeby.springfilms.models.Film;
import fun.madeby.springfilms.services.ActorService;
import fun.madeby.springfilms.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
//@Profile("dev")
@RequiredArgsConstructor // whatever required constructed by lombok
public class Bootstrap {
	private final FilmService filmService;
	private final ActorService actorService;


@PostConstruct // used on methods that need to executed AFTER dependency injection used for any initialization.
	public void init(){
		// Make Actors
		List<Actor> actors = new ArrayList<>(Arrays.asList(
			new Actor("Robert DeNiro"),
			new Actor("Al Pacino")
		));
		actorService.register(actors);
		// Make Films
		Film myFilm = new Film();
		myFilm.setTitle("The Lord of the Rings: The Return of the King");
		myFilm.setReleaseDate(LocalDate.of(2003, Month.NOVEMBER, 1));
		filmService.register(myFilm);
		Film myOtherFilm = new Film();
		myOtherFilm.setTitle("Heat");
		myOtherFilm.setReleaseDate(LocalDate.of(1996, Month.FEBRUARY, 14));
		myOtherFilm.addActors(actors);
		filmService.register(myOtherFilm);


	}


}
