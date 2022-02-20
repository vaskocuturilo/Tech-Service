package site.testengineer.techInterview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.testengineer.techInterview.model.Exercise;
import site.testengineer.techInterview.repository.ExerciseRepository;
import site.testengineer.techInterview.repository.UserRepository;
import site.testengineer.techInterview.service.ExerciseService;

@RestController
@RequestMapping(path = {"/api/v1/exercise"}, consumes = {"application/json"})
public class ExerciseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String NEW_EXERCISE = "A new exercise was create with name:{}.";
    private static final String UPDATE_EXERCISE = "A exercise with exercise:{} was update.";
    private static final String GET_ALL_EXERCISES = "Get information about all exercises.";

    UserRepository userRepository;
    ExerciseRepository exerciseRepository;
    ExerciseService exerciseService;

    @Autowired
    public ExerciseController(UserRepository userRepository, ExerciseRepository exerciseRepository) {
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Operation(summary = "Create a new exercise")
    @ApiResponse(responseCode = "200", description = "The exercise was create",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Exercise.class))})
    @PostMapping
    public ResponseEntity<?> createExercise(@Validated @RequestBody Exercise exercise) {
        try {
            logger.info(NEW_EXERCISE, exercise.getExerciseName());
            return ResponseEntity.ok(exerciseService.create(exercise));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @Operation(summary = "Updated some a exercise fields")
    @ApiResponse(responseCode = "200", description = "The exercise was update",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Exercise.class))})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExercise(@PathVariable Long id, @Validated @RequestBody Exercise exercise) {
        try {
            logger.info(UPDATE_EXERCISE, exercise.toString());
            return ResponseEntity.ok(exerciseService.update(id, exercise));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @Operation(summary = "Get all exercises")
    @ApiResponse(responseCode = "200", description = "Get information about all the exercise",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Exercise.class))})
    @GetMapping
    public ResponseEntity<Page<Exercise>> getAllExercise(Pageable pageable) {
        logger.info(GET_ALL_EXERCISES, pageable.toString());
        return ResponseEntity.ok(exerciseRepository.findAll(pageable));
    }
}
