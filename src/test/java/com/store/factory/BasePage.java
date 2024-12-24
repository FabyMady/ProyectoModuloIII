package com.store.factory;

import com.store.util.LogUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor javascriptExecutor;
    protected LogUtil logUtil;
    protected Actions actions;

    // Constructor para inicializar el WebDriver y los otros campos
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.javascriptExecutor = (JavascriptExecutor) driver;
        this.logUtil = new LogUtil();
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    public abstract boolean isAt();

    public <T> void waitElement(T elementAttr) {
        if (elementAttr instanceof By) {
            wait.until(ExpectedConditions.presenceOfElementLocated((By) elementAttr));
        } else if (elementAttr instanceof WebElement) {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
        }
    }

    public <T> void waitElements(T elementAttr) {
        if (elementAttr instanceof By) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
        } else if (elementAttr instanceof WebElement) {
            wait.until(ExpectedConditions.visibilityOfAllElements((WebElement) elementAttr));
        }
    }

    // Click Method by using JAVA Generics (You can use both By or Web element)
    public <T> void click(T elementAttr) {
        waitElement(elementAttr);
        if (elementAttr instanceof By) {
            driver.findElement((By) elementAttr).click();
        } else if (elementAttr instanceof WebElement) {
            ((WebElement) elementAttr).click();
        }
    }

    public void jsClick(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOfElementLocated(by)));
    }

    public <T> void writeText(T elementAttr, String text) {
        waitElement(elementAttr);
        if (elementAttr instanceof By) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
            driver.findElement((By) elementAttr).sendKeys(text);
        } else if (elementAttr instanceof WebElement) {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
            ((WebElement) elementAttr).sendKeys(text);
        }
    }

    public <T> String readText(T elementAttr) {
        waitElement(elementAttr);
        if (elementAttr instanceof By) {
            return driver.findElement((By) elementAttr).getText();
        } else if (elementAttr instanceof WebElement) {
            return ((WebElement) elementAttr).getText();
        }
        return "";
    }

    public void handleOverlay(By overlayBy) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayBy));
    }

    // Close popup if exists
    public void handlePopup(By by) {
        waitElements(by);
        List<WebElement> popup = driver.findElements(by);
        if (!popup.isEmpty()) {
            popup.get(0).click();
        }
    }
}
