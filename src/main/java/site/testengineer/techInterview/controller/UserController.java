package site.testengineer.techInterview.controller;

import org.springframework.web.bind.annotation.*;
import site.testengineer.techInterview.entity.ExercisesEntity;
import site.testengineer.techInterview.entity.UserEntity;
import site.testengineer.techInterview.repository.ExercisesRepository;
import site.testengineer.techInterview.repository.UserRepository;
import site.testengineer.techInterview.request.UserRequest;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private ExercisesRepository exercisesRepository;

    public UserController(UserRepository userRepository, ExercisesRepository exercisesRepository) {
        this.userRepository = userRepository;
        this.exercisesRepository = exercisesRepository;
    }

    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    }

    @PostMapping
    public UserEntity addUser(@RequestBody UserRequest userRequest) {
     UserEntity userEntity = new UserEntity();
     userEntity.setUsername(userRequest.getUsername());
        return userRepository.save(userEntity);
    }

    @PostMapping("/{userId}/exercise")
    public void addExercises(@PathVariable Long userId, @RequestBody ExercisesEntity exercisesEntity) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        userEntity.getExercisesList().add(exercisesEntity);
    }

    @PostMapping("/exercises/{exerciseId}")
    public void toggleExercisesComplete(@PathVariable Long exercisesId) {
        ExercisesEntity exercisesEntity = exercisesRepository.findById(exercisesId).orElseThrow(() -> new NoSuchElementException());
        exercisesEntity.setCompleted(!exercisesEntity.getCompleted());
        exercisesRepository.save(exercisesEntity);
    }

    @DeleteMapping("exercises/{exercisesId}")
    public void deleteExercises(@PathVariable Long exercisesId) {
        ExercisesEntity exercisesEntity = exercisesRepository.findById(exercisesId).orElseThrow(() -> new NoSuchElementException());
        exercisesRepository.delete(exercisesEntity);
    }
}
