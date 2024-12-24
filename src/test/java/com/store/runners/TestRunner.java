package com.store.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.store.stepDefinitions", "com.store.util"},
        plugin = {"pretty", "summary",
                "html:target/cucumber.html",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = false,
        publish = true,
        dryRun = false
       // tags = "@Checkout"
)

public class TestRunner extends AbstractTestNGCucumberTests {
}