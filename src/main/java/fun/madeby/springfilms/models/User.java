package fun.madeby.springfilms.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties("roles")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@NotBlank
@Column(name = "UserName", columnDefinition = "VARCHAR(50) UNIQUE NOT NULL")
private String username;
@NotBlank
//@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
@Column(name = "Password", columnDefinition = "VARCHAR(500) NOT NULL")
private String password;
@Column(name="AvatarUrl", columnDefinition ="VARCHAR(250)")
private String avatarUrl;
@Column(name="FullName", columnDefinition ="VARCHAR(250)")
private String fullName;
@Column(name="Country", columnDefinition ="VARCHAR(100)")
private String country;
@Column(name="MovieQuote", columnDefinition = "VARCHAR(500)")
private String movieQuote;
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
