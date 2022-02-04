package site.testengineer.techInterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.testengineer.techInterview.entity.Exercises;

public interface ExercisesRepository extends JpaRepository<Exercises, Long> {
}
