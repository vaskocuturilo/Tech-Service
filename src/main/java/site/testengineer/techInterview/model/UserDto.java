package site.testengineer.techInterview.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private Set<Exercise> exercises = new HashSet<>();
}
