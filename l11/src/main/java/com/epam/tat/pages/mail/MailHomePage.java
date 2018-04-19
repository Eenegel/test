package com.epam.tat.pages.mail;

import com.epam.tat.framework.ui.Element;
import com.epam.tat.pages.cloud.CloudLogPage;
import org.openqa.selenium.By;

public class MailHomePage {

    private Element loginInput = new  Element(By.id("mailbox__login"));
    private Element passwordInput = new Element(By.id("mailbox__password"));
    private Element submitButton = new Element(By.id("mailbox__auth__button"));
    private Element authFail = new Element(By.id("mailbox:authfail"));
    private Element cloudBut = new Element(By.xpath("//a[@name='clb3996485']"));

    public MailHomePage setLogin(String login) {
        loginInput.clear();
        loginInput.type(login);
        return new MailHomePage();
    }

    public MailHomePage setPassword(String password) {
        passwordInput.clear();
        passwordInput.type(password);
        return new MailHomePage();
    }

    public MailHomePage signIn() {
        submitButton.click();
        return this;
    }

    public String getErrorMessage() {
        return authFail.getText();
    }

    public boolean hasError() {
        return authFail.isDisplayed();
    }

    public CloudLogPage switchToCloudPage() {
        cloudBut.click();
        return new CloudLogPage();
    }

}
