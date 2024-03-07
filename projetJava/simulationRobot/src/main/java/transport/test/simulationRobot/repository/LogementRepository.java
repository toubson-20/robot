package transport.test.simulationRobot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import transport.test.simulationRobot.model.Logement;


@Repository
public interface LogementRepository extends JpaRepository<Logement, Integer>{
}
