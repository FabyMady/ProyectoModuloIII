@Login
Feature: Login Feature

  @happy
  Scenario Outline: User logins the website with correct username and password
  Given User is in the login page
  When User tries to login with "<username>" and "<password>"
  Then User verifies successful login message
  Examples:
  | username               | password |
  | faby@gmail.com         | FUB123 |


  @negative
  Scenario Outline: User logins the website with invalid username and invalid password-1
  Given User is in the login page
  When User tries to login with "<username>" and "<password>"
  Then User verifies invalid login message
  Examples:
  | username               | password |
  | dsazdovski@test.com | 11223344 |


  @negative
  Scenario Outline: User logins the website with invalid username and invalid password-2
  Given User is in the login page
  When User tries to login with "<username>" and "<password>"
  Then User verifies invalid login message
  Examples:
  | username               | password |
  | dsazdovski@test.com | 11223344 |


  @negative
  Scenario Outline: I login the website with invalid username and invalid password-3
    Given User is in the login page
    When User tries to login with "<username>" and "<password>"
    Then User verifies invalid login message
  Examples:
  | username               | password |
  | dsazdovski@test.com | 11223344 |

