*** Settings ***
Library    OperatingSystem


*** Variables ***
${PATH}         C:/Users/ytoubiou/OneDrive - Sopra Steria/Documents/exemple
${PATH_DOCX}    C:/Users/ytoubiou/OneDrive - Sopra Steria/Bureau/doc.docx
${PATH_JSON}    C:/Users/ytoubiou/OneDrive - Sopra Steria/Documents/moi.json


*** Test Cases ***
Création et vérification fichier
    [Documentation]    céer un fichier et vérifier le contenu du fichier et le supprimer 

    Create File    ${PATH}    Test Automatisé, Tests fonctionnnels ou d'intégration sur des flux
    File Should Exist    ${PATH}
    ${file_content}    Get File    ${PATH_JSON}
    Should Contain    ${file_content}    Tests fonctionnnels ou d'intégration sur des flux 
    Append To File    ${PATH}    \nBy Toubson    encoding=utf-8
    Remove File    ${PATH}
    # Log    ${file_content}

Modification du contenu d'un fichier
    Create File    results/test.txt    Bonjour \nToubson

    

*** Keywords ***