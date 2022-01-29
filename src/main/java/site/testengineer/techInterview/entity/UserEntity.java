package site.testengineer.techInterview.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String username;

    @OneToMany(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    List<ExercisesEntity> exercises = new ArrayList<>();


    public UserEntity() {
    }

    public UserEntity(Long id, String username, List<ExercisesEntity> exercises) {
        this.id = id;
        this.username = username;
        this.exercises = exercises;
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

    public List<ExercisesEntity> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExercisesEntity> exercises) {
        this.exercises = exercises;
    }
}
