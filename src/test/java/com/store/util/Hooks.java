package com.store.util;

import com.store.factory.CheckoutPage;
import com.store.factory.HomePage;
import com.store.factory.LoginPage;
import com.store.factory.RegisterPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class Hooks extends Variables {


    private static WebDriver driver;

    @Before
    public void setup() {

        if (driver == null) {
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Variables.TIME_OUT));

            homePage = new HomePage(driver);
            loginPage = new LoginPage(driver);
            registerPage = new RegisterPage(driver);
            checkoutPage = new CheckoutPage(driver);
        }
    }

    @After
    public void tearDown() {

        //if (driver != null) {
         //  driver.quit();
         // driver = null;
      // }
    }
}
