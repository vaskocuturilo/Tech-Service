package site.testengineer.techInterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.testengineer.techInterview.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByfirstname(String firstname);

}
