package com.epam.tat.pages.mail;

import com.epam.tat.framework.ui.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import static org.openqa.selenium.By.xpath;

public class NewMessagePage {

    public static final int TIME_OUT = 20;

    private Element inputForAddress = new Element(xpath("//textarea[@data-original-name='To']"));
    private Element topic = new Element(By.name("Subject"));
    private Element textField = new Element(xpath("//body[@id='tinymce']"));
    private Element sendMsg = new Element(xpath("//div[@data-name='send']"));
    private Element acceptButton = new Element(xpath("//div[@class='is-compose-empty_in']//button[@type='submit']"));
    private Element saveDraftButton = new Element(xpath("//div[@data-name='saveDraft']"));
    private Element draftsLink = new Element(xpath("//a[@href='/messages/drafts/']"));
    private Element checkForm = new Element(xpath("//div[@class='is-compose-empty_in']"));
    private Element saveStatus = new Element(xpath("//div[@data-mnemo='saveStatus']"));
    private Element frame = new Element(xpath("//iframe[starts-with(@id,'toolkit-')]"));

    public NewMessagePage setTo(String to) {
        inputForAddress.type(to);
        return this;
    }

    public NewMessagePage setSubject(String subject) {
        topic.type(subject);
        return this;
    }

    public NewMessagePage setText(String text) {
        frame.switchToFrame();
        textField.type(text);
        frame.switchToDefaultContent();
        return this;
    }

    public DraftsPage saveDraft() {
        saveDraftButton.click();
        saveStatus.isDisplayed();
        draftsLink.click();
        return new DraftsPage();
    }

    public InboxPage sendMail() {
        sendMsg.click();
        return new InboxPage();
    }

    public boolean isAlertPresent() {
        new WebDriverWait(Browser.getInstance().getWrappedDriver(), TIME_OUT)
                .until(ExpectedConditions.alertIsPresent());
        try {
            Browser.getInstance().getWrappedDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public void acceptCheck() {
        checkForm.isDisplayed();
        acceptButton.click();
    }
}
