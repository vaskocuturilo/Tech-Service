package site.testengineer.techInterview.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Exercise> exercises = new HashSet<>();

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;

        for (Exercise e : exercises) {
            e.setUser(this);
        }
    }
}
