package com.juiceshop.resources;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public enum DriverType {

    FIREFOX {
        public RemoteWebDriver getInstance(DesiredCapabilities capabilities){
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("marionette", true);
            options.merge(capabilities);
            return new FirefoxDriver(options);
        }

    },

    CHROME {
        public RemoteWebDriver getInstance(DesiredCapabilities capabilities){
            ChromeOptions options = new ChromeOptions();
            options.merge(capabilities);
            return new ChromeDriver(options);
        }
    },

}
