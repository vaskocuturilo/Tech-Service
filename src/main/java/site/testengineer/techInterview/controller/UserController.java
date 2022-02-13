package site.testengineer.techInterview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import site.testengineer.techInterview.entity.User;
import site.testengineer.techInterview.repository.ExerciseRepository;
import site.testengineer.techInterview.repository.UserRepository;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    UserRepository userRepository;
    ExerciseRepository exerciseRepository;

    @Autowired
    public UserController(UserRepository userRepository, ExerciseRepository exerciseRepository) {
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody User user) {
        Optional<User> optionalUser = userRepository.findByFirstname(user.getFirstname());
        if (optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body("The user " + user.getFirstname() + " found in database; Change name.");
        }
        User saveUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveUser.getId()).toUri();

        return ResponseEntity.created(location).body(saveUser);
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userRepository.findAll(pageable));
    }
}
