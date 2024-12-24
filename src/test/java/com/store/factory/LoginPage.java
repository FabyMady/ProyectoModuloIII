package com.store.factory;

import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPage extends BasePage {

    // Web Elements by using Page Factory
    @FindBy(name = "email")
    public WebElement userName;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(css = "button[type='submit']")
    public WebElement submitButton;

    @FindBy(css = "button[aria-label='Show the shopping cart']")
    public WebElement shoppingCartButton;

    @FindBy(linkText = "Not yet a customer?")
    public WebElement  newCustomerLink;

    @FindBy(id = "navbarAccount")
    private WebElement accountButton;

    @FindBy(id = "navbarLogoutButton")
    WebElement logoutButton;
    // Web Elements by using By Class
    By errorMessageBy = By.className("error");


    public LoginPage(WebDriver driver) {
        super(driver);
    }
    // Page Methods
    public LoginPage login(String userName, String password) {
        writeText(this.userName, userName);
        writeText(this.password, password);
        click(submitButton);
        return this;
    }

    public LoginPage verifyLoginErrorMessage(String expectedText) {
        String actualMessage = readText(errorMessageBy).replaceAll("\\s+", " ").trim();
        assertEquals(expectedText.replaceAll("\\s+", " ").trim(), actualMessage);
        return this;
    }

    public LoginPage verifyUnclickableLoginButton() {
        assertFalse(submitButton.isEnabled());
        return this;
    }



    public LoginPage newCustomerClick() {
        try {
           wait.until(ExpectedConditions.visibilityOf(newCustomerLink));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newCustomerLink);
           wait.until(ExpectedConditions.elementToBeClickable(newCustomerLink));

            WebElement overlay = null;
            try {
                overlay = driver.findElement(By.id("cdk-overlay-1"));
                if (overlay != null && overlay.isDisplayed()) {
                    wait.until(ExpectedConditions.invisibilityOf(overlay));
                }
            } catch (NoSuchElementException e) {
            }

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newCustomerLink);
        } catch (Exception e) {

            //System.out.println("Error durante el método 'newCustomerClick': " + e.getMessage());
        }

        return this;
    }



    @SneakyThrows
    public LoginPage verifySuccessfulLogin() {
        // Usando WebDriverWait en lugar de Thread.sleep()
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(shoppingCartButton));
        assertTrue(shoppingCartButton.isDisplayed());
        return this;
    }






    public boolean isLogoutLinkVisible() {
        try {
            accountButtonClick();
            System.out.println("is logout displayed" + logoutButton.isDisplayed());
            return logoutButton.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Caught Exception");
            return false;
        }
    }

    public void accountButtonClick() {
        actions.moveToElement(accountButton).build().perform();
        javascriptExecutor.executeScript("arguments[0].click();", accountButton);
    }

    public void logout() {
        accountButtonClick();
        javascriptExecutor.executeScript("arguments[0].click();", logoutButton);

    }


    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.userName.isDisplayed());
    }
}
