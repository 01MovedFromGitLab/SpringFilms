package fun.madeby.springfilms.services;

import fun.madeby.springfilms.models.Role;
import fun.madeby.springfilms.models.User;

public interface UserService {
	void register(User user);
	User retrieveByUsername(String username);
}
