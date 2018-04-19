package com.epam.tat.pages.mail;

import com.epam.tat.pages.cloud.CloudLogPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailHomePage {

    public static final int TIME_OUT = 20;

    private By loginInputLocator = By.id("mailbox__login");
    private By passwordInputLocator = By.id("mailbox__password");
    private By submitButtonLocator = By.id("mailbox__auth__button");
    private By authFail = By.id("mailbox:authfail");
    private By cloudBut = By.xpath("//a[@name='clb3996485']");

    private WebDriver driver;

    public MailHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public InboxPage correctAuthorization(String email, String correctPass) {
        driver.findElement(loginInputLocator).clear();
        driver.findElement(passwordInputLocator).clear();
        driver.findElement(loginInputLocator).sendKeys(email);
        driver.findElement(passwordInputLocator).sendKeys(correctPass);
        driver.findElement(submitButtonLocator).click();
        return new InboxPage(driver);
    }

    public void incorrectAuthorization(String email, String incorrectPass) {
        driver.findElement(loginInputLocator).clear();
        driver.findElement(loginInputLocator).sendKeys(email);
        driver.findElement(passwordInputLocator).clear();
        driver.findElement(passwordInputLocator).sendKeys(incorrectPass);
        driver.findElement(submitButtonLocator).click();
        new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(authFail));
    }

    public CloudLogPage switchToCloudPage() {
        driver.findElement(cloudBut).click();
        return new CloudLogPage(driver);
    }
}
