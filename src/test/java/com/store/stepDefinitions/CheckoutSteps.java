package com.store.stepDefinitions;

import com.store.factory.CheckoutPage;
import com.store.util.Variables;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class CheckoutSteps extends Variables
{
    @Given("User is logged")
    public void user_is_logged() {
        loginPage.isLogoutLinkVisible();
        loginPage.accountButtonClick();
    }
    @When("User searches and selects a product {string}")
    public void user_searches_and_selects_a_product(String nameProduct) {
        checkoutPage.addProductToCart(nameProduct);
    }

    @Then("The cart displays the item added {string}")
    public void the_cart_displays_the_item_added(String name) {
        checkoutPage.navigateToYourBasket();
        checkoutPage.isProductInCart(name);
        loginPage.logout();

    }


    @Given("User has added products to the cart")
    public void user_has_added_products_to_the_cart() {
       checkoutPage.isTotalPriceNonZero();
    }


    @Given("User has entered their address information {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void user_has_entered_their_address_information(String country, String name, String mobileNumber, String zipCode, String address,
                                                           String city, String state) {
        checkoutPage.navigateToYourBasket();
        checkoutPage.checkoutClick();
        checkoutPage.addNewAddressClick();
        checkoutPage.addNewAddress(country, name, mobileNumber, zipCode, address, city, state);

    }

    @Given("User has selected the delivery speed")
    public void user_has_selected_the_delivery_speed() {

        checkoutPage.selectOneDayDelivery();
    }

    @Given("User has entered their card information")
    public void user_has_entered_their_card_information() {
        // Write code here that turns the phrase above into concrete actions
        checkoutPage.makePayment("Fabiola", "4012888888881881", "6", "2080","ABCDEFGHIJ");
    }


    @When("User places order and pay")
    public void user_places_order_and_pay() {
        checkoutPage.placeOrderAndPay();
    }

    @When("The order confirmation is displayed")
    public void the_order_confirmation_is_displayed() {
        checkoutPage.verifySuccessfulMessage();
    }





}
