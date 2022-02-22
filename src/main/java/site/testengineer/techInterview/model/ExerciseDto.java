package site.testengineer.techInterview.model;

import lombok.Data;

@Data
public class ExerciseDto {

    private Long id;
    private String exerciseName;
    User user;
}
