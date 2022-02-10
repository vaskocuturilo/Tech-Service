package site.testengineer.techInterview.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", schema = "dbo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<Exercises> exercisesList;


    public User() {
    }

    public User(Long id, String username, Set<Exercises> exercisesList) {
        this.id = id;
        this.username = username;
        this.exercisesList = exercisesList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Exercises> getExercisesList() {
        return exercisesList;
    }

    public void setExercisesList(Set<Exercises> exercisesList) {
        this.exercisesList = exercisesList;
    }
}
