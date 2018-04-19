package com.epam.tat.pages.cloud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PublicPage {

    public static final int TIME_OUT = 40;

    private By checkFileTitle = By.xpath("//a[@class='viewer__information__link']");

    private WebDriver driver;

    public PublicPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleText() {
        new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(checkFileTitle));
        return driver.findElement(checkFileTitle).getText();
    }

}
