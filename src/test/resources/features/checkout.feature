@Checkout
Feature: Checkout Feature

  Background:
    Given User is in the login page
    When User tries to login with "faby@gmail.com" and "FUB123"
    Then User verifies successful login message



  @AddProducts
  Scenario Outline: User adds product to the cart
    Given User is logged
    When User searches and selects a product "<name>"
    Then The cart displays the item added "<name>"
    Examples:
      | name               |
      | Apple Juice     |



  @CheckoutComplete
  Scenario Outline: Successful checkout process
    Given User has added products to the cart
    And User has entered their address information "<country>", "<name>", "<mobileNumber>", "<zipCode>", "<address>", "<city>", "<state>"
    And User has selected the delivery speed
    And User has entered their card information
    When User places order and pay
    And The order confirmation is displayed
    Examples:
      | country     | name     | mobileNumber     | zipCode    | address    | city     | state
      | Bolivia     | Adela  |  64987542  | 0000  | Circunvalacion  | Cochabamba | Bolivia


