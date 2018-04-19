package com.epam.tat.pages.mail;

import com.epam.tat.framework.ui.Browser;
import com.epam.tat.framework.ui.Element;
import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class InboxPage {

    private static final int TIME_OUT = 50;

    private Element authMenu = new Element(By.id("PH_user-email"));
    private Element inboxMsgsLink = new Element(xpath("//a[@class='b-nav__link' and @href='/messages/inbox/']"));
    private Element checkMsg = new Element(xpath("(//div[contains(@class, 'b-datalist_letters_from')]"
            + "//div[@class='b-datalist__item__subj'])[1]"));
    private Element writeLetter = new Element(xpath("//a[@data-name='compose']"));

    private String msgBySubject = ".b-datalist_letters_from a[data-subject='%s'] div[class='b-datalist__item__subj']";

    public NewMessagePage writeNewLetter() {
        writeLetter.isClickable();
        writeLetter.click();
        return new NewMessagePage();
    }

    public boolean checkAuthorization() {
        return authMenu.isDisplayed();
    }

    public String getSubject(String subj) {
        inboxMsgsLink.isDisplayed();
        inboxMsgsLink.isClickable();
        inboxMsgsLink.click();
        Element msg = new Element(cssSelector(String.format(msgBySubject, subj)));
        msg.isDisplayed();
        return msg.getText();
    }

    public String itemSubject() {
        new WebDriverWait(Browser.getInstance().getWrappedDriver(), TIME_OUT).until(new Function<WebDriver, Boolean>() {
            Boolean isButtonFound = Boolean.FALSE;
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    inboxMsgsLink.click();
                    isButtonFound = Boolean.TRUE;
                } catch (StaleElementReferenceException e) {
                    return isButtonFound;
                }
                return isButtonFound;
            }
        });
        checkMsg.isDisplayed();
        return checkMsg.getAttribute("textContent");
    }
}
