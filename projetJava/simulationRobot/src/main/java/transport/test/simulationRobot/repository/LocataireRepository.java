package transport.test.simulationRobot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import transport.test.simulationRobot.model.Locataire;

@Repository
public interface LocataireRepository extends JpaRepository<Locataire, Integer>{
}
