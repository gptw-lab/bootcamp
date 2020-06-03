package com.juiceshop.test;

import com.github.javafaker.Faker;
import com.juiceshop.resources.CapabilitiesBuilder;
import com.juiceshop.resources.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class UserRegistrationTest {
    private WebDriverWait wait;
    private RemoteWebDriver driver;
    private Faker faker;
    private DesiredCapabilities capabilities;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() throws MalformedURLException {
        faker = new Faker();
        driver = new DriverFactory().getDriver();
        wait = new WebDriverWait(driver, 30);
        driver.get("http://juice-shop:3000");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@aria-label='Close Welcome Banner']")).click();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void userIsAbleToRegister() {
        String emailId = faker.internet().emailAddress();
        String name = faker.name().firstName();
        driver.findElement(By.id("navbarAccount")).click();
        driver.findElement(By.id("navbarLoginButton")).click();
        driver.findElement(By.linkText("Not yet a customer?")).click();
        driver.findElement(By.id("emailControl")).sendKeys(emailId);
        driver.findElement(By.id("passwordControl")).sendKeys("password1");
        driver.findElement(By.id("repeatPasswordControl")).sendKeys("password1");
        driver.findElement(By.name("securityQuestion")).click();
        driver.findElement(By.id("mat-option-4")).click();
        driver.findElement(By.id("securityAnswerControl")).sendKeys(name);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("registerButton"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Registration completed successfully. You can now log in.')]")
        ));
    }

}
