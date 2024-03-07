package transport.test.simulationRobot.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import transport.test.simulationRobot.model.Locataire;
import transport.test.simulationRobot.service.LocataireService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@AllArgsConstructor
@RequestMapping(path = "locataire")
public class LocataireController {
    
    private LocataireService locataireService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Locataire> getAllLocataire() {
        return locataireService.getAll();
    } 

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Locataire> getById(@PathVariable int id) {
        return locataireService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody Locataire locataire) {
        locataireService.save(locataire);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id){
        locataireService.deleteById(id);
    }

    @PutMapping(path = "edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void edit(@PathVariable int id, @RequestBody Locataire locataire) {
        locataireService.update(id, locataire);
    }
}
