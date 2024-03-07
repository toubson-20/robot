# un fichier robot peut définir plusieurs cas de tests(Suite de Tests)
# https://www.youtube.com/watch?v=2DzshvRirII&list=PLUDwpEzHYYLsCHiiihnwl3L0xPspL7BPG&index=2
***Settings***
# les librairies requises
Documentation  Test exemple  #fournir les informations sur le test
Library  Selenium2Library
Library  OperatingSystem
Library  Browser
# Library  AutoItLibrary

*** Variables ***
# les variables au besoin
${BROWSER}    chromium
${HEADLESS}    false

*** Test Cases ***
# script des tests
Log hello world
    [Documentation]  Simple test hello world
    log  hello world!

Search Domain
    [Documentation]  accéder un site et faire une recherche de domaineet vérifier les résultats de recherche
    #open chrome at GoDaddy website
    open browser  https://ca.godaddy.com  edge
    #faire une pause dans l’exécution jusqu’à ce qu’un élément donné soit visible sur la page
    wait until element is visible  name=domainToCheck
    #inserer la data "super..." dans l'elt domainToCheck
    input text  name=domainToCheck  supercrocodile
    #click search button
    click button  xpath=//form[@name="domainsearchform"]//button
    #faire une pause jusquà ce qu'un elmt html donné soit présent dans la page(dans notre cas le titre "exact match headline" )
    wait until page contains element  css=div.exactMatchHeadline
    # close browser
    close browser

Open file
    run  C:\Users\ytoubiou\Documents\docRobotFramework\notes.txt
    sleep  30s

Recherche sur google
    ouvrir google
    entrer et valider la recherche
    verifier premier lien

*** Keywords ***
# définir les mots clés
ouvrir google
    Open Browser  https://google.com  edge
    Wait Until Element Is Visible  css=button[id="W0wltc"]
    Click Element  css=button[id="W0wltc"]

entrer et valider la recherche
    Input Text  name=q  yvan toubiou
    Press Keys  css=textarea[id="APjFqb"]  ENTER

verifier premier lien
    ${val}  Page Should Contain Element  xpath=//*[@id="rso"]/div[1]/div/div/div/div[1]/div/div/span/a    # robotcode: ignore
    # IF  ${val} == true
    #     Log  link is present in the web page
    # ELSE
    #     Log  link is not present in the web page 
    # END
    log  ${val}
    # close browser