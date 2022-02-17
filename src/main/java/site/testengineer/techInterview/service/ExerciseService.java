package site.testengineer.techInterview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import site.testengineer.techInterview.entity.Exercise;
import site.testengineer.techInterview.entity.User;
import site.testengineer.techInterview.repository.ExerciseRepository;
import site.testengineer.techInterview.repository.UserRepository;

import java.net.URI;
import java.util.Optional;

@Service
public class ExerciseService {

    UserRepository userRepository;
    ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(UserRepository userRepository, ExerciseRepository exerciseRepository) {
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public ResponseEntity<?> create(Exercise exercise) {
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

    public ResponseEntity<?> update(Long id, Exercise exercise) {
        Optional<User> optionalUser = userRepository.findById(exercise.getUser().getId());

        if (!optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body("");
        }

        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);
        if (!optionalExercise.isPresent()) {
            return ResponseEntity.badRequest().body("");
        }

        exercise.setUser(optionalUser.get());
        exercise.setId(optionalExercise.get().getId());
        exerciseRepository.save(exercise);

        return ResponseEntity.ok("The data for exercise with id = " + id + "was update");
    }
}
