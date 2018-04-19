package com.epam.tat.pages.cloud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CloudLogPage {

    public static final int TIME_OUT = 20;

    private By logInButton = By.xpath("//div[contains(@class, '_try')]");
    private By loginInputLocator = By.xpath("//input[@id='ph_login']");
    private By passwordInputLocator = By.id("ph_password");
    private By submitButtonLocator = By.xpath("//input[@type='submit']");

    private WebDriver driver;

    public CloudLogPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement visibilityWait(By locator) {
        return new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void openLogForm() {
        driver.findElement(logInButton).click();
    }

    public CloudHomePage correctAuthorization(String email, String correctPass) {
        visibilityWait(loginInputLocator);
        driver.findElement(loginInputLocator).clear();
        driver.findElement(loginInputLocator).sendKeys(email);
        driver.findElement(passwordInputLocator).clear();
        driver.findElement(passwordInputLocator).sendKeys(correctPass);
        driver.findElement(submitButtonLocator).click();
        return new CloudHomePage(driver);
    }

    public EMailPage incorrectAuthorization(String email, String incorrectPass) {
        visibilityWait(loginInputLocator);
        driver.findElement(loginInputLocator).clear();
        driver.findElement(loginInputLocator).sendKeys(email);
        driver.findElement(passwordInputLocator).clear();
        driver.findElement(passwordInputLocator).sendKeys(incorrectPass);
        driver.findElement(submitButtonLocator).click();
        return new EMailPage(driver);
    }
}
