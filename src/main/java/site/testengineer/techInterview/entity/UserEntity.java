package site.testengineer.techInterview.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserEntity", schema = "dbo")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @OneToMany
    @NotFound(action = NotFoundAction.IGNORE)
    List<ExercisesEntity> exercisesList = new ArrayList<>();


    public UserEntity() {
    }

    public UserEntity(Long id, String username, List<ExercisesEntity> exercisesList) {
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

    public List<ExercisesEntity> getExercisesList() {
        return exercisesList;
    }

    public void setExercisesList(List<ExercisesEntity> exercisesList) {
        this.exercisesList = exercisesList;
    }
}
