package com.epam.tat.tests.cloud;

import com.epam.tat.framework.bo.Account;
import com.epam.tat.framework.bo.AccountFactory;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.pages.mail.exception.AuthenticationException;
import com.epam.tat.services.AuthenticationService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginToCloudTest {

    private AuthenticationService authService;
    private Account account;

    @BeforeMethod
    public void switchToCloud() throws InterruptedException {
        authService = new AuthenticationService();
        authService.switchToCloud();
    }

    @Test(description = "Login with correct data must return new logged cloud page")
    public void testLogInToCloudWithCorrectData() {
        account = AccountFactory.getExistentAccount();
        authService.cloudSignIn(account);
    }

    @Test(description = "Login with incorrect data must return error on new page",
            expectedExceptions = AuthenticationException.class)
    public void testWithIncorrectData() {
        account = AccountFactory.getInvalidAccount();
        authService.cloudSignIn(account);
    }

    @Test(description = "Login with empty login must return error on current page",
            expectedExceptions = AuthenticationException.class)
    public void testWithEmptyPassword() {
        account = AccountFactory.getAccountWithoutLogin();
        authService.cloudSignIn(account);
        assertTrue(authService.isLoginBorderRed());

    }

    @Test(description = "Login with emoty password must return error on current page",
            expectedExceptions = AuthenticationException.class)
    public void testWithEmptyLogin() {
        account = AccountFactory.getAccountWithoutPassword();
        authService.cloudSignIn(account);
        assertTrue(authService.isPasswordBorderRed());
    }

    @AfterMethod
    public void tearDown() {
        Browser.getInstance().stopBrowser();
    }
}
