package com.epam.tat.pages.mail;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InboxPage {

    private static final int TIME_OUT = 50;

    private By authMenuLocator = By.id("PH_user-email");
    private By inboxMsgsLink = By.xpath("//a[@class='b-nav__link' and @href='/messages/inbox/']");
    private By checkMsg = By.xpath("(//div[contains(@class, 'b-datalist_letters_from')]"
            + "//div[@class='b-datalist__item__subj'])[1]");
    private By writeLetterLocator = By.xpath("//a[@data-name='compose']");

    private String msgBySubject = ".b-datalist_letters_from a[data-subject='%s'] div[class='b-datalist__item__subj']";

    private WebDriver driver;

    public InboxPage(WebDriver driver) {
        this.driver = driver;
    }

    private void visibilityWait(By locator) {
        new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public NewMessagePage writeNewLetter() {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
        wait.until(ExpectedConditions.elementToBeClickable(writeLetterLocator));
        driver.findElement(writeLetterLocator).click();
        return new NewMessagePage(driver);
    }

    public String checkAuthorization() {
        visibilityWait(authMenuLocator);
        return driver.findElement(authMenuLocator).getText();
    }

    public String getSubject(String subj) {
        visibilityWait(inboxMsgsLink);
        driver.findElement(inboxMsgsLink).click();
        visibilityWait(By.cssSelector(String.format(msgBySubject, subj)));
        return driver.findElement(By.cssSelector(String.format(msgBySubject, subj))).getText();
    }

    public String itemSubject() {
        new WebDriverWait(driver, TIME_OUT).until(new Function<WebDriver, Boolean>() {
            Boolean isButtonFound = Boolean.FALSE;
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    driver.findElement(inboxMsgsLink).click();
                    isButtonFound = Boolean.TRUE;
                } catch (StaleElementReferenceException e) {
                    return isButtonFound;
                }
                return isButtonFound;
            }
        });
        visibilityWait(checkMsg);
        return driver.findElement(checkMsg).getText();
    }
}
