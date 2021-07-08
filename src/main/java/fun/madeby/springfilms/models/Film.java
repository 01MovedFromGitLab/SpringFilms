package fun.madeby.springfilms.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(name = "Title", columnDefinition = "VARCHAR(100) NOT NULL")
	private String title;
	@NotBlank
	@Column(name = "ReleaseDate", columnDefinition = "DATE NOT NULL")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate releaseDate;
	// Dominant side
	@ManyToMany
	@JoinTable(name = "Film_Actor",
					joinColumns = @JoinColumn(name = "film_id"),
					inverseJoinColumns = @JoinColumn(name = "actor_id"))
	private Set<Actor> cast = new HashSet<>();
	@NotBlank
	@Column(name="FilmImageUrl", columnDefinition = "VARCHAR(250)")
	private String filmImageUrl;

	// Reciprocal method ensures both sides of ManyToMany are up to date.
	public void addActor(Actor actor) {
		this.cast.add(actor);
		if(!actor.getFilms().contains(this)) {
			actor.addFilm(this);
		}
	}
	public void addActors(List<Actor> actorList) {
		for(Actor a: actorList) {
			this.cast.add(a);
		if(!a.getFilms().contains(this))	{
			a.addFilm(this);
		}
		}
	}

@Override
public String toString() {
	return "Film{" +
		       "id=" + id +
		       ", title='" + title + '\'' +
		       ", releaseDate=" + releaseDate +
		       ", cast=" + cast.stream().map(a -> a.getName()).
		       collect(Collectors.toList())+
		       '}';
}
}
