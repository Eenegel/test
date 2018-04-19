package com.epam.tat.services;

import com.epam.tat.framework.bo.Account;
import com.epam.tat.framework.logging.Log;
import com.epam.tat.pages.cloud.CloudHomePage;
import com.epam.tat.pages.cloud.CloudLogPage;
import com.epam.tat.pages.mail.InboxPage;
import com.epam.tat.pages.mail.MailHomePage;
import com.epam.tat.pages.mail.exception.AuthenticationException;

public class AuthenticationService {

    private final String redBorderColour = "rgb(206, 25, 0)";

    public void mailSignIn(Account account) {
        Log.debug(String.format("Trying to log in with login: %s, password: %s",
                account.getLogin(), account.getPassword()));
        MailHomePage homePage = new MailHomePage();
        homePage.setLogin(account.getLogin())
                .setPassword(account.getPassword())
                .signIn();
        InboxPage inboxPage = new InboxPage();

        if (homePage.hasError()) {
            throw new AuthenticationException(homePage.getErrorMessage());
        }
        if (!inboxPage.checkAuthorization()) {
            throw new AuthenticationException("Is not logged in!");
        }

    }

    public void cloudSignIn(Account account) {
        Log.debug(String.format("Trying to log in with login: %s, password: %s",
                account.getLogin(), account.getPassword()));
        new CloudLogPage()
                .openLogForm()
                .setLogin(account.getLogin())
                .setPassword(account.getPassword())
                .signIn();

        if (!isLoggedIn()) {
            throw new AuthenticationException("Is not logged in!");
        }
    }

    public boolean isLoggedIn() {
        return new CloudHomePage().checkAuthorization();
    }

    public void switchToCloud() {
        Log.debug(String.format("Trying to switch to cloud"));
        new MailHomePage().switchToCloudPage();
    }

    public boolean isLoginBorderRed() {
        return new CloudLogPage().getLoginBorderColor().equals(redBorderColour);
    }

    public boolean isPasswordBorderRed() {
        return new CloudLogPage().getPasswordBorderColor().equals(redBorderColour);
    }

}
