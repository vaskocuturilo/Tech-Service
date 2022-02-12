package site.testengineer.techInterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.testengineer.techInterview.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
