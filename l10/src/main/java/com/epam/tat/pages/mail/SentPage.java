package com.epam.tat.pages.mail;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SentPage {

    private static final int TIME_OUT = 20;

    private By sentMsgsLink = By.xpath("//a[@href='/messages/sent/']");
    private By msgBySubject = By.xpath("(//div[contains(@class, 'b-datalist_letters_to')]"
            + "//div[@class='b-datalist__item__subj'])[1]");

    private String checkMsgBySubject = ".b-datalist_letters_to a[data-subject='%s']";

    private WebDriver driver;

    public SentPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement visibilityWait(By locator) {
        return new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getSubject(String subj) {
        visibilityWait(By.xpath("//div[@class='message-sent__title']"));
        visibilityWait(sentMsgsLink);
        driver.findElement(sentMsgsLink).click();
        visibilityWait(By.cssSelector(String.format(checkMsgBySubject, subj)));
        return driver.findElement(By.cssSelector(String.format(checkMsgBySubject, subj))).getText();
    }

    public String itemSubject() {
        new WebDriverWait(driver, TIME_OUT).until(new Function<WebDriver, Boolean>() {
            Boolean isButtonFound = Boolean.FALSE;
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    driver.findElement(sentMsgsLink).click();
                    isButtonFound = Boolean.TRUE;
                } catch (StaleElementReferenceException e) {
                    return isButtonFound;
                }
                return isButtonFound;
            }
        });
        visibilityWait(msgBySubject);
        return driver.findElement(msgBySubject).getAttribute("textContent");
    }
}
