package site.testengineer.techInterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.testengineer.techInterview.model.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
