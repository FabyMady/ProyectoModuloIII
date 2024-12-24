package com.store.factory;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    LoginPage loginPage;

    // Web Elements By Using Page Factory
    @FindBy(how = How.ID, using = "navbarAccount")
    private WebElement accountButton;

    @FindBy(how = How.ID, using = "navbarLoginButton")
    private WebElement loginButton;

    @FindBy(how = How.CSS, using = "a.cc-btn.cc-dismiss")
    private WebElement dismissCookieButton;

    @FindBy(how = How.CSS, using = "button[aria-label='Close Welcome Banner']")
    private WebElement dismissWelcomePopupButton;

    By homePageLogo = By.cssSelector("h1");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage goToHomePage() {
       //String baseURL = "http://localhost:3000/#/";
        String baseURL = "https://juice-shop.herokuapp.com/#/";
        driver.get(baseURL);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        if (isElementDisplayed(dismissWelcomePopupButton ) ) {
            dismissPopups();
        }

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return this;
    }


    // Dismiss Popups
    public HomePage dismissPopups() {
        if (isElementDisplayed(dismissWelcomePopupButton)) {
            click(dismissWelcomePopupButton);
        }

        if (isElementDisplayed(dismissCookieButton)) {
            click(dismissCookieButton);
        }

        return this;
    }



    private boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;  // Element not found or not visible
        }
    }

    public HomePage goToLoginPageViaAccount() {
        click(accountButton);
        wait.until(ExpectedConditions.visibilityOf(loginButton));  // Espera explícita para el botón Login
        click(loginButton);
        return this;
    }



    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.accountButton.isDisplayed());
    }

    public HomePage verifyThatIAmAtHomePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePageLogo)); // Espera explícita
        Assertions.assertTrue(driver.findElement(homePageLogo).isDisplayed(), "Home page logo is not displayed");
        return this;
    }
}
