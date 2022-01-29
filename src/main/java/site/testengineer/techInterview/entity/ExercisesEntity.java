package site.testengineer.techInterview.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExercisesEntity {

    @Id
    private Long id;
    private String title;
    private String description;
}
