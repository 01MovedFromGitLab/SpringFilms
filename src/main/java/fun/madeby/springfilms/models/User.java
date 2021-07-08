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
// I find this strange, no join columns just @ManyToMany on a HashSet, first time I've seen.
@ManyToMany
private Set<Role> roles = new HashSet<>();

public void addRole(Role role) {
	this.roles.add(role);
}
}
