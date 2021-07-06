package fun.madeby.springfilms.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor // creates no arg constructor cos you've added name param constructor
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Name", columnDefinition = "VARCHAR(100) NOT NULL")
	private String name;
	@ManyToMany(mappedBy = "cast", fetch = FetchType.EAGER)
	private Set<Film> films = new HashSet<>();

	public Actor(String name) {
		this.name = name;
	}

	public void addFilm(Film film) {
		films.add(film);
		if(!film.getCast().contains(this))
			film.addActor(this);
	}





}
