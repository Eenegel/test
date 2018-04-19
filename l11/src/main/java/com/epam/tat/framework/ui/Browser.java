package com.epam.tat.framework.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

public final class Browser implements WrapsDriver {

    public static final int TIME_OUT = 10;
    public static final int SCRIPT_TIME_OUT = 10;

    private static Browser instance;

    private WebDriver wrappedWebDriver;

    private Browser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        wrappedWebDriver = new ChromeDriver();
        wrappedWebDriver.manage().window().maximize();
        wrappedWebDriver.manage().timeouts().pageLoadTimeout(TIME_OUT, TimeUnit.SECONDS);
        wrappedWebDriver.manage().timeouts().setScriptTimeout(SCRIPT_TIME_OUT, TimeUnit.SECONDS);
        wrappedWebDriver.get("https://mail.ru/");
    }

    @Override
    public WebDriver getWrappedDriver() {
        return wrappedWebDriver;
    }

    public static Browser getInstance() {
        if (instance == null) {
            instance = new Browser();
        }
        return instance;
    }

    public void stopBrowser() {
        try {
            getInstance().wrappedWebDriver.quit();
            getInstance().wrappedWebDriver = null;
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            instance = null;
        }
    }

    public void click(By by) {
        wrappedWebDriver
                .findElement(by)
                .click();
    }

    public void type(By by, String text) {
        waitForAppear(by);
        wrappedWebDriver
                .findElement(by)
                .sendKeys(text);
    }

    public void switchTo(By by) {
        waitForAppear(by);
        wrappedWebDriver
                .switchTo()
                .frame(wrappedWebDriver.findElement(by));
    }

    public void clear(By by) {
        wrappedWebDriver
                .findElement(by)
                .clear();
    }

    public String getText(By by) {
        waitForAppear(by);
        return wrappedWebDriver
                .findElement(by)
                .getText();
    }

    public void waitForAppear(By by) {
        WebDriverWait wait = new WebDriverWait(wrappedWebDriver, TIME_OUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void dragAndDrop(WebElement from, WebElement to) {
        new Actions(wrappedWebDriver)
                .dragAndDrop(from, to)
                .build()
                .perform();
    }

    public void rightClick(By by) {
        new Actions(wrappedWebDriver)
                .contextClick(Browser.getInstance().getWrappedDriver().findElement(by))
                .perform();
    }

    public boolean isVisible(By by) {
        try {
            waitForAppear(by);
            return wrappedWebDriver.findElement(by).isDisplayed();
        } catch (RuntimeException e) {
            return false;
        }
    }

    public void waitForClickable(By by) {
        WebDriverWait wait = new WebDriverWait(wrappedWebDriver, TIME_OUT);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public boolean isClickable(By by) {
        try {
            waitForClickable(by);
            return true;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public void waitForInvisibillity(By by) {
        WebDriverWait wait = new WebDriverWait(wrappedWebDriver, TIME_OUT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public boolean isInvisible(By by) {
        try {
            waitForInvisibillity(by);
            return true;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public String getAttribute(By by, String text) {
        waitForAppear(by);
        return wrappedWebDriver
                .findElement(by)
                .getAttribute(text);
    }

}
