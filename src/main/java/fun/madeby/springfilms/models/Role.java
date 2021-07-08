package fun.madeby.springfilms.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//This is a lookUp table, so @ManyToMany not reciprocated here??
@Entity
@Getter
@Setter
public class Role {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
}
