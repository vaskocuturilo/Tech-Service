package site.testengineer.techInterview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.testengineer.techInterview.entity.Exercise;
import site.testengineer.techInterview.repository.ExerciseRepository;
import site.testengineer.techInterview.repository.UserRepository;
import site.testengineer.techInterview.service.ExerciseService;

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

    ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<?> createExercise(@Validated @RequestBody Exercise exercise) {
        try {
            return ResponseEntity.ok(exerciseService.create(exercise));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Validated @RequestBody Exercise exercise) {
        try {
            return ResponseEntity.ok(exerciseService.update(id, exercise));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<Page<Exercise>> getAll(Pageable pageable) {
        return ResponseEntity.ok(exerciseRepository.findAll(pageable));
    }
}
