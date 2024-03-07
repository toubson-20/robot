Feature: Tests
  Principalement des tests sur des API

#  Scenario: Recherche basique
#    #prérequis
#    Given j'ouvre la page de recherche google
#    #action à effectuer
#    When je cherche le mot "cucumber test"
#    #ce à quoi on s'attend(résultat)
#    Then le resultat doit avoir le mot "cucumber"
  
  Rule: Locataire
    @AllLoc
    Scenario: Afficher tous les locataires
      Given récupérer la liste de locataires
      Then liste récupérée avec succès

#    @AddLoc
#    Scenario Outline: Ajout locataire
#      Given  j'ajoute un nouvel locataire "<nom>" "<email>" "<telephone>"
#      Then   locataire ajoute avec succes
#
#      Examples:
#      | nom         | email              | telephone    |
#      | John Cena   | johnCena@gmail.com | 0632124510   |

#    @DeleteLoc
#    Scenario: Supprimer locataire
#      Given supprimer locataire par son id 5
#      Then  Supprime avec succes

  Rule: Logement
    @AllLog
    Scenario: Afficher tous les logements
      Given récupérer la liste de logements
      Then logements récupérés avec succès

  Rule: Contrat Location
    @Contrat
    Scenario: créer un contrat de location
      When récupérer un locataire et un logement
      Given créé un contrat de location
      Then contrat créé avec succès
      And enregister le contrat dans un fichier csv

  Rule: Fichiers
    @LireFichier
    Scenario: lire dans un fichier
      Then lire et afficher le contenu du fichier
        