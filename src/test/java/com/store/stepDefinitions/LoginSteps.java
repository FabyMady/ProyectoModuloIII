package com.store.stepDefinitions;


import com.store.annotations.TakeScreenshot;
import com.store.factory.HomePage;
import com.store.factory.LoginPage;
import com.store.util.Variables;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginSteps extends Variables {


    @Given("User is in the login page")
    public void user_is_in_the_login_page() {
        homePage.goToHomePage().goToLoginPageViaAccount();

    }

    @When("User tries to login with {string} and {string}")
    public void user_tries_to_login_with_and(String userName, String password) {
        loginPage.login(userName, password);
    }

    @Then("User verifies successful login message")
    public void user_verifies_successful_login_message() {
        loginPage.verifySuccessfulLogin();
    }

    @Then("User verifies invalid login message")
    public void user_verifies_invalid_login_message() {
        loginPage.verifyLoginErrorMessage("Invalid email or password. ");
    }
}