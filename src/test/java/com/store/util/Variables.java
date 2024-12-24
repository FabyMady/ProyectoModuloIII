package com.store.util;

import com.store.factory.CheckoutPage;
import com.store.factory.HomePage;
import com.store.factory.LoginPage;
//import com.store.factory.RegisterPage;
import com.store.factory.RegisterPage;
import org.openqa.selenium.WebDriver;

public class Variables {

    public static WebDriver driver;

    public static int TIME_OUT = 10;

    protected static HomePage homePage;

   protected static LoginPage loginPage;

    protected static RegisterPage registerPage;

    protected static CheckoutPage checkoutPage;
}
