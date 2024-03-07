package transport.test.simulationRobot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Projet de Location",
				version = "1.0.0",
				description = "S'exercer sur les tests automatisés"
		)
)
public class SimulationRobotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulationRobotApplication.class, args);
	}
}

// liens
// springboot : https://bnguimgo.developpez.com/tutoriels/spring/services-rest-avec-springboot-et-spring-resttemplate/?page=premiere-partie-le-serveur