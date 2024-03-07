package transport.test.simulationRobot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import transport.test.simulationRobot.model.ContratLocation;
import transport.test.simulationRobot.repository.ContratLocatIonRepository;

@AllArgsConstructor
@Service
@Slf4j
public class ContratLocationService {
    
    private ContratLocatIonRepository contratLocRepository;
    private static final String ERROR = "Une erreur s'est produite : ";

    public List<ContratLocation> getAll(){
        try {
            return contratLocRepository.findAll();
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    } 

    public Optional<ContratLocation> findById(int id){
        try {
            return contratLocRepository.findById(id);
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    }

    public void save(ContratLocation contratLocation){
        try {
            if(contratLocation != null)
                contratLocRepository.save(contratLocation);
            else
                throw new NullPointerException("objet null");
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    }
    
    public void deleteById(int id){
        try {
            contratLocRepository.deleteById(id);
        } catch (Exception e) {
            log.error(ERROR + e.getMessage(), e);
            throw new IllegalArgumentException(ERROR, e);
        }
    }
}
