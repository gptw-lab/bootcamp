package com.juiceshop.resources;

import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesBuilder {

    private final DesiredCapabilities capabilities;

    public CapabilitiesBuilder(){
        capabilities = new DesiredCapabilities();
    }

    public CapabilitiesBuilder withChrome(){
        capabilities.setCapability("browserName", "chrome");
        return this;
    }

    public CapabilitiesBuilder withFirefox(){
        capabilities.setCapability("browserName", "firefox");
        return this;
    }

    public DesiredCapabilities build(){
        return capabilities;
    }

}
