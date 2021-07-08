package fun.madeby.springfilms.services;

import fun.madeby.springfilms.models.Role;
import fun.madeby.springfilms.models.User;
import fun.madeby.springfilms.repositories.RoleRepository;
import fun.madeby.springfilms.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
private final UserRepository userRepo;
private final RoleRepository roleRepo;
private final PasswordEncoder pwdEncoder;

@Override
public void register(User user) {
	String encodedPassword = pwdEncoder.encode(user.getPassword());
	user.setPassword(encodedPassword);
	//user.addRole(roleRepo.findByName("USER")); // Be sure to add role prior to sending
	userRepo.save(user);
}

@Override
public User retrieveByUsername(String username) {
	User user = new User();
	try {
		user = userRepo.findByUsername(username);
	} catch(EntityNotFoundException enfe) {
		enfe.printStackTrace();
	}
	if(user.getUsername().isBlank()) {
		System.out.println("Retrieve by username USERNAME BLANK");
	}
	return user;
}

}
