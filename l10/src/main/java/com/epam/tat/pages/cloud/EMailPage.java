package com.epam.tat.pages.cloud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EMailPage {

    public static final int TIME_OUT = 20;

    private By authFail = By.xpath("//div[@class='b-login__errors']");

    private WebDriver driver;

    public EMailPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement visibilityWait(By locator) {
        return new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String invalidAuthorization() {
        driver.switchTo().defaultContent();
        visibilityWait(By.xpath("//div[@class='pm-menu__left']"));
        driver.switchTo().frame(0);
        visibilityWait(authFail);
        return driver.findElement(authFail).getText();
    }
}
