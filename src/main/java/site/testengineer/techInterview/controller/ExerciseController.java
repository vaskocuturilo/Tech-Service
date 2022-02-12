package site.testengineer.techInterview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import site.testengineer.techInterview.entity.Exercise;
import site.testengineer.techInterview.entity.User;
import site.testengineer.techInterview.repository.ExerciseRepository;
import site.testengineer.techInterview.repository.UserRepository;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/exercise")
public class ExerciseController {

    UserRepository userRepository;
    ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseController(UserRepository userRepository, ExerciseRepository exerciseRepository) {
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @PostMapping
    public ResponseEntity<Exercise> create(@RequestBody Exercise exercise) {
        Optional<User> optionalUser = userRepository.findById(exercise.getUser().getId());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        exercise.setUser(optionalUser.get());

        Exercise saveExercise = exerciseRepository.save(exercise);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveExercise.getId()).toUri();

        return ResponseEntity.created(location).body(saveExercise);
    }

    @GetMapping
    public ResponseEntity<Page<Exercise>> getAll(Pageable pageable) {
        return ResponseEntity.ok(exerciseRepository.findAll(pageable));
    }
}
