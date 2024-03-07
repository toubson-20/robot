package transport.test.simulationRobot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import transport.test.simulationRobot.model.Logement;
import transport.test.simulationRobot.repository.LogementRepository;

@Service
@AllArgsConstructor
@Slf4j
public class LogementService {
    private LogementRepository logementRepository;
    private static final String ERROR = "Une erreur s'est produite : ";

    public List<Logement> getAll(){
        try {
            return logementRepository.findAll();
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    }

    public Optional<Logement> findById(int id){
        try {
            return logementRepository.findById(id);
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    }

    public void save(Logement logement){
        try {
            if(logement != null)
                logementRepository.save(logement);
            else
                throw new NullPointerException("objet null");
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    }
    
    public void deleteById(int id){
        try {
            logementRepository.deleteById(id);
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    }

    public void edit(int id, Logement logement){
        try {
            Logement existingLogement = logementRepository.findById(id)
                                            .orElseThrow(() -> new EntityNotFoundException("Logement non trouvé!"));

            if(existingLogement.getId() == logement.getId())
                logementRepository.save(logement);
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    }
}
