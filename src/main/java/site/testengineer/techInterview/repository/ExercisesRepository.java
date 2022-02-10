package site.testengineer.techInterview.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import site.testengineer.techInterview.entity.Exercises;
import site.testengineer.techInterview.entity.User;

import java.util.List;

public interface ExercisesRepository extends CrudRepository<Exercises, Long> {
    List<Exercises> findByExercise(User user, Sort sort);
}
