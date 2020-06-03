package com.juiceshop.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebdriverSingleton {

    private static WebdriverSingleton instance;
    private final WebDriver driver;

    private WebdriverSingleton(){
        driver = new ChromeDriver();
    }

    public static WebdriverSingleton getInstance(){
        if(instance == null){
            instance = new WebdriverSingleton();
        }
        return instance;
    }


}
