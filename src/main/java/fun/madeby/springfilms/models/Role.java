package fun.madeby.springfilms.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//This is a lookUp table, so @ManyToMany not reciprocated here??
@Entity
@Getter
@Setter
public class Role {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
// Sub side
@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
private Set<User> users = new HashSet<>();

public void addUser(User user) {
	this.users.add(user);
	if(!user.getRoles().contains(this)) {
		user.addRole(this);
	}
}
}
