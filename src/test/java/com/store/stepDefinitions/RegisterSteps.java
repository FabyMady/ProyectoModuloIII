package com.store.stepDefinitions;


import com.store.util.Variables;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class RegisterSteps extends Variables {


    @Given("User is in the registration page")
    public void user_is_in_the_registration_page() {
        homePage.goToHomePage().goToLoginPageViaAccount();
        loginPage.newCustomerClick();
    }

    @When("User tries to register with {string}, {string}, {string} and {string}")
    public void user_tries_to_register_with_and(String email, String password, String securityQuestion, String securityAnswer) {
        registerPage.register(email, password, securityQuestion, securityAnswer);
    }

    @Then("User verifies successful registration message")
    public void user_verifies_successful_registration_message() {
        registerPage.verifySuccessfulRegister();
    }
}