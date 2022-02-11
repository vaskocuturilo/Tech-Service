package site.testengineer.techInterview.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import site.testengineer.techInterview.entity.Exercise;
import site.testengineer.techInterview.entity.User;

import java.util.List;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    List<Exercise> findByExercise(User user, Sort sort);
}
