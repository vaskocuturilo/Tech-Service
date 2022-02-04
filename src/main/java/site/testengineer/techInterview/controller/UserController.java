package site.testengineer.techInterview.controller;

import org.springframework.web.bind.annotation.*;
import site.testengineer.techInterview.entity.ExercisesEntity;
import site.testengineer.techInterview.entity.UserEntity;
import site.testengineer.techInterview.exception.CanSaveExercise;
import site.testengineer.techInterview.exception.ExercisesNotFound;
import site.testengineer.techInterview.exception.UserNotFound;
import site.testengineer.techInterview.repository.ExercisesRepository;
import site.testengineer.techInterview.repository.UserRepository;
import site.testengineer.techInterview.request.ExercisesRequest;
import site.testengineer.techInterview.request.UserRequest;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final String USER_NOT_FOUND_MSG = "User not Found";
    private static final String EXERCISE_NO_SAVE_MSG = "Can't save exercise";
    private static final String EXERCISE_NOT_FOUND = "Exercise not found";


    private UserRepository userRepository;
    private ExercisesRepository exercisesRepository;

    public UserController(UserRepository userRepository, ExercisesRepository exercisesRepository) {
        this.userRepository = userRepository;
        this.exercisesRepository = exercisesRepository;
    }

    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable Long userId) throws UserNotFound {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFound(USER_NOT_FOUND_MSG));
    }

    @PostMapping
    public UserEntity addUser(@RequestBody UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequest.getUsername());
        return userRepository.save(userEntity);
    }

    @PostMapping("/{userId}/exercise")
    public void addExercises(@PathVariable Long userId, @RequestBody ExercisesRequest exercisesRequest) throws CanSaveExercise {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new CanSaveExercise(EXERCISE_NO_SAVE_MSG));

        ExercisesEntity exercisesEntity = new ExercisesEntity();
        exercisesEntity.setTitle(exercisesRequest.getTitle());
        exercisesEntity.setDescription(exercisesRequest.getDescription());
        exercisesEntity.setCompleted(exercisesRequest.getCompleted());
        userEntity.getExercisesList().add(exercisesEntity);

        exercisesRepository.save(exercisesEntity);
        userRepository.save(userEntity);
    }

    @PostMapping("/exercises/{exerciseId}")
    public void toggleExercisesComplete(@PathVariable Long exercisesId) throws ExercisesNotFound {
        ExercisesEntity exercisesEntity = exercisesRepository.findById(exercisesId).orElseThrow(() -> new ExercisesNotFound(EXERCISE_NOT_FOUND));
        exercisesEntity.setCompleted(!exercisesEntity.getCompleted());
        exercisesRepository.save(exercisesEntity);
    }

    @DeleteMapping("exercises/{exercisesId}")
    public void deleteExercises(@PathVariable Long exercisesId) throws ExercisesNotFound {
        ExercisesEntity exercisesEntity = exercisesRepository.findById(exercisesId).orElseThrow(() -> new ExercisesNotFound(EXERCISE_NOT_FOUND));
        exercisesRepository.delete(exercisesEntity);
    }
}
