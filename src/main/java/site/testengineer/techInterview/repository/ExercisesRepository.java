package site.testengineer.techInterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.testengineer.techInterview.entity.ExercisesEntity;

public interface ExercisesRepository extends JpaRepository<ExercisesEntity, Long> {
}
