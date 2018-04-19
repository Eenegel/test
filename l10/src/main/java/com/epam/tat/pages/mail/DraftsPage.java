package com.epam.tat.pages.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DraftsPage {

    public static final int TIME_OUT = 20;

    private By deleteButtonLocator = By.xpath("//div[contains(@class, 'b-toolbar__btn_grouped_last')"
            + " and @data-name='remove']");
    private By trashLink = By.xpath("//a[@href='/messages/trash/']");
    private By notifyLocator = By.xpath(".//html[@id='jsHtml']//body//div[contains(@class, 'js-ok')]");

    private String checkMsgBySubject = "(//div[@class='b-datalists']//a[@data-subject='%s'])[1]"
            + "//div[@class='b-checkbox__box']";

    private WebDriver driver;

    public DraftsPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement visibilityWait(By locator) {
        return new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public TrashPage deleteMsg(String template) {
        visibilityWait(By.xpath(String.format(checkMsgBySubject, template)));
        driver.findElement(By.xpath(String.format(checkMsgBySubject, template))).click();
        driver.findElement(deleteButtonLocator).click();
        visibilityWait(notifyLocator);
        driver.findElement(trashLink).click();
        return new TrashPage(driver);
    }
}
