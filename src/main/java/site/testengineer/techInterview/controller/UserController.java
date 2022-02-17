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
import site.testengineer.techInterview.service.UserService;

import java.net.URI;
import java.util.Optional;

import static site.testengineer.techInterview.utils.EmailValidation.isEmailValid;

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

    UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Validated @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.create(user));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Validated @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.update(id, user));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userRepository.findAll(pageable));
    }
}
