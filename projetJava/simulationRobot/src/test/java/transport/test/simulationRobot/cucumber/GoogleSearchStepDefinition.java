package transport.test.simulationRobot.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import transport.test.simulationRobot.model.Locataire;
import transport.test.simulationRobot.model.Logement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GoogleSearchStepDefinition {

    WebDriver webDriver;
    private static final String BASE_URL = "http://localhost:8084/api";
    private static final RequestSpecification request = RestAssured.given();
    private static List<Locataire> locataires = new ArrayList<>();
    private static List<Logement> logements = new ArrayList<>();
    private Response response;

    // ------------------ Google Search -----------------------------

    @Given("j'ouvre la page de recherche google")
    public void jOuvreLaPageDeRechercheGoogle() {
        System.setProperty("webdriver.edge.driver",
                "src/test/java/transport/test/simulationRobot/cucumber/msedgedriver.exe");
        webDriver = new EdgeDriver();
        webDriver.get("https://www.google.com");
        webDriver.findElement(By.id("W0wltc")).click();
    }

    @When("je cherche le mot {string}")
    public void jeChercheLeMot(String mot) throws InterruptedException {
        webDriver.findElement(By.name("q")).clear();
        webDriver.findElement(By.name("q")).sendKeys(mot);

        // Thread.sleep(2000);

        webDriver.findElement(By.name("btnK")).click();
    }

    @Then("le resultat doit avoir le mot {string}")
    public void leResultatDoitAvoirLeMot(String mot) {
        System.out.println(webDriver.getTitle());
        assertTrue(webDriver.getTitle().contains(mot));
    }

    // ------------------- Locataire -------------------------------
    // Liste locataire
    @Given("récupérer la liste de locataires")
    public void RecupererLaListeDeLocataires() {
        response = request.get(BASE_URL + "/locataire");
        System.out.println(response.asString());
    }

    @Then("liste récupérée avec succès")
    public void ListerecupereeAvecSucces() {
        assertEquals(200, response.getStatusCode());
        locataires = response.getBody().jsonPath().getList(".", Locataire.class);
        assertNotNull(locataires);
        assertFalse(locataires.isEmpty());
    }

    // Ajout
    @Given("j'ajoute un nouvel locataire {string} {string} {string}")
    public void jAjouteUnNouvelLocataire(String nom, String email, String telephone) {

        HashMap<String, String> locataire = new HashMap<>();
        locataire.put("nom", nom);
        locataire.put("email", email);
        locataire.put("telephone", telephone);

        response = request
                .contentType(ContentType.JSON)
                .with()
                .body(locataire)
                .when() // point ou la requete est exécuté
                .post(BASE_URL + "/locataire");

    }

    @Then("locataire ajoute avec succes")
    public void locataireAjouteAvecSucces() {
        assertEquals(200, response.getStatusCode());
    }

    // Suppression
    @Given("supprimer locataire par son id {int}")
    public void supprimerLocataireParSonId(int id) {
        response = request.delete(BASE_URL + "/locataire/" + id);
    }

    @Then("Supprime avec succes")
    public void SupprimeAvecSucces() {
        assertEquals(200, response.getStatusCode());
    }

    // ---------------------- Logement --------------------------------
    // Liste logement
    @Given("récupérer la liste de logements")
    public void RecupererLaListeDeLogements() {
        response = request.get(BASE_URL + "/logement");
        System.out.println(response.asString());
    }

    @Then("logements récupérés avec succès")
    public void LogementsRecupereeAvecSucces() {
        assertEquals(200, response.getStatusCode());
        logements = response.getBody().jsonPath().getList(".", Logement.class);
        System.out.println("\nLogements[0] : "+logements.get(0).getVille()+"\n");
        assertNotNull(logements);
        assertFalse(logements.isEmpty());
    }

    // ---------------------- Contrat -----------------------------------
    // créer contrat
    @When("récupérer un locataire et un logement")
    public void recupererUnLocataireEtUnLogement() {
        assertNotNull(logements.get(0));
        assertNotNull(locataires.get(0));
    }

    @Given("créé un contrat de location")
    public void creeUnContratDeLocation() {
        System.out.println("LOCATAIRE : "+locataires.get(0).toString());
        HashMap<String, Object> contrat = new HashMap<>();
        contrat.put("locataire", locataires.get(0));
        contrat.put("logement", logements.get(0));
        contrat.put("dateDebut", new Date());
        contrat.put("dateFin", new Date());
        contrat.put("montantMensuel", 13000);

        response = request
                .contentType(ContentType.JSON)
                .with()
                .body(contrat)
                .when() // point ou la requete est exécuté
                .post(BASE_URL + "/contrat");
    }

    @Then("contrat créé avec succès")
    public void contratCreeAvecSucces() {
        assertEquals(200, response.getStatusCode());
    }

    @And("enregister le contrat dans un fichier csv")
    public void enregisterLeContratDansUnFichierCsv() {
        String csvFile = "projetJava/simulationRobot/results/contrat.csv";


    }

//    ------------------------ Fichier ------------------------
    @Then("lire et afficher le contenu du fichier")
    public void lireEtAfficherLeContenuDuFichier() throws IOException {
        BufferedReader fichier = new BufferedReader(new FileReader("C:/Users/ytoubiou/OneDrive - Sopra Steria/Documents/moi.json"));
        String ligne;
        while((ligne = fichier.readLine()) != null){
            System.out.println(ligne);
        }

        fichier.close();
    }
}
