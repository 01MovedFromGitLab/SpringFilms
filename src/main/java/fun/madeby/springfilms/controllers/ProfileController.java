package fun.madeby.springfilms.controllers;

import fun.madeby.springfilms.models.User;
import fun.madeby.springfilms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProfileController {
private final UserService userService;

//@GetMapping("myprofile")
//public String myProfileView(Model model, Principal principal) {
//	User loggedInUser = userService.retrieveByUsername(principal.getName());
//	model.addAttribute("user", loggedInUser);
//	return "myProfile"; // shows user/myProfile in text which does not exist.
//}

@GetMapping("my-profile")
public String myProfileView(Model model){
	SecurityContext secContext = SecurityContextHolder.getContext();
	Authentication auth = secContext.getAuthentication();
	String username = auth.getName();
	User loggedInUser = userService.retrieveByUsername(username);
	model.addAttribute("user", loggedInUser);
	return "myProfile";
}

@GetMapping("my-profile/edit")
public String myProfileEditView(Model model, Principal principal) {
	User userProfile = userService.retrieveByUsername(principal.getName());
	model.addAttribute("profile", userProfile );
	return "myProfileEdit";
}

@PostMapping("my-profile/edit")
public String postEditedProfile(User profile, BindingResult br) {
	if(br.hasErrors())
		return "my-profile/edit";
	userService.register(profile);
	return "redirect:/my-profile?updated";
}

}
