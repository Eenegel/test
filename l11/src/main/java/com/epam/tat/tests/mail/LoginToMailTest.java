package com.epam.tat.tests.mail;

import com.epam.tat.framework.bo.Account;
import com.epam.tat.framework.bo.AccountFactory;
import com.epam.tat.framework.runner.TestListener;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.pages.mail.exception.AuthenticationException;
import com.epam.tat.services.AuthenticationService;
import org.testng.annotations.*;

import static org.testng.Assert.*;

@Listeners(TestListener.class)
public class LoginToMailTest {

    private AuthenticationService authService;
    private Account account;

    @BeforeMethod
    public void setUp() {
        Browser browser = Browser.getInstance();
        authService = new AuthenticationService();
    }

    @Test(description = "Login with correct data must return new logged mail page")
    public void testWithCorrectData() {
        account = AccountFactory.getExistentAccount();
        authService.mailSignIn(account);
    }

    @Test(description = "Login with incorrect data must return error on current page",
            expectedExceptions = AuthenticationException.class)
    public void testWithIncorrectData() {
        account = AccountFactory.getInvalidAccount();
        authService.mailSignIn(account);
    }

    @Test(description = "Login with empty login must return error on current page",
            expectedExceptions = AuthenticationException.class)
    public void testWithEmptyPassword() {
        account = AccountFactory.getAccountWithoutLogin();
        authService.mailSignIn(account);
    }

    @Test(description = "Login with emoty password must return error on current page",
            expectedExceptions = AuthenticationException.class)
    public void testWithEmptyLogin() {
        account = AccountFactory.getAccountWithoutPassword();
        authService.mailSignIn(account);
    }

    @AfterMethod
    public void tearDown() {
        Browser.getInstance().stopBrowser();
    }
}
