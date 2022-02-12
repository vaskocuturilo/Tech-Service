package site.testengineer.techInterview.repository;

import org.springframework.data.repository.CrudRepository;
import site.testengineer.techInterview.entity.Exercise;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

}
