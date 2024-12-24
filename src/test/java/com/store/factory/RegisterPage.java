package com.store.factory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class RegisterPage extends BasePage {

    // Web Elements by using Page Factory
    @FindBy(id = "emailControl")
    WebElement emailField;

    @FindBy(id = "passwordControl")
    WebElement passwordField;

    @FindBy(id = "repeatPasswordControl")
    WebElement repeatPasswordField;

    @FindBy(name = "securityQuestion")
    WebElement securityQuestionField;

    @FindBy(className = "mat-option-text")
    List<WebElement> securityQuestionOptionsField;

    @FindBy(id = "securityAnswerControl")
    WebElement securityAnswerField;

    @FindBy(id = "registerButton")
    WebElement registerBtn;

    @FindBy(xpath ="//mat-card//h1[text()='Login']\n")
    WebElement LoginText;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }


    public RegisterPage register(String email, String password, String securityQuestion, String securityAnswer) {

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        LoginPage loginPage = new LoginPage(driver);
        loginPage.newCustomerClick();
        writeText(emailField,email);
        writeText(passwordField,password);
        writeText(repeatPasswordField,password);


        javascriptExecutor.executeScript("arguments[0].click();", securityQuestionField);

        for (WebElement option : securityQuestionOptionsField) {

            if (option.getText().trim().equals(securityQuestion.trim())) {
                option.click();
                break;
            }
        }


        //securityAnswerField.clear();
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
        securityAnswerField.sendKeys(securityAnswer);
        return registerButtonClick();

    }

   public RegisterPage registerButtonClick() {

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", registerBtn);
        return this;
    }

    @Override
    public boolean isAt() {
        return false;
    }


    public RegisterPage verifySuccessfulRegister() {
        wait.until(ExpectedConditions.visibilityOf(LoginText));
        assertTrue(LoginText.isDisplayed());
        return this;
    }
}
