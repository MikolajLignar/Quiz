package pl.mikolajlignar.quiz.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mikolajlignar.quiz.database.entities.PlayerEntity;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
}