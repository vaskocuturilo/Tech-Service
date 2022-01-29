package site.testengineer.techInterview.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {

    @Id
    private Long id;

    private String username;

    List<ExercisesEntity> exercises = new ArrayList<>();


}
