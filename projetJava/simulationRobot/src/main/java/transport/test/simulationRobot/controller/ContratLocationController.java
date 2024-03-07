package transport.test.simulationRobot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import transport.test.simulationRobot.model.ContratLocation;
import transport.test.simulationRobot.service.ContratLocationService;

@RestController
@RequestMapping(path = "/contrat")
@AllArgsConstructor
public class ContratLocationController {

    private ContratLocationService contratService;

     @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContratLocation> getAllContratLocation() {
        return contratService.getAll();
    } 

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<ContratLocation> getById(@PathVariable int id) {
        return contratService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody ContratLocation contratLocation) {
        contratService.save(contratLocation);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id){
        contratService.deleteById(id);
    }
}
