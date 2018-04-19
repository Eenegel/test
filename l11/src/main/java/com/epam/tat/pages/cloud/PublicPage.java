package com.epam.tat.pages.cloud;

import com.epam.tat.framework.ui.Element;
import org.openqa.selenium.By;

public class PublicPage {

    private Element checkFileTitle = new Element(By.xpath("//a[@class='viewer__information__link']"));

    public String getTitleText() {
        checkFileTitle.isDisplayed();
        return checkFileTitle.getText();
    }

}
