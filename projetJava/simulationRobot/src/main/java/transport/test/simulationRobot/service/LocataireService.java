package transport.test.simulationRobot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import transport.test.simulationRobot.model.Locataire;
import transport.test.simulationRobot.repository.LocataireRepository;

@Service
@AllArgsConstructor
@Slf4j
public class LocataireService {

    private LocataireRepository locataireRepository;
    private static final String ERROR = "Une erreur s'est produite : ";

    public List<Locataire> getAll() {
        try {
            return locataireRepository.findAll();
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    }

    public Optional<Locataire> findById(int id) {
        try {
            return locataireRepository.findById(id);
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    }

    public void save(Locataire locataire) {
        try {
            if (locataire != null)
                locataireRepository.save(locataire);
            else
                throw new NullPointerException("objet null");
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    }

    public void deleteById(int id) {
        try {
            locataireRepository.deleteById(id);
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    }

    public void update(int id, Locataire locataire) {
        try {
            Locataire existingLocataire = locataireRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Locataire non trouvé!"));

            if (existingLocataire != null && locataire != null) {
                if (existingLocataire.getId() == locataire.getId() && id == locataire.getId()) {
                    locataireRepository.save(locataire);
                } else {
                    log.error("Ids différents");
                    throw new IllegalArgumentException("ids différents");
                }
            } else {
                log.error("locataire inexistant");
                throw new NullPointerException("locataire inexistant");
            }
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    }

}
