package fun.madeby.springfilms;

import fun.madeby.springfilms.models.Actor;
import fun.madeby.springfilms.models.Film;
import fun.madeby.springfilms.models.Role;
import fun.madeby.springfilms.models.User;
import fun.madeby.springfilms.repositories.RoleRepository;
import fun.madeby.springfilms.services.ActorService;
import fun.madeby.springfilms.services.FilmService;
import fun.madeby.springfilms.services.UserService;
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
//@Profile("dev") // Look into this and have dev/prod
@RequiredArgsConstructor
public class Bootstrap {
	private final FilmService filmService;
	private final ActorService actorService;
	private final UserService userService;
	private final RoleRepository roleRepo;

// // to turn off @PostConstruct
//@PostConstruct
	public void init(){
		// Make Actors
		List<Actor> oldActors = new ArrayList<>(Arrays.asList(
			new Actor("Choi Min-sik", "https://www.dropbox.com/s/kmisfdcic103usb/220px-Choi_Min-sik.jpg?raw=1"),
			new Actor("Kang Hye-jung",  "https://www.dropbox.com/s/iju6rh1ad11zk9i/Kang_Hye-jung_%282012%2C_cropped%29.jpg?raw=1")
		));
		List<Actor> aTasteActors = new ArrayList<>(Arrays.asList(
			new Actor("Tomokazu Miura", "https://www.dropbox.com/s/tychmxlwv8bz84a/Tomokazu_Miura.jpg?raw=1"),
			new Actor("Tadanobu Asano",  "https://www.dropbox.com/s/29zajzbewjf7ice/220px-Tadanobu_Asano_2011_%28cropped%29.jpg?raw=1")
		));
		actorService.register(aTasteActors);
		actorService.register(oldActors);
		// Make Films
		Film film01 = new Film();
		film01.setTitle("A Taste of Tea");
		film01.setReleaseDate(LocalDate.of(2004, Month.JULY, 17));
		film01.setFilmImageUrl("https://www.dropbox.com/s/gi3m3bega2qm069/ATasteOfTea.jpg?raw=1");
		film01.addActors(aTasteActors);
		filmService.register(film01);
		Film film02 = new Film();
		film02.setTitle("Oldboy");
		film02.setReleaseDate(LocalDate.of(2003, Month.NOVEMBER, 21));
		film02.addActors(oldActors);
		film02.setFilmImageUrl("https://www.dropbox.com/s/71nhfcvahmbwa0e/Oldboy.jpg?raw=1");
		filmService.register(film02);

		Role userRole = new Role();
		userRole.setName("USER");
		roleRepo.save(userRole);
		Role adminRole = new Role();
		adminRole.setName("ADMIN");
		roleRepo.save(adminRole);
		User gram = new User();
		gram.setUsername("gram");
		gram.setPassword("asdf1234%");
		userService.register(gram);

	}


}
