package com.juiceshop.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private RemoteWebDriver driver;
    private final DriverType selectedDriverType;

    public DriverFactory(){
        DriverType driverType = DriverType.FIREFOX;
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        try{
            driverType = DriverType.valueOf(browser);
        }catch (IllegalArgumentException ignored){
            System.err.println("Unknown driver specified, defaulting to " + driverType);
        }catch (NullPointerException ignored){
            System.err.println("No driver specified, defaulting to " + driverType);
        }
        selectedDriverType = driverType;
    }

    public RemoteWebDriver getDriver() throws MalformedURLException {
        if(null == driver){
            System.out.println("Creating new driver instance");
            createWebDriver(selectedDriverType);
        }
        return driver;
    }

    private void createWebDriver(DriverType driverType) throws MalformedURLException {
        boolean remote = Boolean.getBoolean("remote");
        if (remote) {
            URL gridURL = new URL(System.getProperty("seleniumGridURL"));
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", selectedDriverType.toString().toLowerCase());
            driver = new RemoteWebDriver(gridURL, capabilities);
        }
    }

}
