package site.testengineer.techInterview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import site.testengineer.techInterview.entity.User;
import site.testengineer.techInterview.repository.ExerciseRepository;
import site.testengineer.techInterview.repository.UserRepository;

import java.net.URI;

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
    public ResponseEntity<User> create(@RequestBody User user) {
        User saveUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(saveUser.getId()).toUri();

        return ResponseEntity.created(location).body(saveUser);
    }
}
