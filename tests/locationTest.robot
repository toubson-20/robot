*** Settings ***
Library  Selenium2Library
Library  RequestsLibrary
Library  Collections
Library  JSONLibrary
Library  OperatingSystem

*** Variables ***
${BASE_URL}                http://localhost:8084/api
${PATH}                    results/locataires.json
${id_suppression}          1
${id_modification}         1
${id_recherche}            1
${locataire_modifié}       Create Dictionary  id=1  nom=Martin  email=martin@soprasteria.com  telephone=1
${locataire_à_ajouter}     Create Dictionary  nom=Martin  email=martin@soprasteria.com  telephone=06677799766

*** Test Cases ***
# Ajout locataire
#     [Documentation]  ajouter un locataire via l'url

Convertion
    ${convert}    Convert To Octal    33

Récupérer tous les locataires
    # Create Session    mysession    ${BASE_URL}
    # ${Response}    Get    mysession    /locataire
    # Log    ${Response.status_code}

    ${Response}  GET  ${BASE_URL}/locataire
    Status Should Be  200
    ${content-type}    Get From Dictionary    ${Response.headers}    Content-Type
    log     ${content-type}
    ${response_string}    Convert Json To String    ${Response.json()}
    Remove File    ${PATH}
    Create File    ${PATH}    ${response_string}

    # Log List  ${response.json()}
    # FOR  ${locataire}  IN  List  ${Response.json()}
    #     Log   ${locataire}
    # END

Récupérer Locataire par Id
    ${Response}=  Get  ${BASE_URL}/locataire/${id_recherche}
    Status Should Be  200
    Log  ${response.json()}

Ajouter un locataire
    Log  ${locataire_à_ajouter} 
    POST  ${BASE_URL}/locataire  json=${locataire_à_ajouter} 
    Status Should Be  200

Supprimer un locataire
    ${Response}  DELETE  ${BASE_URL}/locataire/${id_suppression}
    Status Should Be  200
    
Modifier un locataire
    # PUT  ${BASE_URL}/locataire/edit/${id_modification}  json=${locataire_modifié}
    ${file_content}    Get File    ${PATH}
    ${template}    Evaluate    json.loads(${file_content})    modules=json
    # ${file_content_json}    Convert String To Json    ${file_content}
    
    FOR  ${obj}  IN  @{template}
        Log    ${obj}
        # IF  ${obj}[id] == ${id_modification}
        #     Set To Dictionary    ${obj}    telephone    1
        #     BREAK
        # END
    END

    # ${template}=        Get File    template.json
    # ${template_d}=      Evaluate    json.loads(${template})    modules=json
    # Set To Dictionary   ${template_d['variables']}   id    123
    # Log To Console      ${template_d}
    
    
    # Status Should Be  200

*** Keywords ***
    
