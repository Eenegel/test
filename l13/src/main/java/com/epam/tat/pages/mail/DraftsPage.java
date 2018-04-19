package com.epam.tat.pages.mail;

import com.epam.tat.framework.ui.Element;

import static org.openqa.selenium.By.xpath;

public class DraftsPage {

    private Element deleteButton = new Element(xpath("//div[contains(@class, 'b-toolbar__btn_grouped_last')"
            + " and @data-name='remove']"));
    private Element trashLink = new Element(xpath("//a[@href='/messages/trash/']"));
    private Element notify = new Element(xpath(".//html[@id='jsHtml']//body//div[contains(@class, 'js-ok')]"));

    private String checkMsgBySubject = "(//div[@class='b-datalists']//a[@data-subject='%s'])[1]"
            + "//div[@class='b-checkbox__box']";

    public TrashPage deleteMsg(String template) {
        Element mail = new Element(xpath(String.format(checkMsgBySubject, template)));
        mail.isDisplayed();
        mail.click();
        deleteButton.click();
        notify.isDisplayed();
        trashLink.click();
        return new TrashPage();
    }
}
