package transport.test.simulationRobot.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import transport.test.simulationRobot.model.Logement;
import transport.test.simulationRobot.service.LogementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/logement")
@AllArgsConstructor
public class LogementController {
        private LogementService logementService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Logement> getAllLogement() {
        return logementService.getAll();
    } 

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Logement> getById(@PathVariable int id) {
        return logementService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody Logement logement) {
        logementService.save(logement);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable int id){
        logementService.deleteById(id);
    }

    @PutMapping(path = "edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void edit(@PathVariable int id, @RequestBody Logement logement) {
        logementService.edit(id, logement);
    }

    @GetMapping(path = "/ville", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Logement> getByVille(@RequestParam(name="ville", required=true) String ville, @RequestParam(name="piece", required=false) int piece) {
        return logementService.getAll().stream().filter(elt -> elt.getVille().toLowerCase().equalsIgnoreCase(ville) && elt.getPrix()>=piece).collect(Collectors.toList());
    }


    // List<String> listeTest = Arrays.asList("demain","lundi", "aurevoir");
	// 	listeTest.stream().sorted().forEach(System.out::println);
	// 	System.out.println("\n"+listeTest.stream().map(x -> x+"2").collect(Collectors.toSet()));
}
