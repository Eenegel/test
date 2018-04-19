package com.epam.tat.pages.cloud;

import com.epam.tat.framework.ui.Element;

import static org.openqa.selenium.By.*;

public class CloudLogPage {

    private Element logInButton = new Element(xpath("//div[contains(@class, '_try')]"));
    private Element loginInput = new Element(xpath("//input[@id='ph_login']"));
    private Element passwordInput = new Element(id("ph_password"));
    private Element submitButton = new Element(xpath("//input[@type='submit']"));
    private Element passwordFieldBorder = new Element(xpath("//label[@for='ph_password']"));
    private Element loginFieldBorder = new Element(xpath("//label[@for='ph_login']"));

    public CloudLogPage openLogForm() {
        logInButton.click();
        return this;
    }

    public CloudLogPage setLogin(String login) {
        loginInput.isDisplayed();
        loginInput.clear();
        loginInput.type(login);
        return new CloudLogPage();
    }

    public CloudLogPage setPassword(String password) {
        passwordInput.clear();
        passwordInput.type(password);
        return new CloudLogPage();
    }

    public CloudHomePage signIn() {
        submitButton.click();
        return new CloudHomePage();
    }

    public String getPasswordBorderColor() {
        return passwordFieldBorder.getCssValue("border-color");
    }

    public String getLoginBorderColor() {
        return loginFieldBorder.getCssValue("border-color");
    }
}
