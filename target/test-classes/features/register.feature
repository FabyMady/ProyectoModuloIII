@Register
Feature: Register Feature

  @happyRegister
  Scenario Outline: User registers on the website with valid data
    Given User is in the registration page
    When User tries to register with "<email>", "<password>", "<securityQuestion>" and "<answer>"
    Then User verifies successful registration message
    Examples:
      | email                | password |  securityQuestion         | answer          |
      | user1@example.com     | Pass123  | Your eldest siblings middle name?  | Silvia         |


  @negativeRegister
  Scenario Outline: User registers on the website with invalid data
    Given User is in the registration page
    When User tries to register with "<email>", "<password>", "<securityQuestion>" and "<answer>"
    Then User verifies invalid register message
    Examples:
      | email  | password | securityQuestion         | answer          |
      | user   | Pass123  |  Your eldest siblings middle name?  | Silvia         |






