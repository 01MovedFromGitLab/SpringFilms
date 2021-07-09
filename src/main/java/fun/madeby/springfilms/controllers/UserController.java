package fun.madeby.springfilms.controllers;

import fun.madeby.springfilms.models.User;
import fun.madeby.springfilms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping("login")
	public String login() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken)
			return "login";
		return "redirect:/";
	}

	@GetMapping("register")
	public String registerForm(Model model) {
		//The below did not work
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) // eventually the button wont even be shown and this will be redundant
			return "home";*/
		model.addAttribute("inkBlot", new User());
		return "register";
	}

	@PostMapping("register")
	public String registerPost(User inkBlot, BindingResult bindingResult) {

		if(bindingResult.hasErrors())
			return"register";

		userService.register(inkBlot);
		return "redirect:/login";
	}





}
