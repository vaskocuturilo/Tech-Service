package site.testengineer.techInterview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import site.testengineer.techInterview.repository.ExercisesRepository;
import site.testengineer.techInterview.repository.UserRepository;

@SpringBootApplication
public class TechInterviewApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExercisesRepository exercisesRepository;

    public static void main(String[] args) {

        SpringApplication.run(TechInterviewApplication.class, args);
    }
}
