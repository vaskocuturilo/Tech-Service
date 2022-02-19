package site.testengineer.techInterview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.testengineer.techInterview.entity.User;
import site.testengineer.techInterview.repository.ExerciseRepository;
import site.testengineer.techInterview.repository.UserRepository;
import site.testengineer.techInterview.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String NEW_USER = "A new user was create with email:{}.";
    private static final String DELETE_USER = "A user with id:{} was delete.";
    private static final String UPDATE_USER = "A user with id:{} was update.";

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
            logger.info(NEW_USER, user.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            logger.info(DELETE_USER, id);
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Validated @RequestBody User user) {
        try {
            logger.info(DELETE_USER, user.toString());
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
