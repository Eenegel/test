package com.epam.tat.pages.mail;

import com.epam.tat.framework.ui.Element;

import static org.openqa.selenium.By.xpath;

public class TrashPage {

    private String findMsgBySubject = "(//div[@class='b-datalists']//a[@data-subject='%s'])[1]";
    private String checkMsgBySubject = "(//div[@class='b-datalists']//a[@data-subject='%s'])[1]"
            + "//div[@class='b-checkbox__box']";
    private Element notify = new Element(xpath("//html[@id='jsHtml']//body//div[contains(@class, 'js-ok')]"));
    private String searchString = "From:%s  Subject:%s";

    private Element delNotify = new Element(xpath("//div[@class='notify']"));
    private Element deleteButton = new Element(xpath("//div[contains(@data-cache-key, '500002')]"
            + "//div[@data-mnemo='toolbar-letters']//div[@data-name='remove']"));
    private Element emptySearch = new Element(xpath("//div[@id='b-search']"
            + "//div[@class='b-folder-promo__content']//div[2]"));
    private Element searchLine = new Element(xpath("//form[@id='portal-menu__search__form']//input[@name='q_query']"));
    private Element searchParams = new Element(xpath("//span[contains(@class, 'pm-toolbar__search__params__text')]"));
    private Element searchButton = new Element(xpath("//button[@data-act='search_advanced']"));
    private Element searchInTrash = new Element(xpath("(//a[@data-mnemo='search_in_trash'])[1]"));

    public void deleteMsg(String template) {
        new Element(xpath(String.format(findMsgBySubject, template)));
        Element check = new Element(xpath(String.format(checkMsgBySubject, template)));
        check.isDisplayed();
        check.click();
        deleteButton.click();
        notify.isDisplayed();
    }

    public boolean notFoundResult() {
        return emptySearch.isDisplayed();
    }

    public void searchMsg(String email, String template) {
        searchLine.type(String.format(searchString, email, template));
        delNotify.isInvisible();
        searchParams.isClickable();
        searchParams.click();
        searchButton.click();
        searchInTrash.isClickable();
        searchInTrash.click();
    }
}
