package com.epam.tat.pages.cloud;

import com.epam.tat.framework.ui.Element;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;

public class EMailPage {

    private Element frame = new Element(xpath("//iframe[@class='ag-popup__frame__layout__iframe']"));

    private Element authFail = new Element(By.xpath("//div[@class='b-login__errors']"));
    private Element pageBar = new Element(By.xpath("//div[@class='pm-menu__left']"));

    public boolean invalidAuthorization() {
        frame.switchToDefaultContent();
        pageBar.isDisplayed();
        frame.switchToFrame();
        authFail.isDisplayed();
        return true;
    }
}
