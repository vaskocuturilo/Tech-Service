package site.testengineer.techInterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.testengineer.techInterview.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
