package com.epam.tat.pages.mail;

import com.epam.tat.framework.ui.Browser;
import com.epam.tat.framework.ui.Element;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.By.*;

public class SentPage {

    private static final int TIME_OUT = 20;

    private Element sentMsgsLink = new Element(xpath("//a[@href='/messages/sent/']"));
    private Element msgBySubject = new Element(xpath("(//div[contains(@class, 'b-datalist_letters_to')]"
            + "//div[@class='b-datalist__item__subj'])[1]"));

    private String checkMsgBySubject = ".b-datalist_letters_to a[data-subject='%s']";
    private Element sendingConfirmation = new Element(xpath("//div[@class='message-sent__title']"));

    public String getSubject(String subj) {
        sendingConfirmation.isDisplayed();
        sentMsgsLink.isClickable();
        sentMsgsLink.isDisplayed();
        sentMsgsLink.click();
        Element check = new Element(cssSelector(String.format(checkMsgBySubject, subj)));
        check.isDisplayed();
        return check.getText();
    }

    public String itemSubject() {
        new WebDriverWait(Browser.getInstance().getWrappedDriver(), TIME_OUT).until(new Function<WebDriver, Boolean>() {
            Boolean isButtonFound = Boolean.FALSE;
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    sentMsgsLink.click();
                    isButtonFound = Boolean.TRUE;
                } catch (StaleElementReferenceException e) {
                    return isButtonFound;
                }
                return isButtonFound;
            }
        });
        msgBySubject.isDisplayed();
        return msgBySubject.getAttribute("textContent");
    }
}
