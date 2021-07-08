package fun.madeby.springfilms.security;

import fun.madeby.springfilms.models.Role;
import fun.madeby.springfilms.models.User;
import fun.madeby.springfilms.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepository userRepo;

@Override
@Transactional(readOnly = true)
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = new User();
	try {
		user = userRepo.findByUsername(username);
	} catch (UsernameNotFoundException unfe) {
		System.out.println("IN UserDetailsServiceImpl");
		unfe.printStackTrace();
	}
		           // .orElseThrow(()-> new UsernameNotFoundException(username)); this does not work here but strangely works elsewhere in another repo.
	Set<GrantedAuthority> authoritySet = new HashSet<>();
	for(Role role: user.getRoles()) {
		authoritySet.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
	}

	return new org.springframework.security.core.userdetails.User(user.getUsername(),
		user.getPassword(), authoritySet);
}
}
