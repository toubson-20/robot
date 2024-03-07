package transport.test.simulationRobot.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:results/results.html"},
        features = {"src/test/java/transport/test/simulationRobot/cucumber"},
        glue = {""},
        tags = "@LireFichier"
)
public class AppTest {
}