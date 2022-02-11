package site.testengineer.techInterview.controller;

import org.springframework.web.bind.annotation.*;
import site.testengineer.techInterview.entity.Exercise;
import site.testengineer.techInterview.entity.User;
import site.testengineer.techInterview.exception.CanSaveExercise;
import site.testengineer.techInterview.exception.ExercisesNotFound;
import site.testengineer.techInterview.exception.UserNotFound;
import site.testengineer.techInterview.repository.ExercisesRepository;
import site.testengineer.techInterview.repository.UserRepository;
import site.testengineer.techInterview.request.ExercisesRequest;
import site.testengineer.techInterview.request.UserRequest;

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
    public User getUserById(@PathVariable Long userId) throws UserNotFound {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFound(USER_NOT_FOUND_MSG));
    }

    @PostMapping
    public User addUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setFirstname(userRequest.getUsername());
        return userRepository.save(user);
    }

    @PostMapping("/{userId}/exercise")
    public void addExercises(@PathVariable Long userId, @RequestBody ExercisesRequest exercisesRequest) throws CanSaveExercise {
        User user = userRepository.findById(userId).orElseThrow(() -> new CanSaveExercise(EXERCISE_NO_SAVE_MSG));

        Exercise exercise = new Exercise();


        exercisesRepository.save(exercise);
        userRepository.save(user);
    }

    @PostMapping("/exercises/{exerciseId}")
    public void toggleExercisesComplete(@PathVariable Long exercisesId) throws ExercisesNotFound {
        Exercise exercise = exercisesRepository.findById(exercisesId).orElseThrow(() -> new ExercisesNotFound(EXERCISE_NOT_FOUND));

        exercisesRepository.save(exercise);
    }

    @DeleteMapping("exercises/{exercisesId}")
    public void deleteExercises(@PathVariable Long exercisesId) throws ExercisesNotFound {
        Exercise exercise = exercisesRepository.findById(exercisesId).orElseThrow(() -> new ExercisesNotFound(EXERCISE_NOT_FOUND));
        exercisesRepository.delete(exercise);
    }
}
