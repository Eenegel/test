package com.epam.tat.framework.ui;

import com.epam.tat.framework.logging.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;

public final class Browser implements WrapsDriver {

    public static final int TIME_OUT = 10;

    private static ThreadLocal<Browser> instance = new ThreadLocal<>();

    private WebDriver wrappedWebDriver;

    private Browser() {
        this.wrappedWebDriver = WebDriverFactory.getWebDriver();
    }

    @Override
    public WebDriver getWrappedDriver() {
        return wrappedWebDriver;
    }

    public static synchronized Browser getInstance() {
        if (instance.get() == null) {
            instance.set(new Browser());
            Log.debug("[Browser was opened]");
        }
        return instance.get();
    }

    public void stopBrowser() {
        try {
            if (wrappedWebDriver != null) {
                wrappedWebDriver.quit();
            }
        } finally {
            instance.set(null);
            Log.debug("[Browser stopped]");
        }
    }

    public void click(By by) {
        Log.debug(String.format("Click on element %s", by));
        wrappedWebDriver
                .findElement(by)
                .click();
    }

    public void type(By by, String text) {
        waitForAppear(by);
        Log.debug(String.format("Typing to element %s", by));
        wrappedWebDriver
                .findElement(by)
                .sendKeys(text);
    }

    public void switchTo(By by) {
        waitForAppear(by);
        Log.debug(String.format("Switch to element %s", by));
        wrappedWebDriver
                .switchTo()
                .frame(wrappedWebDriver.findElement(by));
    }

    public void clear(By by) {
        Log.debug(String.format("Clear the element %s", by));
        wrappedWebDriver
                .findElement(by)
                .clear();
    }

    public String getText(By by) {
        waitForAppear(by);
        Log.debug(String.format("Getting text from element %s", by));
        return wrappedWebDriver
                .findElement(by)
                .getText();
    }

    public void waitForAppear(By by) {
        WebDriverWait wait = new WebDriverWait(wrappedWebDriver, TIME_OUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void dragAndDrop(WebElement from, WebElement to) {
        Log.debug(String.format("Drag and drop from %s to %s", from, to));
        new Actions(wrappedWebDriver)
                .dragAndDrop(from, to)
                .build()
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
        Log.debug(String.format("Getting attribute %s from element %s", text, by));
        waitForAppear(by);
        return wrappedWebDriver
                .findElement(by)
                .getAttribute(text);
    }

    public static void screenshot() {
        File screenshotFile = new File(String.format("src/main/screenshots/screenshot%s.png", System.nanoTime()));
        try {
            byte[] screenshotBytes = ((TakesScreenshot) Browser.getInstance()
                    .getWrappedDriver()).getScreenshotAs(OutputType.BYTES);
            FileUtils.writeByteArrayToFile(screenshotFile, screenshotBytes);
            Log.info("Screenshot was taken. File: " + screenshotFile.getCanonicalPath());
            String screenshotImgTag = String.format("<img src=%s>", screenshotFile.getCanonicalPath());
            Reporter.log("<a href=" + screenshotFile.getCanonicalPath() + " target='blank' >"
                    + screenshotImgTag + "</a>");
        } catch (IOException e) {
            System.out.println("Failed to write screenshot! " + e);
        }
    }

}
