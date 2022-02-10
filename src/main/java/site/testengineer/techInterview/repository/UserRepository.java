package site.testengineer.techInterview.repository;

import org.springframework.data.repository.CrudRepository;
import site.testengineer.techInterview.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
