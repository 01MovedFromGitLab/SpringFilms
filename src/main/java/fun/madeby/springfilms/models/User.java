package fun.madeby.springfilms.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String username;
private String password;
// Dominant side
@ManyToMany
@JoinTable(name = "User_Role",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
private Set<Role> roles = new HashSet<>();

public void addRole(Role role) {
	this.roles.add(role);
	if(!role.getUsers().contains(this)) {
		role.addUser(this);
	}
}
}
