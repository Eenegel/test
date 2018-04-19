package com.epam.tat.pages.mail;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewMessagePage {

    public static final int TIME_OUT = 20;

    private static final int FRAME_ID = 3;

    private By inputForAddressLocator = By.xpath("//textarea[@data-original-name='To']");
    private By topicLocator = By.name("Subject");
    private By textLocator = By.xpath("//body[@id='tinymce']");
    private By sendMsgLocator = By.xpath("//div[@data-name='send']");
    private By acceptButton = By.xpath("//div[@class='is-compose-empty_in']//button[@type='submit']");
    private By saveDraftButton = By.xpath("//div[@data-name='saveDraft']");
    private By draftsLink = By.xpath("//a[@href='/messages/drafts/']");
    private By checkForm = By.xpath("//div[@class='is-compose-empty_in']");
    private By saveStatus = By.xpath("//div[@data-mnemo='saveStatus']");

    private WebDriver driver;

    public NewMessagePage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement visibilityWait(By locator) {
        return new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public InboxPage properlyFillingFields(String to, String subject, String  text) {
        driver.findElement(inputForAddressLocator).sendKeys(to);
        driver.findElement(topicLocator).sendKeys(subject);
        waitForFrame();
        driver.findElement(textLocator).sendKeys(text);
        driver.switchTo().defaultContent();
        driver.findElement(sendMsgLocator).click();
        return new InboxPage(driver);
    }

    public boolean isAlertPresent() {
        new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.alertIsPresent());
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public void acceptCheck() {
        visibilityWait(checkForm);
        driver.findElement(acceptButton).click();
    }

    public DraftsPage createMsg(String template, String text) {
        driver.findElement(topicLocator).sendKeys(template);
        waitForFrame();
        driver.findElement(textLocator).sendKeys(text);
        driver.switchTo().defaultContent();
        driver.findElement(saveDraftButton).click();
        visibilityWait(saveStatus);
        driver.findElement(draftsLink).click();
        return new DraftsPage(driver);
    }

    private void waitForFrame() {
        new WebDriverWait(driver, TIME_OUT).until(new Function<WebDriver, Boolean>() {
            Boolean isFrameFound = Boolean.FALSE;
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    driver.switchTo().frame(FRAME_ID);
                    isFrameFound = Boolean.TRUE;
                } catch (NoSuchFrameException e) {
                    return isFrameFound;
                }
                return isFrameFound;
            }
        });
    }
}
