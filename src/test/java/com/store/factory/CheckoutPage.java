package com.store.factory;

import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//button[contains(@class,'mat-focus-indicator buttons mat-button mat-button-base ng-star-inserted')]")
    WebElement yourBasketButton;

    @FindBy(id = "checkoutButton")
    WebElement checkoutButton;


    @FindBy(xpath = "//mat-label[text()='Country']/ancestor::div[contains(@class,'mat-form-field-infix')]//input")
    WebElement countryInput;

    @FindBy(xpath = "//mat-label[text()='Name']/ancestor::div[contains(@class,'mat-form-field-infix')]//input")
    WebElement nameInput;

    @FindBy(xpath = "//mat-label[text()='Mobile Number']/ancestor::div[contains(@class,'mat-form-field-infix')]//input")
    WebElement mobileNumberInput;

    @FindBy(xpath = "//mat-label[text()='ZIP Code']/ancestor::div[contains(@class,'mat-form-field-infix')]//input")
    WebElement zipCodeInput;

    @FindBy(id = "address")
    WebElement addressInput;

    @FindBy(xpath = "//mat-label[text()='City']/ancestor::div[contains(@class,'mat-form-field-infix')]//input")
    WebElement cityInput;

    @FindBy(xpath = "//mat-label[text()='State']/ancestor::div[contains(@class,'mat-form-field-infix')]//input")
    WebElement stateInput;

    @FindBy(id = "submitButton")
    WebElement submitButton;

    @FindBy(xpath = "//mat-cell //mat-radio-button")
    WebElement addressRadioButton;

    @FindBy(xpath = "//button[contains(@class,'btn-next')]")
    WebElement continueButton;

    @FindBy(xpath = "//h1[text()='Select an address']")
    WebElement addressHeader;

    @FindBy(xpath = "//button[contains(@class,'btn-new-address')]")
    WebElement addNewAddressButton;

    @FindBy(xpath = "//mat-cell[contains(text(),'One Day Delivery')]/ancestor::mat-row//mat-radio-button")
    WebElement oneDayDeliveryRadioButton;

    @FindBy(xpath = "//button[contains(@class,'nextButton')]")
    WebElement continueButton2;


    // Card Details Section
    @FindBy(xpath = "//mat-panel-title[contains(text(),'Add new card')]/ancestor::mat-expansion-panel//span[contains(@class,'mat-expansion-indicator')]")
    WebElement addNewCardPanel;

    //@FindBy(xpath = "//mat-label[text()='Name']/ancestor::div[contains(@class,'mat-form-field-infix')]//input")
   // WebElement nameInput;

    @FindBy(xpath = "//mat-label[text()='Card Number']/ancestor::div[contains(@class,'mat-form-field-infix')]//input")
    WebElement cardNumberInput;

    @FindBy(xpath = "//mat-label[text()='Expiry Month']/ancestor::div[contains(@class,'mat-form-field-infix')]//select")
    WebElement expiryMonthSelect;

    @FindBy(xpath = "//mat-label[text()='Expiry Year']/ancestor::div[contains(@class,'mat-form-field-infix')]//select")
    WebElement expiryYearSelect;

    //	@FindBy(xpath = "//mat-row[1]//input[contains(@class,'mat-radio-input')]")
    @FindBy(xpath = "//mat-row[1]//span[@class='mat-radio-outer-circle']")
    WebElement cardDetailsRadioButton;

    @FindBy(xpath = "//h1[text()='My Payment Options']")
    WebElement paymentHeader;

    // Coupon Section
    @FindBy(xpath = "//mat-panel-title[contains(text(),'Add a coupon')]/ancestor::mat-expansion-panel//span[contains(@class,'mat-expansion-indicator')]")
    WebElement addCouponPanel;

    @FindBy(xpath = "//mat-label[text()='Coupon']/ancestor::div[contains(@class,'mat-form-field-infix')]//input")
    WebElement couponInput;

    @FindBy(id = "applyCouponButton")
    WebElement applyCouponButton;

    @FindBy(xpath = "//h1[text()=\"Thank you for your purchase!\"]\n")
    WebElement successfulMessage;



    @FindBy(id="checkoutButton")
    WebElement placeOrderAndPayButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        return false;
    }

    public void addProductToCart(String productName) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean productAdded = false;

        while (!productAdded) {

            List<WebElement> products = driver.findElements(By.xpath("//div[contains(text(),'" + productName + "')]"));

            if (!products.isEmpty()) {

                WebElement addToCartButton = wait
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'" + productName
                                + "')]/ancestor::div[contains(@class,'mat-grid-tile-content')]//button")));

                actions.scrollToElement(addToCartButton).build().perform();

                javascriptExecutor.executeScript("arguments[0].click();", addToCartButton);
                productAdded = true;
            } else {

                WebElement nextButton = wait
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Next page']")));
                if (nextButton.isEnabled()) {

                    actions.moveToElement(nextButton).build().perform();

                    javascriptExecutor.executeScript("arguments[0].click();", nextButton);

                    // Wait for the next page to load
                    wait.until(ExpectedConditions.stalenessOf(nextButton)); // Wait for the next page to load completely
                } else {
                    System.out.println("Product not found in any page.");
                    break;
                }
            }
        }

    }

    public void navigateToYourBasket() {
        javascriptExecutor.executeScript("arguments[0].click();", yourBasketButton);
    }


    public boolean isProductInCart(String productName) {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//mat-cell[contains(text(),'" + productName + "')]")));


            return product.isDisplayed();

        } catch (NoSuchElementException ex) {

            return false;
        }
    }


    public boolean isTotalPriceNonZero() {
        try {
            WebElement totalPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='price' and contains(text(),'Total Price:')]")));

            String totalPriceText = totalPriceElement.getText();


            totalPriceText = totalPriceText.replace("Total Price:", "").trim().replace("¤", "").trim();

            double totalPrice = Double.parseDouble(totalPriceText);

            return totalPrice != 0;

        } catch (Exception e) {

            System.out.println("Error al verificar el precio total: " + e.getMessage());
            return false;
        }
    }


    public CheckoutPage checkoutClick() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));


        javascriptExecutor.executeScript("arguments[0].click();", checkoutButton);

        return this;
    }

    public void addNewAddressClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addNewAddressButton));

        javascriptExecutor.executeScript("arguments[0].click();", addNewAddressButton);
    }



    public CheckoutPage addNewAddress(String country, String name, String mobileNumber, String zipCode, String address,
                                      String city, String state) {



        writeText(countryInput, country);
        writeText(nameInput, name);
        writeText(mobileNumberInput, String.valueOf(mobileNumber));
        writeText(zipCodeInput, zipCode);
        writeText(addressInput, address);
        writeText(cityInput, city);
        writeText(stateInput, state);

        javascriptExecutor.executeScript("arguments[0].click();", submitButton);
        javascriptExecutor.executeScript("arguments[0].click();", addressRadioButton);
        return onContinueClick();

    }

    public CheckoutPage onContinueClick() {

        javascriptExecutor.executeScript("arguments[0].click();", continueButton);
        return this;
    }

    public boolean isAddressHeaderDisplayed() {
        try {

            return addressHeader.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public CheckoutPage selectOneDayDelivery() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", oneDayDeliveryRadioButton);
        return continueClick2();
    }

    private CheckoutPage continueClick2() {

        javascriptExecutor.executeScript("arguments[0].click();", continueButton2);
        return this;
    }


    public CheckoutPage makePayment(String name, String cardNumber, String expiryMonth, String expiryYear, String couponCode) {

        addNewCard(name, cardNumber, expiryMonth, expiryYear);
        String errorText = addCoupon(couponCode);
        System.out.println(errorText);
        javascriptExecutor.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(cardDetailsRadioButton)));
        return continueClick2();
    }

    private String addCoupon(String couponCode) {
        javascriptExecutor.executeScript("arguments[0].click();", addCouponPanel);
        writeText(couponInput,couponCode);
        javascriptExecutor.executeScript("arguments[0].click();", applyCouponButton);

        List<WebElement> errors = driver.findElements(By.xpath("//div[contains(@class,'error')]"));
        if(!errors.isEmpty())
            return errors.getClass().toString();
        return "";

    }

    private void addNewCard(String name, String cardNumber, String expiryMonth, String expiryYear) {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", addNewCardPanel);

        writeText(nameInput,name);
        writeText(cardNumberInput,cardNumber);

        Select expiryMonthDropDown = new Select(expiryMonthSelect);
        expiryMonthDropDown.selectByValue(expiryMonth);

        Select expiryYearDropDown = new Select(expiryYearSelect);
        expiryYearDropDown.selectByValue(expiryYear);

        javascriptExecutor.executeScript("arguments[0].click();", submitButton);

    }


    public CheckoutPage placeOrderAndPay() {
        javascriptExecutor.executeScript("arguments[0].click();", placeOrderAndPayButton);
        return this;
    }

    @SneakyThrows
    public CheckoutPage verifySuccessfulMessage() {

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(successfulMessage));
        assertTrue(successfulMessage.isDisplayed());
        return this;
    }


}
