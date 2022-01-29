package site.testengineer.techInterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.testengineer.techInterview.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
