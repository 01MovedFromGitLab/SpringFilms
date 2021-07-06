package fun.madeby.springfilms.controllers;

import fun.madeby.springfilms.models.Actor;
import fun.madeby.springfilms.services.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ActorController {
	private final ActorService actorService;

	@GetMapping("/actors")
	public String allActorsRequested(Model model){
		List<Actor> allActors = actorService.retrieveAll();

		model.addAttribute("gaggleOfWoke", allActors); // a few exceptions.

		return "actors";

	}
}
