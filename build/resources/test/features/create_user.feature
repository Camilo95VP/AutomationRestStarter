#language: en
#project: starter automation
#client: xxx
#made by: Sofka
#email: info@sofka.com.co
#phone: (4) 366 83 25
#website: www.sofka.com.co
Feature: It is required to expose an API that receives the user's information and store it in the users table

  It is required to expose an API that receives the user's name, last name, email, telephone, address, and city

  @EN0001-HP
  Scenario Outline: <testId> The backend server of the automation rest consumes the user registration service in a happy path scenario: <testDescription>
    When the backend server calls the microservice to register an user with the data
      | firstName   | lastName   | email   | telephone   | address   | city   |
      | <firstName> | <lastName> | <email> | <telephone> | <address> | <city> |
    Then the response data is validated
      | firstName   | lastName   | email   | telephone   | address   | city   | codeResponse   | description   | type   | databaseCheck   |
      | <firstName> | <lastName> | <email> | <telephone> | <address> | <city> | <codeResponse> | <description> | <type> | <databaseCheck> |
    Examples:
      | testId  | testDescription    | firstName | lastName | email | telephone | address | city | codeResponse | description                 | type    | databaseCheck |
      | TST-001 | Happy path - 1user | OK        | OK       | OK    | OK        | OK      | OK   | 201          | Usuario creado exitosamente | SUCCESS | false         |

#  @EN0001-FE
#  Scenario Outline: <testId> The backend server of the automation rest consumes the user registration service in a happy path scenario: <testDescription>
#    When the backend server calls the microservice to register an user with the data
#      | firstName   | lastName   | email   | telephone   | address   | city   |
#      | <firstName> | <lastName> | <email> | <telephone> | <address> | <city> |
#    Then the response data is validated
#      | firstName   | lastName   | email   | telephone   | address   | city   | codeResponse   | description   | type   | databaseCheck   |
#      | <firstName> | <lastName> | <email> | <telephone> | <address> | <city> | <codeResponse> | <description> | <type> | <databaseCheck> |
#    Examples:
#      | testId  | testDescription    | firstName | lastName | email         | telephone | address | city  | codeResponse | description                                                          | type  | databaseCheck |
#      | TST-001 | Happy path - 1user | 1         | OK       | OK            | OK        | OK      | OK    | 400          | Nombre inválido, solo se permiten caracteres alfabéticos             | ERROR | false         |
#      | TST-002 | Happy path - 1user | empty     | OK       | OK            | OK        | OK      | OK    | 400          | Nombre inválido, solo se permiten caracteres alfabéticos             | ERROR | false         |
#      | TST-003 | Happy path - 1user | null      | OK       | OK            | OK        | OK      | OK    | 400          | El campo 'firstName' no puede estar en blanco                        | ERROR | false         |
#      | TST-004 | Happy path - 1user | OK        | 1        | OK            | OK        | OK      | OK    | 400          | Apellido inválido, solo se permiten caracteres alfabéticos           | ERROR | false         |
#      | TST-005 | Happy path - 1user | OK        | empty    | OK            | OK        | OK      | OK    | 400          | Apellido inválido, solo se permiten caracteres alfabéticos           | ERROR | false         |
#      | TST-007 | Happy path - 1user | OK        | null     | OK            | OK        | OK      | OK    | 400          | El campo 'lastName' no puede estar en blanco                         | ERROR | false         |
#      | TST-008 | Happy path - 1user | OK        | OK       | juangmail.com | OK        | OK      | OK    | 400          | El campo 'email' debe ser una dirección de correo electrónico válida | ERROR | false         |
#      | TST-009 | Happy path - 1user | OK        | OK       | OK            | telefono  | OK      | OK    | 400          | El campo 'telephone' no es válido                                    | ERROR | false         |
#      | TST-010 | Happy path - 1user | OK        | OK       | OK            | empty     | OK      | OK    | 400          | El campo 'telephone' no es válido                                    | ERROR | false         |
#      | TST-011 | Happy path - 1user | OK        | OK       | OK            | null      | OK      | OK    | 400          | El campo 'telephone' no puede estar en blanco                        | ERROR | false         |
#      | TST-012 | Happy path - 1user | OK        | OK       | OK            | OK        | empty   | OK    | 400          | El campo 'address' no puede estar en blanco                          | ERROR | false         |
#      | TST-013 | Happy path - 1user | OK        | OK       | OK            | OK        | null    | OK    | 400          | El campo 'address' no puede estar en blanco                          | ERROR | false         |
#      | TST-014 | Happy path - 1user | OK        | OK       | OK            | OK        | OK      | empty | 400          | El campo 'city' no puede estar en blanco                             | ERROR | false         |
#      | TST-015 | Happy path - 1user | OK        | OK       | OK            | OK        | OK      | null  | 400          | El campo 'city' no puede estar en blanco                             | ERROR | false         |



