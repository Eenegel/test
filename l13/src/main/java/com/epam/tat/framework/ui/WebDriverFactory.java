package com.epam.tat.framework.ui;

import com.epam.tat.framework.runner.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    public static final int TIME_OUT = 10;
    public static final int SCRIPT_TIME_OUT = 10;

    public static WebDriver getWebDriver() {
        WebDriver webDriver;
        switch (Parameters.instance().getBrowserType()) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", Parameters.instance().getChromeDriver());
                webDriver = new ChromeDriver();
                webDriver.manage().window().maximize();
                webDriver.manage().timeouts().pageLoadTimeout(TIME_OUT, TimeUnit.SECONDS);
                webDriver.manage().timeouts().setScriptTimeout(SCRIPT_TIME_OUT, TimeUnit.SECONDS);
                webDriver.get("https://mail.ru/");
                break;
            default:
                throw new RuntimeException("No support for browser: " + Parameters.instance().getBrowserType());
        }
        return webDriver;
    }
}
