package site.testengineer.techInterview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import site.testengineer.techInterview.entity.ExercisesEntity;
import site.testengineer.techInterview.entity.UserEntity;
import site.testengineer.techInterview.repository.ExercisesRepository;
import site.testengineer.techInterview.repository.UserRepository;

@SpringBootApplication
public class TechInterviewApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExercisesRepository exercisesRepository;

    public static void main(String[] args) {
        SpringApplication.run(TechInterviewApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("Test");


        ExercisesEntity exercisesEntity = new ExercisesEntity();
        exercisesEntity.setId(1L);
        exercisesEntity.setDescription("This is Test exercises");
        exercisesEntity.setTitle("This is test title");


        userEntity.getExercisesList().add(exercisesEntity);

        userRepository.save(userEntity);
        exercisesRepository.save(exercisesEntity);


    }
}
