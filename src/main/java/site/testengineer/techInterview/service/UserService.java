package site.testengineer.techInterview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import site.testengineer.techInterview.entity.Exercise;
import site.testengineer.techInterview.entity.User;
import site.testengineer.techInterview.repository.ExerciseRepository;
import site.testengineer.techInterview.repository.UserRepository;

import java.net.URI;
import java.util.Optional;

import static site.testengineer.techInterview.utils.EmailValidation.isEmailValid;

@Service
public class UserService {

    UserRepository userRepository;
    ExerciseRepository exerciseRepository;

    @Autowired
    public UserService(UserRepository userRepository, ExerciseRepository exerciseRepository) {
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public ResponseEntity<?> create(User user) {
        if (!isEmailValid(user.getEmail(), "^(.+)@(.+)$")) {
            return ResponseEntity.badRequest().body("The user " + user.getEmail() + " has invalid email.");
        }
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body("The user " + user.getEmail() + " exist. Please, change user name.");
        }
        User saveUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saveUser.getId()).toUri();

        return ResponseEntity.created(location).body(saveUser);
    }

    public ResponseEntity<?> delete(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("The user with id = " + id + " not exist.");
        }

        userRepository.deleteById(id);

        return ResponseEntity.ok("The user with id  = " + id + " was delete from database.");
    }

    public ResponseEntity<?> update(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("The user with id = " + id + " not exist.");
        }

        user.setId(user.getId());
        userRepository.save(user);

        return ResponseEntity.ok("The data for user with id = " + id + "was update");
    }
}
