package com.epam.tat.pages.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrashPage {

    private static final int TIME_OUT = 30;

    private String findMsgBySubjectLoc = "(//div[@class='b-datalists']//a[@data-subject='%s'])[1]";
    private String checkMsgBySubjectlocator = "(//div[@class='b-datalists']//a[@data-subject='%s'])[1]"
            + "//div[@class='b-checkbox__box']";
    private String notify = "//html[@id='jsHtml']//body//div[contains(@class, 'js-ok')]";
    private String searchString = "From:%s  Subject:%s";

    private By delNotify = By.xpath("//div[@class='notify']");
    private By deleteButton = By.xpath("//div[contains(@data-cache-key, '500002')]"
            + "//div[@data-mnemo='toolbar-letters']//div[@data-name='remove']");
    private By emptySearch = By.xpath("//div[@id='b-search']//div[@class='b-folder-promo__content']//div[2]");
    private By searchLine = By.xpath("//form[@id='portal-menu__search__form']//input[@name='q_query']");
    private By searchParams = By.xpath("//span[contains(@class, 'pm-toolbar__search__params__text')]");
    private By searchButton = By.xpath("//button[@data-act='search_advanced']");
    private By searchInTrash = By.xpath("(//a[@data-mnemo='search_in_trash'])[1]");

    private WebDriver driver;

    public TrashPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement visibilityWait(By locator) {
        return new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void checkMsg(String template) {
        visibilityWait(By.xpath(String.format(findMsgBySubjectLoc, template)));
    }

    public void deleteMsg(String template) {
        driver.findElement(By.xpath(String.format(checkMsgBySubjectlocator, template))).click();
        driver.findElement(deleteButton).click();
        visibilityWait(By.xpath(notify));
    }

    public boolean notFoundResult() {
        return visibilityWait(emptySearch) != null;
    }

    public void searchMsg(String email, String template) {
        driver.findElement(searchLine).sendKeys(String.format(searchString, email, template));
        new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.invisibilityOfElementLocated(delNotify));
        new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.elementToBeClickable(searchParams));
        driver.findElement(searchParams).click();
        driver.findElement(searchButton).click();
        new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.elementToBeClickable(searchInTrash));
        driver.findElement(searchInTrash).click();
    }
}
